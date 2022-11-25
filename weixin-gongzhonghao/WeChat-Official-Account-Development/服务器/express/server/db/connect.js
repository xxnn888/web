const mongoose = require("mongoose")

mongoose.connect("mongodb://127.0.0.1:27017/weixin", (err) => {
    if (err) {
        console.log("数据库链接失败");
    } else {
        console.log("数据库链接成功");
    }
})