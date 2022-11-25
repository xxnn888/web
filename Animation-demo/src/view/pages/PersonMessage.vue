<template>
  <div>
    <ul>
      <li>{{ Info.title }}</li>
    </ul>
  </div>
</template>

<script>
export default {
  name: "PersonMessage",
  data() {
    return {
      Info: ""
    }
  },
  mounted() {
    this.Info = this.$route.query
  }
}
</script>

<style scoped>
li {
  list-style-type: none;
}
</style>
