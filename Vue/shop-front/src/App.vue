<template>
  <div id="app">
    <el-container>
      <el-header style="padding: 0">
        <MyHeader></MyHeader>
      </el-header>
      <el-main style="padding: 0">
        <keep-alive>
          <router-view>
            <Home></Home>
          </router-view>
        </keep-alive>
      </el-main>
      <el-footer>
        <MyFooter></MyFooter>
      </el-footer>
    </el-container>
    <MyLogin></MyLogin>
    <MyRegister></MyRegister>
  </div>
</template>

<script>
import MyHeader from "@/components/little components/MyHeader";
import MyLogin from "@/components/little components/MyLogin";
import MyRegister from "@/components/little components/MyRegister";
import MyFooter from "@/components/little components/MyFooter";

export default {
  components: {
    MyFooter,
    MyHeader, MyLogin, MyRegister
  },
  data() {
    return {}
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
}

nav {
  padding: 30px;
}

nav a {
  font-weight: bold;
  color: #2c3e50;
}

nav a.router-link-exact-active {
  color: #42b983;
}
</style>

