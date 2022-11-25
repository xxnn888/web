var mongoose = require("mongoose")

var userschema = new mongoose.Schema({
    user: 'string',
    pwd: 'string'
});
var usermodel = mongoose.model('usermodel', userschema);

module.exports = usermodel