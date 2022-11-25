<template>
  <div class="hello">
    公司信息
    <div>
      <el-input v-model="key"
                placeholder="请输入搜索关键字"
                style="width: 300px"
                size="mini"
                @keydown.enter.native="search"
                prefix-icon="el-icon-search">
      </el-input>
      <el-button @click="search"
                 type="primary"
                 size="mini"
                 icon="el-icon-search">
      </el-button>
    </div>
    <div>
      <el-button type="primary" icon="el-icon-plus" @click="showAddEmpView" size="mini">添加</el-button>
    </div>
  </div>
</template>

<script>
export default {
  name: 'HelloWorld',
  data() {
    return {
      key: '',
    }
  },
  methods: {
    search() {
      console.log("搜索中")
    },
    showAddEmpView() {
      console.log("已添加")
    }
  }
}
</script>

<style>
.hello {
  font-family: 楷体, serif;
  color: #42b983;
  font-size: large;
  font-weight: 800;
}
</style>
