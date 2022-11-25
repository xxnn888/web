<template>
  <div class="Header">
    <div>
      <div style="margin-top: 4px" class="avatar" @click="UserInfoDetail">
        <el-dropdown @command="handleCommand">
          <el-avatar><img :src="ImgSrc"></el-avatar>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item command="a">详细信息</el-dropdown-item>
            <el-dropdown-item command="b">狮子头</el-dropdown-item>
            <el-dropdown-item command="c">螺蛳粉</el-dropdown-item>
            <el-dropdown-item command="d" disabled>双皮奶</el-dropdown-item>
            <el-dropdown-item command="e" divided>退出登录</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
      </div>
    </div>
    <div @click="Login" style="cursor: pointer">登录</div>
    <div @click="Registered" style="cursor: pointer">注册</div>
    <div @click="MyOrder" style="cursor: pointer">我的订单</div>
    <div @click="MyCollection" style="cursor: pointer">我的收藏</div>
    <div @click="MyShoppingCart" style="cursor: pointer">购物车</div>
  </div>
</template>

<script>
import pubsub from "pubsub-js"

export default {
  name: "MyHeader",
  data() {
    return {
      name: "",
      ImgSrc: "",
      Infomation: JSON.parse(window.sessionStorage.getItem("Info"))
    }
  },
  computed: {},
  mounted() {
    if (!JSON.parse(window.sessionStorage.getItem("Info"))) {
      this.ImgSrc = "http://rk56pj32h.hb-bkt.clouddn.com/e-commerce/KHCFDC_%E5%A4%B4%E5%83%8F%20.png"
    } else {
      this.ImgSrc = JSON.parse(window.sessionStorage.getItem("Info")).avatar
    }
    this.pubId = pubsub.subscribe("GetName", (msgName, data) => {
      this.name = data.username;
      this.ImgSrc = data.avatar
    })
  },
  methods: {
    handleCommand(command) {
      if (command === "e") {
        window.sessionStorage.clear()
        this.$router.go(0)
      }
    },
    UserInfoDetail() {
      this.$notify({
        title: '成功',
        message: '这是一条成功的提示消息',
        type: 'success'
      });
    },
    Login() {
      if (window.sessionStorage.getItem('Info') == null)
        this.$store.commit("setShowLogin", true)
      else {
        this.$notify.error({
          title: '警告',
          message: '你已登录，无需重新登录',
          offset: 100
        });
      }
    },
    Registered() {
      this.$store.commit("setShowRegister", true)
    },
    MyOrder() {
      if (!this.Infomation) {
        this.$notify.error({
          title: '错误',
          message: '尚未登录，请先登录',
          offset: 100
        });
      } else {
        this.$router.push("Order")
      }
    },
    MyCollection() {
      if (!this.Infomation) {
        this.$notify.error({
          title: '错误',
          message: '尚未登录，请先登录',
          offset: 100
        });
      } else {
        this.$router.push("Collection")
      }
    },
    MyShoppingCart() {
      if (!this.Infomation) {
        this.$notify.error({
          title: '错误',
          message: '尚未登录，请先登录',
          offset: 100
        });
      } else {
        this.$router.push("ShoppingCart")
      }
    },
    showMessage() {

    }
  },
  beforeDestroy() {
    pubsub.unsubscribe(this.pubId)
  }
}
</script>

<style scoped>
.Header {
  background-color: #272643;
  width: 100%;
  height: 50px;
  line-height: 50px;
  color: aliceblue;
  display: flex;
  flex-direction: row;
  justify-content: flex-end;
}

.Header div {
  margin-right: 10px;
  width: 80px;
}

.avatar {
  cursor: pointer;
}
</style>
