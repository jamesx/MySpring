var gulp = require('gulp');
var less = require('gulp-less');

gulp.task('less',function(){
    gulp.src('./static/notpack/less/index.less')
        .pipe(less())
        .pipe(gulp.dest('./static/pack/css'));
});
gulp.task('concat',function(){
    gulp.src()
        .pipe()
})
gulp.task('default',['less']);
gulp.watch('./static/notpack/less/*.less',['less']);