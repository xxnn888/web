<template>
  <div id="content">
    <vue-waterfall-easy ref="waterfall"
                        :imgsArr="imgsArr"
                        @scrollReachBottom="getData"
                        @click="clickFn"
                        :enablePullDownEvent="true"
                        @pullDownMove="pullDownMove"
                        @pullDownEnd="pullDownEnd">
      <div slot-scope="props">
        <p v-html="props.value.info"></p>
      </div>
    </vue-waterfall-easy>
  </div>
</template>

<script>
import vueWaterfallEasy from "vue-waterfall-easy";
import axios from "axios"

export default {
  name: 'MyWater',
  data() {
    return {
      imgsArr: []
    }
  },
  components: {
    vueWaterfallEasy
  },
  methods: {
    // getData() {
    //   axios.get('./static/mock/data.json?group=' + this.group) // 真实环境中，后端会根据参数group返回新的图片数组，这里我用一个静态json文件模拟
    //       .then(res => {
    //         this.group++
    //         if (this.group === 10) { // 模拟已经无新数据，显示 slot="waterfall-over"
    //           this.$refs.waterfall.waterfallOver()
    //           return
    //         }
    //         this.imgsArr = this.imgsArr.concat(res.data)
    //       })
    // },
    clickFn(event, {index, value}) {
      // event.preventDefault()
      if (event.target.tagName.toLowerCase() == 'img') {
        console.log('img clicked', index, value)
      }
    },
    imgErrorFn(imgItem) {
      console.log('图片加载错误', imgItem)
    },
    changeImgArr() {
      axios.get('./static/mock/data-change.json') // 真实环境中，后端会根据参数group返回新的图片数组，这里我用一个静态json文件模拟
          .then(res => {
            this.imgsArr = res.data
          })
    },
    pullDownMove(pullDownDistance) {
      // console.log('pullDownDistance', pullDownDistance)
      this.pullDownDistance = pullDownDistance
    },
    pullDownEnd(pullDownDistance) {
      console.log('pullDownEnd')
      if (pullDownDistance > 50) {
        alert('下拉刷新')
      }
      this.pullDownDistance = 0
    },
  },
  created() {
    this.getData()
  },
}
</script>

<style>
#content {
  position: absolute;
  top: 80px;
  bottom: 0;
  left: 250px;
  width: 80%;
}
</style>
