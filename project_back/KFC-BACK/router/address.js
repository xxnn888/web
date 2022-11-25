const express = require('express')
const router = express.Router()
const router_hander = require('../router_hander/address')

router.post('/all', router_hander.getAllInfo)

router.get('/all2', router_hander.getAllInfo2)

router.post('/student', router_hander.getAllInfoStudent)
module.exports = router