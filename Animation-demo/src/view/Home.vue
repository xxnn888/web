<template>
  <div class="body">
    <div class="left">
      <div class="left-up">
        <img src="../assets/耐克.png" alt="logo" width="70px;" height="50px">
        <div class="left-up-item">
          <div @click="q"><i class="el-icon-s-home"></i>首页</div>
          <div @click="q"><i class="el-icon-s-help"></i>社区</div>
          <div @click="q"><i class="el-icon-shopping-cart-full"></i>购物车</div>
          <div @click="q"><i class="el-icon-chat-line-square"></i>我的订单</div>
          <div @click="q"><i class="el-icon-star-on"></i>我的收藏</div>
        </div>
      </div>
      <hr>
      <div class="left-bottom">
        <hr>
      </div>
    </div>
    <div class="right" :style="flexStart">
      <transition-group name="animate__animated animate__bounce animate__faster"
                        class="animate"
                        appear
                        enter-active-class="animate__backInLeft"
                        leave-active-class="animate__backOutUp">
        <div v-show="show" key="1" class="right-up">
          <i class="el-icon-zoom-in" style="font-size: 20px;font-weight: bold" @click="showF"></i>
          <i class="el-icon-shopping-cart-full" style="font-size: 20px;font-weight: bold"></i>
          <i class="el-icon-s-fold" style="font-size: 20px;font-weight: bold"></i>
        </div>
        <div v-show="!show" :key="2" class="right-up">
          <input v-model="search" type="text" class="input" required="" ref="input" @blur="showT"
                 @keydown.enter="SearchInfo">
        </div>
      </transition-group>
      <div class="right-bottom" v-show="!showS">
        <div class="right-bottom-item">
          <div class="right-bottom-list1">
            <div style="font-weight: bold">Air Max</div>
            <div style="font-size: 35px;font-weight: bold;font-family: 华文隶书,serif">270</div>
            <div class="a" @click="AddTOCollection">S 199 <i :class="icon"></i></div>
          </div>
          <i class="el-icon-caret-top position"></i>
          <div class="right-bottom-list2">
            <div>www</div>
            <div>www</div>
            <div>www</div>
          </div>
        </div>
        <div class="right-bottom-botm">
          <i class="el-icon-share" style="font-size: 20px;font-weight: bold;margin-left: 320px;"></i>
        </div>
      </div>
      <div class="SearchInfo" v-show="showS">
        <i class="el-icon-caret-right position2" @click="Receive"></i>
        Search
      </div>
    </div>
  </div>
</template>

<script>
import "animate.css"

export default {
  name: "MyHome",
  data() {
    return {
      search: "",
      show: true,
      showS: false,
      flexStart: "",
      icon: "el-icon-star-off"
    }
  },
  methods: {
    AddTOCollection() {
      this.icon = "el-icon-star-on"
    },
    q() {
      this.$router.push("Test/Edit")
    },
    Receive() {
      this.showS = false;
      this.flexStart = "justify-content: space-between;"
    },
    SearchInfo() {
      this.showS = true;
      this.flexStart = "justify-content: flex-start;"
    },
    showF() {
      this.show = false;
    },
    showT() {
      if (this.show === false) return;
      this.show = true;
      if (this.show === true) {
        return
      }
      this.flexStart = ""
    }
  },
  watch: {
    show(newVal, oldVal) {
      if (newVal === false) {
        this.$nextTick(() => {
          this.$refs.input.focus()
          console.log(oldVal)
        })
      }
    }
  }
}
</script>

<style scoped>
input {
  line-height: 28px;
  border: 2px solid transparent;
  border-bottom-color: #777;
  padding: .2rem 0;
  outline: none;
  background-color: transparent;
  color: #0d0c22;
  transition: .3s cubic-bezier(0.645, 0.045, 0.355, 1);
}

.input:focus, input:hover {
  outline: none;
  padding: .2rem 1rem;
  border-radius: 1rem;
  border-color: #7a9cc6;
}

.input::placeholder {
  color: #777;
}

.input:focus::placeholder {
  opacity: 0;
  transition: opacity .3s;
}

.body {
  width: 1200px;
  height: 600px;
  align-items: center;
  margin: 0 auto;
  /*border: 1px black solid;*/
  /*background-color: red;*/
  display: flex;
  justify-content: left;
}

.body .left {
  width: 850px;
  height: 100%;
  /*background-image: url("../assets/1666150981-actbanner.jpg");*/
  background-repeat: repeat-y;
  background-position: -413px -37px;
  /*background-color: #5082dc;*/
}

.body .right {
  width: 350px;
  height: 100%;
  /*background-image: linear-gradient(0deg, #88d3ce 0%, #6e45e2 100%)*/
}

.body .left .left-up {
  display: flex;
  justify-content: left;
  margin-top: 40px;
  border-right: black solid 1px;
}

.left-up-item {
  width: 100%;
  text-align: center;
  display: flex;
  justify-content: space-around;
}

.left-up-item div {
  font-family: 华文中宋, fantasy;
  /*width: 70px;*/
  height: 67px;
  line-height: 67px;
  margin-right: 10px;
}

.left-up-item div:hover {
  border-bottom: white 1px solid;
  cursor: pointer;
}

.left-bottom {
  height: 80%;
  display: flex;
  flex-direction: column-reverse;
  justify-content: flex-start;
}

.left-bottom hr {
  background-color: black;
  border-radius: 20px;
  height: 10px;
  width: 50%;
  margin-left: 300px;
}

.left-bottom hr:hover {
  background-color: white;
}

.animate {
  display: flex;
}

.right {
  display: flex;
  flex-direction: column;
  justify-content: space-between;
}

.right-up {
  width: 100%;
  margin-left: 50px;
  margin-top: 50px;
  height: 30px;
  display: flex;
  justify-content: center;
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

/*!*动画效果*!*/


/*.search-enter {*/
/*  transform: translateX(-100%);*/
/*}*/

/*.search-enter-to {*/
/*  transform: translateX(0);*/
/*}*/

/*.search-enter-active, .search-leave-active {*/
/*  transition: 0.5s linear;*/
/*}*/

/*.search-leave {*/
/*  transform: translateX(0);*/
/*}*/

/*.search-leave-to {*/
/*  transform: translateX(-100%);*/
/*}*/

.right-bottom-item {
  /*margin-top: 228px;*/
  /*background-color: #5082dc;*/
  position: relative;
  width: 350px;
  height: 210px;
  display: flex;
  justify-content: space-between;
}

.right-bottom-list1 {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  width: 169px;
  height: 128px;
  border-radius: 10px;
  margin-left: 20px;
  box-shadow: 10px 10px 5px rgba(100, 100, 100, .5);
  font-family: "Sans GB", serif
}

.position {
  font-size: 20px;
  position: absolute;
  left: 100px;
  top: -5px;
  border-radius: 20px;
  box-shadow: #777777 -5px -5px 5px;
  background-color: #E3E3E3;
}

.position2 {
  font-size: 20px;
  position: absolute;
  left: 10px;
  top: 50px;
  border-radius: 20px;
  box-shadow: #777777 -5px -5px 5px;
  background-color: #E3E3E3;
}

.position:hover {
  cursor: pointer;
  background-color: #552525;
}

.position2:hover {
  cursor: pointer;
  background-color: #552525;
}

.right-bottom-list1 .a:hover {
  cursor: pointer;
}

.right-bottom-list1 div {

}

.right-bottom-list2 {
  width: 30px;
  height: 200px;
  display: flex;
  flex-direction: column;
  margin-right: 10px;
  justify-content: space-around;
}

.right-bottom-list2 div {
  writing-mode: vertical-lr;
}

.right-bottom-list2 div:hover {
  color: white;
  cursor: pointer;
}

.right-bottom-botm i:hover {
  color: white;
  cursor: pointer;
}

.SearchInfo {
  position: relative;
}
</style>
