const db = require('../db/index')
const bcrypt = require('bcryptjs')
const jwt = require('jsonwebtoken')
const config = require('../config')
exports.regUser = (req, res) => {
    const userinfo = req.body
    const sql = `select * from ev_users where username=?`
    db.query(sql, [userinfo.username], function (err, results) {
        if (err) {
            // return res.send({status: 1, message: err.message})
            return res.cc(err)
        }
        if (results.length > 0) {
            // return res.send({status: 1, message: '用户名被占用，请更换其他用户名！'})
            return res.cc('用户名被占用，请更换其他用户名！')
        }
        // TODO: 用户名可用，继续后续流程...
        userinfo.password = bcrypt.hashSync(userinfo.password, 10)
        const sql = 'insert into ev_users set ?'
        db.query(sql, {username: userinfo.username, password: userinfo.password}, (err, result) => {
            if (err)
                // return res.send({status: 1, message: err.message})
                return res.cc(err)
            if (result.affectedRows !== 1)
                // return res.send({status: 1, message: '注册用户失败，请稍后再试！'})
                return res.cc('注册用户失败，请稍后再试！')
            // res.send({status: 0, message: '注册成功！'})
            res.cc('注册成功！', 0)
        })
    })
}

exports.login = (req, res) => {
    console.log("u")
    const userinfo = req.body

    const sql = `select * from ev_users where username=?`

    db.query(sql, userinfo.username, (err, result) => {
        if (err) return res.cc(err)

        if (result.length !== 1) return res.cc('登录失败！')

        const compareResult = bcrypt.compareSync(userinfo.password, result[0].password)
        if (!compareResult) return res.cc('登录失败！')
        const user = {...result[0], password: '', user_pic: ''}
        const tokenStr = jwt.sign(user, config.jwtSecretKey, {expiresIn: config.expiresIn})
        res.send({status: 0, message: "登陆成功", token: 'Bearer ' + tokenStr})
    })
}