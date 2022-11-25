const {defineConfig} = require('@vue/cli-service')
module.exports = defineConfig({
    transpileDependencies: true,
    lintOnSave: false,
})
module.exports = {
    devServer: {
        proxy: {
            "/api": {
                target: 'http://zhinengwangluo.natapp1.cc',
                changeOrigin: true,
                pathRewrite: {
                    '^/api': ''
                },
            }
        },
    },
}
