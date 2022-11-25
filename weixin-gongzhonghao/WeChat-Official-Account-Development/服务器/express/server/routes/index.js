var express = require('express');
var router = express.Router();
var usermodel = require("../db/models/UserModel")
/* GET home page. */
router.get('/', function (req, res, next) {
    res.render('index', {title: 'Express'});
});
router.get("/auth", function (req, res, next) {
    res.send("welcome to www")
})
router.post('/reg', function (req, res) {
    let {user, pwd} = req.body
    new usermodel({
        user: user,
        pwd: pwd
    }).save().then(() => {
        res.send("注册成功")
    })
})
module.exports = router;
