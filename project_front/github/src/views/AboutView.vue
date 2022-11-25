<template>
  <div>
    <div class="about">
      <h1>This is an about GitHub detial</h1>
      <el-input v-model="searchKey"
                prefix-icon="el-icon-search"
                size="small"
                placeholder="通过用户名搜索"
                @keydown.enter.native="search"
                style="width: 400px;margin-right: 10px">
      </el-input>
      <el-button type="primary" size="small" @click="search">搜索</el-button>
    </div>
    <div class="container">
      <el-card class="admin-card" v-for="item in users" :key="item.id">
        <div slot="header" class="clearfix">
          <span>{{ item.login }}</span>
          <el-button style="float: right; padding: 3px 0" type="text">操作按钮</el-button>
        </div>
        <div>
          <a :href="item.html_url"><img :src="item.avatar_url" alt="item.login" title="item.login" class="img"></a>
        </div>
        <div>
          <div>用户名：{{ item.login }}</div>
          {{ item.url }}
        </div>
      </el-card>
    </div>
  </div>
</template>
<script>
import axios from "axios";

export default {
  name: 'aboutView',
  data() {
    return {
      searchKey: '',
      users: [],
      date: '',
    }
  },
  methods: {
    search() {
      axios.get(`https://api.github.com/search/users?q=${this.searchKey}`).then(resp => {
        console.log(resp.data.items)
        this.users = resp.data.items
        console.log(resp.data.items.url)
      }, error => {
        console.log(error.message)
      })
    }
  },

  mounted() {
    if (window.sessionStorage.getItem('list')) {
      this.users = JSON.parse(window.sessionStorage.getItem('list'));
    } else {
      axios.get('https://api.github.com/search/users?q=XUXIAO').then(resp => {
        console.log(resp.data.items)
        window.sessionStorage.setItem('list', JSON.stringify(resp.data.items))
      }, error => {
        console.log(error.message)
      })
    }

  },
  // watch: {
  //   searchKey: {
  //     handler(current) {
  //       axios.get(`https://api.github.com/search/users?q=${this.searchKey}`).then(resp => {
  //         console.log(resp.data.items)
  //         this.users = resp.data.items
  //         // this.date = resp.data.items.url;
  //         console.log(resp.data.items.url)
  //       }, error => {
  //         console.log(error.message)
  //       })
  //     }
  //   }
  // }
}
</script>
<style>
.admin-card {
  width: 350px;
  margin-bottom: 20px;
}

.container {
  display: flex;
  justify-content: space-around;
  flex-wrap: wrap;
  margin-top: 10px;
}

.img {
  width: 72px;
  height: 72px;
  border-radius: 72px;
}
</style>