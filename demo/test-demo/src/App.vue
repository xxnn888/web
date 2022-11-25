<template>
  <div id="app">
    <img alt="Vue logo" src="./assets/logo.png">
    <div>
      <el-input-number v-model="num" :min="1" :max="10" label="购买数量" style="width: 140px"></el-input-number>
    </div>
    <div>
      <!--      <MyWater></MyWater>-->
    </div>
  </div>
</template>

<script>

import axios from "axios";

export default {
  name: 'App',

  data() {
    return {
      num: 2
    }
  },
  mounted() {
    axios.get("http://127.0.0.1:5000/auth/user").then((resp) => {
      console.log(resp.data.data)
    })
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
