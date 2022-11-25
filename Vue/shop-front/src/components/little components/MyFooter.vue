<template>
  <div class="botton">
    <div class="all">
      <div class="item">七天无理由退换货</div>
      <div class="item">满99元全场免邮</div>
      <div class="item">100%品质保证</div>
    </div>
    <div class="index">
      <div>首页 | 全部商品 | 关于我们</div>
      <div>商城版权所有 2021-2022</div>
    </div>
  </div>
</template>

<script>
export default {
  name: "MyFooter"
}
</script>

<style scoped>
.botton {
  color: #cccccc;
  background-color: rgba(0, 0, 0, 0.79);
  height: 220px;

}

.all {
  display: flex;
}

.item {
  padding: 60px;
  margin-left: 200px;
}

.index {

  color: rgb(121, 119, 119);
  font-size: large;
  /*margin: 0 auto;*/
}
</style>