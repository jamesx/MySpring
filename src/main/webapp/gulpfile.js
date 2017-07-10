/**
 * [gulp description]
 * @type {[type]}
 */
var gulp = require("gulp");
var gutil = require("gulp-util");
var del = require("del");
var rename = require('gulp-rename');
var sass = require('gulp-sass');
var autoprefixer = require('gulp-autoprefixer');
var cached = require('gulp-cached');
var remember = require('gulp-remember');

var webpack = require("webpack");
var webpackConfig = require("./webpack.config.js");

/**
 * ----------------------------------------------------
 * source configuration
 * ----------------------------------------------------
 */

var src = {
    html: "WEB-INF/templates/web/home/*.html",                          // html 文件
    common: ["static/common/**/*"], // Common 目录和 bower_components
    style: "static/src/style/*/index.sass",                  // style 目录下所有 xx/index.less
    assets: "static/assets/**/*"                             // 图片等应用资源
};

var dist = {
    root: "static/dist/",
    html: "WEB-INF/templates/web/home/*.html",
    style: "static/dist/style",
    common: "static/dist/common",
    assets: "static/dist/assets"
};

var bin = {
    root: "static/bin/",
    html: "static/bin/",
    style: "static/bin/style",
    common: "static/bin/common",
    assets: "static/bin/assets"
};

/**
 * ----------------------------------------------------
 *  tasks
 * ----------------------------------------------------
 */

/**
 * clean build dir
 */
function clean(done) {
    del.sync(dist.root);
    done();
}

/**
 * [cleanBin description]
 * @return {[type]} [description]
 */
function cleanBin(done) {
    del.sync(bin.root);
    done();
}

/**
 * [copyCommon description]
 * @return {[type]} [description]
 */
function copyCommon() {
    return gulp.src(src.common)
        .pipe(gulp.dest(dist.common));
}

/**
 * [copyAssets description]
 * @return {[type]} [description]
 */
function copyAssets() {
    return gulp.src(src.assets)
        .pipe(gulp.dest(dist.assets));
}

/**
 * [copyDist description]
 * @return {[type]} [description]
 */
function copyDist() {
    return gulp.src(dist.root + '**/*')
        .pipe(gulp.dest(bin.root));
}


/**
 * [style description]
 * @param  {Function} done [description]
 * @return {[type]}        [description]
 */
function style() {
    return gulp.src(src.style)
        .pipe(cached('style'))
        .pipe(sass())
        .on('error', handleError)
        .pipe(autoprefixer({
            browsers: ['last 3 version']
        }))
        .pipe(gulp.dest(dist.style))
}

exports.style = style;

/**
 * [webpackProduction description]
 * @param  {Function} done [description]
 * @return {[type]}        [description]
 */
function webpackProduction(done) {
    var config = Object.create(webpackConfig);
    config.plugins = config.plugins.concat(
        new webpack.DefinePlugin({
            "process.env": {
                "NODE_ENV": "production"
            }
        }),
        new webpack.optimize.DedupePlugin(),
        new webpack.optimize.UglifyJsPlugin()
    );

    webpack(config, function(err, stats) {
        if(err) throw new gutil.PluginError("webpack:build", err);
        gutil.log("[webpack:production]", stats.toString({
            colors: true
        }));
        done();
    });
}


/**
 * [webpackDevelopment description]
 * @param  {Function} done [description]
 * @return {[type]}        [description]
 */
var devConfig, devCompiler;

devConfig = Object.create(webpackConfig);
devCompiler = webpack(devConfig);

function webpackDevelopment(done) {
    devCompiler.run(function(err, stats) {
        if (err) {
            throw new gutil.PluginError("webpack:build-dev", err);
            return;
        }
        gutil.log("[webpack:build-dev]", stats.toString({
            colors: true
        }));
        done();
    });
}
/**
 * default task
 */
gulp.task("default", gulp.series(
    clean,
    gulp.parallel(copyAssets, copyCommon, html, style, webpackDevelopment)
));



/**
 * [handleError description]
 * @param  {[type]} err [description]
 * @return {[type]}     [description]
 */
function handleError(err) {
    if (err.message) {
        console.log(err.message)
    } else {
        console.log(err)
    }
    this.emit('end')
}