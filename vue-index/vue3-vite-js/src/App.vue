<template>
    <div id="app">
      <Headers v-if="showHeaders"/>
      <router-view @updateHeadersStatus="updateHeadersStatus"/>
      <Footers/>
  </div>
</template>

<script>
import HelloWorld from './components/HelloWorld.vue'
import Headers from './components/Headers.vue'
import Footers from './components/Footers.vue'
import Cart from './views/Cart.vue'

import { onMounted } from 'vue'
 

export default {
  name: 'App',
  components: {
    HelloWorld,
    Headers,
    Footers,
    Cart
  },data() {
    return {
      showHeaders: true
    };
  },
  setup(){
    onMounted(() => {
  document.body.style.setProperty('--el-color-primary', '#606266') //重点：直接重置颜色
  document.body.style.setProperty('--el-color-primary-light-9', '#CDD0D6')
  document.body.style.setProperty('--el-color-primary-light-3', '#409EF')
})
  },
  methods:{
    updateHeadersStatus(status) {
      this.showHeaders = status;
    },
  }
}
</script>

<style>
</style>
