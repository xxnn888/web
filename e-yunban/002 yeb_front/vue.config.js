const {defineConfig} = require('@vue/cli-service')

module.exports = defineConfig({
    transpileDependencies: true
})

let proxyObj = {}

proxyObj['/'] = {
    //websocket
    ws: false,
    //目标地址
    target: 'http://localhost:8081',
    //发送请求头host会被设置target
    changeOrigin: true,
    //不重写请求地址
    PathRewrite: {
        '^/': '/'
    }

}

proxyObj['/ws'] = {
    //websocket
    ws: false,
    //目标地址
    target: 'ws://localhost:8081'
}

module.exports = {
    devServer: {
        host: 'localhost',
        port: 8080,
        proxy: proxyObj
    }
}
