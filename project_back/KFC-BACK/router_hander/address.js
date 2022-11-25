// 导入数据库操作模块
const db = require('../db/index')
exports.getAllInfo = (req, res) => {
    const page1 = Number(req.body.page)
    const page = (page1 - 1) * req.body.limit
    const limit = Number(req.body.limit)
    const sql = `select *from kfc_name limit ${page},${limit}`
    db.query(sql, (err, results) => {
        if (err) {
            return res.send(err.message)
        }
        console.log(results.length)
        res.send({
            status: 0,
            message: '成功！',
            data: results,
        })
    })
}
exports.getAllInfo2 = (req, res) => {
    const sql = `select *from kfc_name2`
    db.query(sql, (err, results) => {
        // 1. 执行 SQL 语句失败  (PAGE-1)*SIZE, SIZE
        if (err) return res.send(err.message)
        // 2. 执行 SQL 语句成功
        console.log(results.length)
        res.send({
            status: 0,
            message: '成功！',
            data: results,
        })
    })
}
exports.getAllInfoStudent = (req, res) => {
    const page1 = Number(req.body.page)
    const page = (page1 - 1) * req.body.limit
    const limit = Number(req.body.limit)
    const sql = `select *from senior_student limit ${page},${limit}`
    db.query(sql, (err, results) => {
        if (err) {
            return res.send(err.message)
        }
        console.log(results.length)
        res.send({
            status: 0,
            message: '成功！',
            data: results,
        })
    })
}