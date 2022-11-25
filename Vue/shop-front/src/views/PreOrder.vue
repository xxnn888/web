<template>
  <div style="margin-top: 10px;height: 600px">
    <div style="background-color: white; padding: 10px">
      <!--        商品确认-->
      <el-table :data="carts" stripe style="width: 100%" :show-header="false">
        <el-table-column label="商品图片" width="150">
          <template slot-scope="scope">
            <el-image :src="scope.row.goods.img" style="width: 100px; height: 100px;" fit="contain"></el-image>
          </template>
        </el-table-column>
        <el-table-column prop="goods.name" label="商品名称"></el-table-column>
        <el-table-column label="价格">
          <template slot-scope="scope">
            <span v-html="scope.row.goods.realPrice + ' X ' + scope.row.count "></span>
          </template>
        </el-table-column>

      </el-table>

      <div style="margin-top: 10px">
        <div style="background-color: white; padding: 10px">
          <div style="color: red; text-align: right">
            <div>
              <span>总价：</span>
              <span>￥ {{ totalPrice }}</span>
            </div>
            <div style="text-align: right; color: #999; font-size: 12px; padding: 5px 0">
              运费： ￥ 0
            </div>
            <div style="text-align: right; color: #999; font-size: 12px;">
              优惠： - ￥{{ discount }}
            </div>
            <div style="padding: 10px 0">
              <el-button style="background-color: red; color: white; width: 100px" @click="submitOrder">提交订单</el-button>
            </div>
          </div>
        </div>
      </div>

    </div>
  </div>
</template>

<script>
import API from "@/utils/request";

export default {
  name: "MyCart",
  data() {
    return {
      chooseAddress: {
        linkUser: "xxx",
        linkPhone: "18182976697",
        linkAddress: "江西南昌"
      },
      user: {},
      addressData: [],
      carts: [],
      totalPrice: 0,
      discount: 0
    }
  },
  created() {
    this.user = sessionStorage.getItem("user") ? JSON.parse(sessionStorage.getItem("user")) : {}
    if (!this.user) {
      this.$message({
        type: "warning",
        message: '请登录！'
      })
      return
    }
    this.load()

  },
  methods: {
    submitOrder() {
      // 提交订单
      API.post("/api/order", {
        totalPrice: this.totalPrice,
        linkUser: this.chooseAddress.linkUser,
        linkPhone: this.chooseAddress.linkPhone,
        linkAddress: this.chooseAddress.linkAddress,
        carts: JSON.stringify(this.carts),
        type: this.$store.state.type
      }).then(res => {
        if (res.code === '0') {
          this.$message({
            type: 'success',
            message: '提交成功！'
          })
          this.$router.replace("/order")
        } else {
          this.$message({
            type: 'error',
            message: res.msg
          })
        }
      })
    },
    load() {
      this.carts = this.$store.state.carts
      console.log(this.carts, "AAA")
      API.post("/api/order/pre", {carts: JSON.stringify(this.carts)}).then(res => {
        this.carts = res.data.list
        this.totalPrice = res.data.totalPrice
        this.discount = res.data.discount


      })
      // 获取收货地址
      API.get("/api/address").then(res => {
        this.addressData = res.data
      })
    },
  }
}
</script>

<style scoped>

</style>
