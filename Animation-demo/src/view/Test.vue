<template>
  <div>
    <div>
      <transition-group
          appear
          enter-active-class="animate__backInUp"
          leave-active-class="animate__backOutUp"
          name="animate__animated animate__bounce"
          class="up">
        <img src="../assets/耐克.png" alt="logo" width="70px;" height="50px" @click="Home" key="3">
        <div key="1" class="left-up-item">
          <router-link :to="{name:'Edit'}" class="div" active-class="active">首页</router-link>
          <router-link to="/Test/Info" class="div" active-class="active">社区</router-link>
          <router-link to="/Test/Info1" class="div" active-class="active">购物车</router-link>
          <router-link to="/Test/Info2" class="div" active-class="active">我的订单</router-link>
          <router-link to="/Test/Info3" class="div" active-class="active">我的收藏</router-link>
        </div>
        <div key="2" class="right-up">
          <i class="el-icon-user-solid" style="font-size: 20px;font-weight: bold;" @click="DetailInfo"></i>
          <i class="el-icon-shopping-cart-full" style="font-size: 20px;font-weight: bold"></i>
          <i class="el-icon-s-fold" style="font-size: 20px;font-weight: bold"></i>
        </div>
      </transition-group>
    </div>
    <div style="width: 1100px;align-items: center;margin: 30px auto">
      <transition
          appear
          enter-active-class="animate__flipInX"
          leave-active-class="animate__backOutUp"
          name="animate__animated animate__bounce">
        <router-view/>
      </transition>
    </div>
  </div>
</template>

<script>

export default {
  name: "MyTest",
  methods: {
    DetailInfo() {
      if (!window.sessionStorage.getItem("Info")) {
        this.$router.push('LoginReg')
      } else {
        this.$router.push({
          name: "PersonMessage",
          query: {
            id: 1,
            title: "message"
          }
        })
      }
    },
    Home() {
      this.$router.replace("/")
    }
  }
}
</script>

<style scoped>
.up {
  height: 50px;
  display: flex;
  justify-content: space-around;
  /*background-color: palegreen;*/
}

.left-up-item {
  width: 100%;
  text-align: center;
  display: flex;
  justify-content: center;
}

a {
  text-decoration: none;
}

.active {
  border-radius: 50px;
  border-bottom: #0d0c22 2px solid;
}

.left-up-item .div {
  font-family: 华文中宋, fantasy;
  width: 70px;
  height: 50px;
  color: black;
  line-height: 50px;
  margin-right: 10px;
}

.left-up-item div:hover {
  border-bottom: deeppink 1px solid;
  cursor: pointer;
}

.right-up {
  width: 100%;
  height: 50px;
  line-height: 50px;
  display: flex;
  justify-content: space-around;
  align-items: center;
}


.right-up i {
  margin: 0 auto;
  align-items: center;
}

.right-up i:hover {
  color: white;
  cursor: pointer;
}

</style>
