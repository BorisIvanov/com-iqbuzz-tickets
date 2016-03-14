var
    gulp = require("gulp"),
    uglify = require("gulp-uglify"),
    concat = require("gulp-concat"),
    minifyCSS = require("gulp-minify-css");

gulp.task("default", function () {

    gulp.src([
            "src/main/webapp/resources/bootstrap-3.3.6/css/bootstrap.min.css",
            "src/main/webapp/resources/css/main.css"
        ])
        .pipe(minifyCSS())
        .pipe(concat("main.min.css"))
        .pipe(gulp.dest("src/main/webapp/resources/css"));

    gulp.src([
            "src/main/webapp/resources/js/lib/jquery-2.2.1.min.js",
            "src/main/webapp/resources/js/lib/handlebars-v4.0.5.js",
            "src/main/webapp/resources/bootstrap-3.3.6/js/bootstrap.min.js",
            "src/main/webapp/resources/js/app/main.js"
        ])
        .pipe(uglify())
        .pipe(concat("main.min.js"))
        .pipe(gulp.dest("src/main/webapp/resources/js"));
});