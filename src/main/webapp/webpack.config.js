var webpack = require("webpack");
const glob = require('glob');
var HtmlWebpackPlugin = require('html-webpack-plugin');
var config = {
    entry: {
        common: ['react', 'react-dom']
    },
    output: {
        path: __dirname + '/static/dist/js/',
        filename: '[name].min.js'
    },
    module: {
        loaders: [
            {
                test:/\.css$/,
                loader:'style-loader!css-loader'
            },
            {
                test: /\.scss$/,
                loader: "style-loader!css-loader!sass-loader" // creates style nodes from JS strings

            },
            {
            test: /\.js|.jsx$/,
            exclude: /node_modules/,
            loader: 'babel-loader',
            query: {
                presets: ['es2015', 'stage-0', 'react']
            }
        }],
    },
    plugins: [
        new webpack.optimize.UglifyJsPlugin({
            compress: {
                warnings: false
            }
        }),
        new webpack.optimize.CommonsChunkPlugin({name:'common', filename:'../../common/base.js'}),
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
var plugins=new Array()
var newPlugins = files.reduce(function(memo, file) {
    var name = /.*\/(.*?)\/index\.js/.exec(file)[1];
    plugins[plugins.length]=new HtmlWebpackPlugin({
        chunks: [name,'common'],
        filename: "../../../WEB-INF/templates/web/home/"+name+'/index.html'
    });
    return plugins;
}, {});
config.entry = Object.assign({}, config.entry, newEntries);
config.plugins=config.plugins.concat(newPlugins);
/**
 * [entry description]
 * @param  {[type]} name [description]
 * @return {[type]}      [description]
 */
function entry(name) {
    return './static/src/js/home/' + name + '/index.js';
}
module.exports = config;