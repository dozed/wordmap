'use strict';

var webpack = require('webpack');

module.exports = {
  devServer: {
    //host: "0.0.0.0",
    //port: 3000,
    //contentBase: 'src/www',
    historyApiFallback: true,
    //hot: true        // live-reload
  },
  entry: [
    'webpack/hot/only-dev-server',
    './bundles/index.js'
  ],
  output: {
    path: __dirname + '/build',
    publicPath: __dirname  + "/build/",
    filename: 'bundle.js'
  },
  plugins: [
    new webpack.HotModuleReplacementPlugin(),
    new webpack.NoErrorsPlugin()
  ]


};