var webpack=require('webpack');
var HtmlWebpackPlugin = require('html-webpack-plugin');
module.exports={
	entry:{
			app:__dirname+"/static/src/js/es6/model/model.js",
	},
	output:{
		path:__dirname+"/static/build/",
		filename:"js/es6/model/model.js",
		publicPath:'http://192.168.1.251:8080/static/pack/'
	},	
	module:{
		loaders:[
		{
			test:/\.css$/,
			loader:'style-loader!css-loader'
		},
		{
			test:/\.json$/,
			loader:'json-loader'
		},
		{
			test:/\.js$/,
			exclude:/node-modules/,
			loader:'babel-loader',
			query:{
				presets:['es2015','react','stage-0']
			}
		},
		{
			test:/\.png|.jpg|.jpeg$/,
			loader:'file-loader?limit=5000&name=img/[hash:8].[name].[ext]',
		}
		]
	},
	plugins:[
		new webpack.optimize.UglifyJsPlugin({
		compress: {
			warnings: false
			}
		}),
		 new HtmlWebpackPlugin({
			title: 'My App',
			filename: '../../WEB-INF/templates/web/es6/button.html'
		})
	]
}