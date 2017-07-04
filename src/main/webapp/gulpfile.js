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
const build='./static/build/'
const src='./static/src/';
const named=require('vinyl-named');
gulp.task('sass',function(){
    console.log('sass');
    gulp.src(src+'sass/**/*.sass')
        .pipe(sass())
        .pipe(gulp.dest(src+'css'));
});
gulp.task('concat',['sass'],function(){
    console.log('concat');
    gulp.src(src+'css/**/*.css')
         .pipe(concat('index.min.css'))
        .pipe(cleanCss())
        .pipe(gulp.dest(build+'css'))
})
gulp.task('del',function(){
    del([
        src+ 'css/bg.css'
    ]);
})
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
                            presets:['es2015','react']
                        }
                    },
                ],
            },
        }))
        // .pipe(concat('index.js'))
       .pipe(uglify())
       // .pipe(rename('index.min.js'))
        .pipe(gulp.dest(build+'js/react/main'));
});
gulp.task('img',function(){
    gulp.src(src+'img/*.*')
        .pipe(imagemin())
        .pipe(gulp.dest(build+'img/'));
})
gulp.task('default',['concat','js','img'],function(){
    // gulp.watch(src+'css/*.css',['concat']);
    // gulp.watch(src+'sass/*.sass',['concat']);
    // gulp.watch(src+'js/react/*/*.js',['js']);
    // gulp.watch(src+'img/*.*',['img']);
});