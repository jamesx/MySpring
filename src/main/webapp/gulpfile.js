var gulp = require('gulp');
var sass = require('gulp-sass');
var concat=require('gulp-concat');
var cleanCss=require('gulp-clean-css');
var del=require('del');
const babel = require('gulp-babel');
const uglify=require('gulp-uglify');
const rename=require('gulp-rename');
const imagemin=require('gulp-imagemin');
const webpack = require('gulp-webpack');
const build='./static/build/'
const src='./static/src/';
gulp.task('sass',function(){
    console.log('sass');
    gulp.src(src+'sass/*.sass')
        .pipe(sass())
        .pipe(gulp.dest(src+'css'));
});
gulp.task('concat',['sass'],function(){
    console.log('concat');
    gulp.src(src+'css/*.css')
        .pipe(concat('main.css'))
        .pipe(cleanCss())
        .pipe(gulp.dest(build+'css/'))
})
gulp.task('del',function(){
    del([
        src+ 'css/bg.css'
    ]);
})
gulp.task('js',function(){
    gulp.src(src+'js/react/main/*.js')
        .pipe(webpack({
            watch: false,
            module: {
                loaders: [
                    {
                        test:/\.js$/,
                        exclude:/node-modules/,
                        loader:'babel-loader',
                        query:{
                            presets:['es2015','react']
                        }
                    },
                ],
            },
        }))
        .pipe(concat('index.js'))
        .pipe(uglify())
        .pipe(rename('index.min.js'))
        .pipe(gulp.dest(build+'js/react'));
});
gulp.task('img',function(){
    gulp.src(src+'img/*.*')
        .pipe(imagemin())
        .pipe(gulp.dest(build+'img/'));
})
gulp.task('default',['concat','js','img'],function(){
    gulp.watch(src+'css/*.css',['concat']);
    gulp.watch(src+'sass/*.sass',['concat']);
    gulp.watch(src+'js/react/*/*.js',['js']);
    gulp.watch(src+'img/*.*',['img']);
});