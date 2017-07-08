const gulp = require('gulp');
const sass = require('gulp-sass');
const concat=require('gulp-concat');
const cleanCss=require('gulp-clean-css');
const del=require('del');
const babel = require('gulp-babel');
const uglify=require('gulp-uglify');
const rename=require('gulp-rename');
const imagemin=require('gulp-imagemin');
const webpack = require('gulp-webpack');
const fs = require('fs');
const path = require('path');
const merge = require('merge-stream');
const named=require('vinyl-named');
const build='static/build/js/react/main/'
const src='static/src/js/react/main/';
var HtmlWebpackPlugin = require('html-webpack-plugin');
function getFolders(dir) {
    return fs.readdirSync(dir)
        .filter(function(file) {
            return fs.statSync(path.join(dir, file)).isDirectory();
        });
}
gulp.task('scripts', function() {
    var folders = getFolders(src);

    var tasks = folders.map(function(folder) {
        // 拼接成 foldername.js
        // 写入输出
        // 压缩
        // 重命名为 folder.min.js
        // 再一次写入输出
        return gulp.src(path.join(src, folder, '/*.js'))
            .pipe(named())
            .pipe(webpack({
                output:{
                    path:__dirname+"/static/build/",
                    filename:"[name].min.js",
                },
                module:{
                    loaders:[
                        {
                            test:/\.css$/,
                            loader:'style-loader!css-loader'
                        },
                        {
                            test: /\.scss|.sass$/,
                            loader: "style-loader!css-loader!sass-loader" // creates style nodes from JS strings

                        },
                        {
                            test:/\.js|.jsx$/,
                            exclude:/node-modules/,
                            loader:'babel-loader',
                            query:{
                                presets:['es2015','react']
                            }
                        },
                        {
                            test:/\.png|.jpg|.jpeg$/,
                            loader:'file-loader?limit=5000&name=img/[hash:8].[name].[ext]',
                        }
                    ]
                }
            }))
             .pipe(uglify())
            .pipe(rename(folder + '.min.js'))
            .pipe(gulp.dest(build+'/'+folder+'/'));
    });

    return merge(tasks);
});
// gulp.task('sass',function(){
//     console.log('sass');
//     gulp.src(src+'sass/**/*.sass')
//         .pipe(sass())
//         .pipe(gulp.dest(src+'css'));
// });
// gulp.task('concat',['sass'],function(){
//     console.log('concat');
//     gulp.src(src+'css/**/*.css')
//          .pipe(concat('index.min.css'))
//         .pipe(cleanCss())
//         .pipe(gulp.dest(build+'css'))
// })
// gulp.task('del',function(){
//     del([
//         src+ 'css/bg.css'
//     ]);
// })
gulp.task('js',function(){
    gulp.src(src+'js/react/main/**/*.js')
        .pipe(named())
        .pipe(webpack({
            output: {
                filename: '[name].min.js',
            },
            watch: false,
            module: {
                loaders: [
                    {
                        test:/\.js$/,
                        exclude:/node-modules/,
                        loader:'babel-loader',
                        query:{
                            presets:['es2015','react','stage-0']
                        }
                    },
                ],
            },
        }))
        // .pipe(concat('list.js'))
       .pipe(uglify())
       // .pipe(rename('index.min.js'))
        .pipe(gulp.dest(build+'js/react/main'));
});
gulp.task('img',function(){
    gulp.src(build+'img/*.*')
        .pipe(imagemin())
        .pipe(gulp.dest(build+'img/'));
})
gulp.task('default',['js','img'],function(){
    // gulp.watch(src+'css/*.css',['concat']);
    // gulp.watch(src+'sass/*.sass',['concat']);
    // gulp.watch(src+'js/react/*/*.js',['js']);
    // gulp.watch(src+'img/*.*',['img']);
});