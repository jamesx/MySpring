var webpack=require('webpack');
module.exports={
	entry:{
			app:__dirname+"/static/src/js/react/main/form/myForm.js",
        	react:['react','react-dom']
	},
	output:{
		path:__dirname+"/static/build/",
		filename:"js/react/main/form/myForm.js",
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
}