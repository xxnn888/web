<template>
  <div>
    <div style="display: flex;justify-content: center">
      <div>
        <img :src="goods.img" style="width: 400px;height: 400px">
      </div>
      <div>
        <el-card style="display: flex;justify-content: left">
          <div style="text-align: right">
            <div style="display: inline-block; cursor: pointer;" @click="collect">
              <img :src="collectActiveIcon" alt="收藏" style="width: 15px; ">
              <span style="position: relative; bottom: 3px; font-size: 12px; left: 5px; color: #666">收藏</span>
            </div>
            <div style="display: inline-block; cursor: pointer; margin-left: 20px" @click="praise">
              <img :src="praiseActiveIcon" alt="点赞" style="width: 15px; ">
              <span style="position: relative; bottom: 2px; color: #666; margin-left: 5px">{{ goods.praise }}</span>
            </div>
          </div>
          <div style="padding: 10px 0; font-size: 20px; font-weight: bold">{{ goods.name }}</div>
          <div style="padding: 10px 0; color: #666; font-size: 14px">{{ goods.description }}</div>
          <div style="padding: 10px 0; color: #999;">
            <span>上架时间</span>
            <span style="margin-left: 20px; color: #666">{{ goods.createTime }}</span>
          </div>
          <div style="padding: 10px 0">
            <span style="color: #999">抢购价</span>
            <span style="margin-left: 10px; color: orangered; font-size: 20px">￥ {{ goods.realPrice }}</span>
            <span style="color: orangered; margin-left: 10px"
                  v-if="goods.discount < 1"> ({{ (goods.discount * 10).toFixed(1) }}折)</span>
          </div>

          <div v-if="goods.discount < 1" style="padding: 10px 0; text-decoration:line-through; color: #999">
            原价：{{ goods.price }}
          </div>
          <div style="padding: 10px 0; color: #999">
            库存：{{ goods.store }}
          </div>

          <div style="padding: 10px 0; color: #999;">
            <span>服务</span>
            <span style="margin-left: 20px; color: #666">
                由“电商平台”发货，并提供售后服务。 23:55前完成下单，预计明天可送达
              </span>
          </div>
          <div style="margin-top: 20px; padding: 10px 0">
            <el-input-number v-model="num" :min="1" :max="10" label="购买数量" style="width: 120px"></el-input-number>
            <el-button style="margin-left: 10px; background-color: orangered; color: white; position: relative"
                       @click="addCart">
              <img :src="cartIcon" style="width: 20px; position: absolute; left: 8px; top: 5px">
              <span style="margin-left: 20px">加入购物车</span>
            </el-button>
            <el-button style="margin-left: 10px; background-color: orangered; color: white" @click="buyNow">立即购买
            </el-button>
          </div>

        </el-card>
      </div>
    </div>
    <div style="margin-top: 10px; margin-bottom: 80px">
      <el-card>
        <!--              <div style="padding: 20px; color: #888">-->
        <!--                <div>-->
        <!--                  <el-input type="textarea" :rows="3" v-model="entity.content"></el-input>-->
        <!--                  <div style="text-align: right; padding: 10px"><el-button type="primary" @click="save">评论</el-button></div>-->
        <!--                </div>-->
        <!--              </div>-->

        <div
            style="padding-bottom: 10px; margin-bottom: 20px; border-bottom: 2px solid orangered; font-size: 20px">
          商品评论
        </div>
        <div tyle="display: flex; padding: 20px" v-for="item in messages" :key="item.id">
          <div style="text-align: center; flex: 1">
            <el-image :src="item.avatar" style="width: 60px; height: 60px; border-radius: 50%"></el-image>
          </div>
          <div style="padding: 0 10px; flex: 5">
            <div><b style="font-size: 14px">{{ item.username }}</b></div>
            <div style="padding: 10px 0; color: #888">
              {{ item.content }}
              <el-button type="text" size="mini" @click="del(item.id)" v-if="item.username === user.username">删除
              </el-button>
            </div>
            <div style="background-color: #eee; padding: 10px" v-if="item.parentMessage">{{
                item.username
              }}：{{ item.parentMessage.content }}
            </div>
            <div style="color: #888; font-size: 12px">
              <span>{{ item.time }}</span>
              <!--                    <el-button type="text" style="margin-left: 20px" @click="reReply(item.id)">回复</el-button>-->
            </div>
          </div>
        </div>

        <el-dialog title="回复信息" :visible.sync="dialogFormVisible" width="30%"
                   :close-on-click-modal="false">
          <el-form :model="entity">
            <el-form-item label="内容" label-width="100px">
              <el-input v-model="entity.reply" autocomplete="off" type="textarea" :rows="3"></el-input>
            </el-form-item>
          </el-form>
          <div slot="footer" class="dialog-footer">
            <el-button @click="cancel">取 消</el-button>
            <el-button type="primary" @click="reply">确 定</el-button>
          </div>
        </el-dialog>
      </el-card>
    </div>
  </div>
</template>

<script>
import API from "@/utils/request";

export default {
  name: "MyGoods",
  data() {
    return {
      messages: [],
      dialogFormVisible: false,
      entity: {},
      address: '',
      mainImg: '',
      cartIcon: require('../../src/assets/购物车-23.png'),
      praiseActiveIcon: require("../../src/assets/点赞-激活.png"),
      collectActiveIcon: require("../../src/assets/收藏-激活.png"),
      num: 1,
      id: 1,
      user: {},
      goods: [],
      praiseFlag: false
    }
  },
  created() {
    this.user = sessionStorage.getItem("Info") ? JSON.parse(sessionStorage.getItem("Info")) : {}
    this.id = this.$route.query.id
    this.load()
  },
  methods: {
    collect() {
      API.post("/api/collect", {
        goodsName: this.goods.name,
        goodsImg: this.goods.imgs,
        goodsId: this.goods.id,
        userId: this.user.id
      }).then(res => {
        if (res.code === '0') {
          this.$message({
            message: "收藏成功",
            type: "success"
          });
        } else {
          this.$message({
            message: res.msg,
            type: "error"
          });
        }
        this.load();
      })
    },
    praise() {
      if (this.praiseFlag) {
        this.$message({
          message: "您已点过赞",
          type: "warning"
        });
        return
      }
      this.praiseFlag = true
      this.entity = JSON.parse(JSON.stringify(this.goods))
      this.entity.praise += 1
      this.entity.imgs = null
      API.put("/api/goods", this.entity).then(res => {
        if (res.code === '0') {
          this.$message({
            message: "点赞成功",
            type: "success"
          });
        } else {
          this.$message({
            message: res.msg,
            type: "error"
          });
        }
        this.load();
      })
    },
    loadMessage() {
      API.get("/api/message/foreign/" + this.goods.id).then(res => {
        this.messages = res.data;
      })
    },
    buyNow() {
      if ((this.goods.store - this.num) < 0) {
        this.$message({
          type: 'warning',
          message: '商品库存不足！'
        })
        return
      }
      let cart = []
      cart.push({count: this.num, goods: this.goods, goodsId: this.goods.id})
      this.$store.commit("setCarts", cart)
      this.$router.replace("/PreOrder")
    },
    save() {  // 新增评论
      if (!this.user.username) {
        this.$message({
          message: "请登录",
          type: "warning"
        });
        return;
      }
      if (!this.entity.content) {
        this.$message({
          message: "请填写内容",
          type: "warning"
        });
        return;
      }
      API.post("/api/message", this.entity).then(res => {
        if (res.code === '0') {
          this.$message({
            message: "评论成功",
            type: "success"
          });
        } else {
          this.$message({
            message: res.msg,
            type: "error"
          });
        }
        this.entity = {}
        this.loadMessage();
        this.dialogFormVisible = false;
      })
    },
    cancel() {
      this.dialogFormVisible = false;
      this.entity = {};
    },
    reReply(id) {
      this.dialogFormVisible = true;
      this.entity.parentId = id;
    },
    reply() {
      this.entity.content = this.entity.reply;
      this.save();
    },
    del(id) {
      API.delete("/api/message/" + id).then(res => {
        console.log(res.data)
        this.$message({
          message: "删除成功",
          type: "success"
        });
        this.loadMessage()
      })
    },
    addCart() {
      if (!this.user.id) {
        this.$message({
          type: 'warning',
          message: '请登录！'
        })
        return
      }
      if ((this.goods.store - this.num) < 0) {
        this.$message({
          type: 'warning',
          message: '商品库存不足！'
        })
        return
      }
      API.post("/api/cart", {goodsId: this.goods.id, count: this.num, userId: this.user.id}).then(res => {
        if (res.code === '0') {
          this.$message({
            type: 'success',
            message: '加入成功！'
          })
        } else {
          this.$message({
            type: 'error',
            message: res.msg
          })
        }
      })
    },
    show(img) {
      this.mainImg = img
      console.log(img)
    },
    load() {
      API.get("/api/goods/" + this.id).then(res => {
        this.goods = res.data

        this.loadMessage()

        this.mainImg = this.goods.imgs
      })
    },
  },
}
</script>

<style scoped>

</style>