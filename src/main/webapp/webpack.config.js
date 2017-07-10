var webpack = require("webpack");
const glob = require('glob');
var config = {
    entry: {
        common: ['react', 'react-dom']
    },
    output: {
        path: __dirname + '/static/dist/js/',
        filename: '[name].js'
    },
    module: {
        loaders: [{
            test: /\.js|.jsx$/,
            exclude: /node_modules/,
            loader: 'babel-loader',
            query: {
                presets: ['es2015', 'stage-0', 'react']
            }
        }],
    },
    plugins: [
        new webpack.optimize.CommonsChunkPlugin({name:'common', filename:'../common/base.js'})
    ],
};
/**
 * find entries
 */
var files = glob.sync('./static/src/js/home/*/index.js');
var newEntries = files.reduce(function(memo, file) {
    var name = /.*\/(.*?)\/index\.js/.exec(file)[1];
    memo[name] = entry(name);
    return memo;
}, {});
config.entry = Object.assign({}, config.entry, newEntries);
/**
 * [entry description]
 * @param  {[type]} name [description]
 * @return {[type]}      [description]
 */
function entry(name) {
    return './static/src/js/home/' + name + '/index.js';
}
module.exports = config;