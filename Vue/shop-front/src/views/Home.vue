<template>
  <div>
    <div style="display: flex;justify-content: space-between">
      <img :src="url" alt="" width="300px" height="75px">
      <el-menu
          :default-active="activeIndex2"
          class="el-menu-demo"
          mode="horizontal"
          @select="handleSelect"
          text-color="black"
          active-text-color="#ffd04b">
        <el-menu-item index="1">首页</el-menu-item>
        <el-menu-item index="2">分类</el-menu-item>
        <el-menu-item index="3">社区</el-menu-item>
        <el-menu-item index="4">关于</el-menu-item>
      </el-menu>
    </div>
    <div v-show="keys==1">
      <Shop></Shop>
    </div>
    <div v-show="keys==2">
      <classification></classification>
    </div>
    <div v-show="keys==3">
      <community></community>
    </div>
    <div v-show="keys==4">
      <about></about>
    </div>
  </div>

</template>

<script>
import Shop from "@/components/Home page components/Shop";
import classification from "@/components/Home page components/classification";
import community from "@/components/Home page components/community";
import about from "@/components/Home page components/about";

export default {
  name: "MyHome",
  components: {Shop, classification, community, about},
  data() {
    return {
      activeIndex2: '1',
      keys: "1",
      url: "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg.yzcdn.cn%2Fupload_" +
          "files%2F2017%2F07%2F06%2FFgaBa9toyWQ9kf6ZEe3Nhy8QaTxR.jpg&refer=http%3A%2F%2F" +
          "img.yzcdn.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1671770568&t" +
          "=7698cd5573ee1e1c14da1fffbdfe730a"
    };
  },
  methods: {
    handleSelect(key) {
      this.keys = key
    },
  }
}
</script>

<style scoped>
.el-menu-demo {
  margin-right: 400px;
  display: flex;
  flex-direction: row;
  justify-content: center;
}
</style>