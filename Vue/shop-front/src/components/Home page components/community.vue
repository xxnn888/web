<template>
  <div id="content">
    <vue-waterfall-easy ref="waterfall"
                        :imgsArr="imgsArr"
                        @scrollReachBottom="getData"
                        @click="clickFn"
                        srcKey="img"
                        @imgError="imgErrorFn"
                        :enablePullDownEvent="true">
      <div class="img-info" slot-scope="props">
        <!--        <p class="some-info">第{{ props.index + 1 }}张图片</p>-->
        <p class="some-info">{{ props.value.description }}</p>
        <!--        <span style="margin-left: 10px; color: orangered; font-size: 20px">￥ {{ props.value.realPrice }}</span>-->
      </div>
      <div slot="loading" slot-scope="{isFirstLoad}">
        <div slot="loading" v-if="isFirstLoad">first-loading...</div>
        <div v-else>loading...</div>
      </div>
      <div slot="waterfall-over">waterfall-over</div>
    </vue-waterfall-easy>
  </div>
</template>

<script>
import vueWaterfallEasy from "vue-waterfall-easy";
import API from "@/utils/request";

export default {
  // eslint-disable-next-line vue/multi-word-component-names
  name: 'community',
  data() {
    return {
      imgsArr: [],
      group: 0, // 当前加载的加载图片的次数
      pullDownDistance: 0
    }
  },
  components: {
    vueWaterfallEasy
  },
  methods: {
    getData() {
      API.get('/api/community') // 真实环境中，后端会根据参数group返回新的图片数组，这里我用一个静态json文件模拟
          .then(res => {
            this.group++
            if (this.group === 10) { // 模拟已经无新数据，显示 slot="waterfall-over"
              this.$refs.waterfall.waterfallOver()
              return
            }
            this.imgsArr = this.imgsArr.concat(res.data)
            // console.log(this.imgsArr, "2324")
          })
    },
    clickFn(event, {index, value}) {
      console.log(event, index, value, " )))))")
      event.preventDefault()
      if (event.target.tagName.toLowerCase() == 'img') {
        console.log('img clicked', index, value)
        this.$router.push({path: '/goods', query: {id: value.id}})
      }

    },
    imgErrorFn(imgItem) {
      console.log('图片加载错误', imgItem)
    },
  },
  created() {
    this.getData()
  },
}
</script>

<style>
#content {
  /*background-color: white;*/
  /*position: absolute;*/
  /*top: 150px;*/
  /*bottom: 0;*/
  /*left: 50px;*/
  height: 700px;
  width: 100%;
}

</style>
