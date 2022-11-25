const express = require('express')
// 创建路由对象
const router = express.Router()
//导入路由处理函数
const user_handler = require('../router_handler/user')
//导入表单验证模块
const expressJoi = require('@escook/express-joi')
const {reg_login_schema} = require('../schema/user')
// 注册新用户
router.post('/reguser', expressJoi(reg_login_schema), user_handler.regUser)
// 登录
router.post('/login', expressJoi(reg_login_schema), user_handler.login)
// 将路由对象共享出去
module.exports = router