<template>
  <div>
    <el-carousel indicator-position="outside">
      <el-carousel-item v-for="item in ShufflingFigure" :key="item.id">
        <img :src="item.url" alt="" width="100%" height="300px">
      </el-carousel-item>
    </el-carousel>
    <div style="display: flex;flex-direction: row">
      <el-menu
          :default-active="activeIndex2"
          class="el-menu-demo"
          mode="horizontal"
          @select="handleSelect"
          text-color="black"
          active-text-color="#ffd04b">
        <el-menu-item index="9">潮流上装</el-menu-item>
        <el-menu-item index="10">下装</el-menu-item>
        <el-menu-item index="11">潮帽</el-menu-item>
        <el-menu-item index="12">鞋类</el-menu-item>
      </el-menu>
      <div>
        <div style="display: flex;flex-direction: row;flex-wrap: wrap;justify-content: space-between">
          <el-card class="box-card" v-for="item in tableData" :key="item.id"
                   style="width: 270px;height: 280px">
            <div class="text item" style="display: flex;flex-direction: column;justify-content: center;">
              <img :src="item.img" width="200px" height="200px" @click="goodsDetail(item.id)">
              <span style="white-space: nowrap;overflow: hidden;text-overflow: ellipsis; font-size: 12px">{{
                  item.name
                }}</span>
              <span style="padding: 5px; text-align: center; color: red; font-size: 12px">${{ item.price }}</span>
            </div>
          </el-card>
        </div>
        <div class="block">
          <el-pagination
              small
              hide-on-single-page
              layout="prev, pager, next"
              @size-change="handleSizeChange"
              @current-change="handleCurrentChange"
              :page-size="pageSize"
              :page-sizes="[4, 8, 12]"
              :total="total">
          </el-pagination>
        </div>
      </div>
    </div>
    <div class="recommend-shop">
      <div style="margin:20px  0; font-size: 20px; color: orangered">推荐商品</div>
      <div style="display: flex;flex-direction: row;flex-wrap: wrap;justify-content: space-between">
        <el-card class="box-card" v-for="item in recommend" :key="item.id">
          <div class="text item" style="display: flex;flex-direction: column;justify-content: center;">
            <img :src="item.img" width="200px" height="200px" @click="goodsDetail(item.id)">
            <span style="white-space: nowrap;overflow: hidden;text-overflow: ellipsis; font-size: 12px">{{
                item.name
              }}</span>
            <span style="padding: 5px; text-align: center; color: red; font-size: 12px">${{ item.price }}</span>
          </div>
        </el-card>
      </div>
    </div>
  </div>
</template>
<script>
import API from "@/utils/request";

export default {
  // eslint-disable-next-line vue/multi-word-component-names
  name: "Shop",
  data() {
    return {
      // ShufflingFigure: JSON.parse(window.localStorage.getItem("picture")),
      ShufflingFigure: [{id: 1, url: "https://www.helloimg.com/images/2022/11/23/ZjSkVC.jpg"},
        {id: 2, url: "https://www.helloimg.com/images/2022/11/23/ZjSYMt.jpg"},
        {id: 3, url: "https://www.helloimg.com/images/2022/11/23/ZjSV3Q.jpg"}],
      activeIndex2: '1',
      keys: "9",
      tableData: [],
      pageNum: 1,
      pageSize: 4,
      total: 0,
      recommend: []
    }
  },
  mounted() {
    this.GetRecommendShop()
    this.GetBaner()
    this.handleSelect(this.keys)
  },
  methods: {
    GetRecommendShop() {
      API.get("/api/goods/recommend").then(res => {
        console.log(res.data)
        this.recommend = res.data
      })
    },
    handleCurrentChange(pageNum) {
      this.pageNum = pageNum;
      this.handleSelect(this.keys)
    },
    handleSizeChange(pageSize) {
      this.pageSize = pageSize;
      this.handleSelect(this.keys)
    },
    //跳转商品详情页面
    goodsDetail(id) {
      this.$router.push({path: '/goods', query: {id: id}})
    },
    //分类商品
    handleSelect(key) {
      console.log("aaaa", key, this.pageNum, this.pageSize)
      this.keys = key;
      API.get("/api/goods/byCategory/" + this.keys, {
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize
        }
      }).then(res => {
        this.tableData = res.data.records
        console.log(this.tableData)
        this.total = res.data.total
      })
    },
    //轮播图
    GetBaner() {
      if (!this.ShufflingFigure) {
        API.get("api/banner").then(resp => {
          if (resp.code === "0") {
            console.log(resp.data, "ooo")
            this.ShufflingFigure = resp.data
            const Picture = JSON.stringify(resp.data)
            window.localStorage.setItem("picture", Picture)
          }
        })
      }
    }
  }
}
</script>

<style scoped>
.el-menu-demo {
  margin-left: 40px;
  display: flex;
  flex-direction: column;
  height: 280px;
}

.recommend-shop {
  margin-top: 56px;
  /*background-image: linear-gradient(-225deg, #FFFEFF 0%, #D7FFFE 100%);*/
  border: #272643 solid 1px;
  border-radius: 20px;
}

.box-card {
  width: 270px;
  height: 280px;
  border-radius: 20px;
  margin: 10px 0
}

.box-card:hover {
  cursor: pointer;
}
</style>
