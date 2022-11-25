//1.导入express
const express = require('express')
//2.创建web服务器
const app = express()
const cors = require('cors')
app.use(cors())
const router = require('./router/address')
app.use(express.json())
app.use(express.urlencoded({extended: false}))
app.use('/api', router)
//3.启动web服务器
app.listen(80, () => {
    console.log('express server running at http://127.0.0.1')
})