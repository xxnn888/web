const joi = require('joi')

const name = joi.string().required()
const alias = joi.string().alphanum().required()
exports.add_cate_schema = ({
    body: {
        name,
        alias
    }

})

// 定义 分类Id 的校验规则
const id = joi.number().integer().min(1).required()
// 校验规则对象 - 删除分类
exports.delete_cate_schema = {
    params: {
        id,
    },
}

// 校验规则对象 - 根据 Id 获取分类
exports.get_cate_schema = {
    params: {
        id,
    },
}

// 校验规则对象 - 更新分类
exports.update_cate_schema = {
    body: {
        Id: id,
        name,
        alias,
    },
}