const express = require('express')
// 创建路由对象
const router = express.Router()
// 导入用户信息的处理函数模块
const userinfo_handler = require('../router_handler/userinfo')
//导入表单验证模块
const expressJoi = require("@escook/express-joi");
const {update_userinfo_schema, update_password_schema, update_avatar_schema} = require('../schema/user')

//get请求
router.get('/userinfo', userinfo_handler.getUserinfo)
//post请求
router.post('/userinfo', expressJoi(update_userinfo_schema), userinfo_handler.updateUserinfo)
// 重置密码的路由
router.post('/updatepwd', expressJoi(update_password_schema), userinfo_handler.updatePassword)
// 更新用户头像的路由
router.post('/update/avatar', expressJoi(update_avatar_schema), userinfo_handler.updateAvatar)
// 将路由对象共享出去
module.exports = router