<template>
    <!-- 内容部分 -->
    <div class="row neirong">
        <div class="col-xl-3 col-md-3 col-sm-12 zuobian">
            <!-- <div class="false" id="c1" @click="change($event)">
                <router-link to="/personal/MyInfo" style="float: left;line-height: 30px;">个人信息</router-link>
                <div style="font-size: 20px;color: #bcbcbc;" align="right"> > </div>
            </div>
            <div class="false" id="c2" @click="change($event)">
                <router-link to="/personal/UsualAddress" style="float: left;line-height: 30px;">常用地址</router-link>
                <div style="font-size: 20px;color: #bcbcbc;" align="right"> > </div>
            </div>
            <div class="false" id="c3" @click="change($event)">
                <router-link to="/personal/Content" style="float: left;line-height: 30px;">更多内容</router-link>
                <div style="font-size: 20px;color: #bcbcbc;" align="right"> > </div>
            </div> -->


            <!-- <Menu :theme="theme" active-name="1">
            <router-link to="/personal/MyInfo">
                <MenuItem name="1">
                    <Icon type="md-document" />  
                    个人信息
                </MenuItem>
            </router-link>
            <router-link to="/personal/UsualAddress">
                <MenuItem name="2">
                    <Icon type="md-chatbubbles" />
                    常用地址
                </MenuItem>
            </router-link>
            <router-link to="/personal/Content">
                <MenuItem name="3">
                    <Icon type="md-heart" />
                    更多内容
                </MenuItem>
            </router-link>
            <MenuItem name="4">
                <Icon type="md-leaf" />
                流失用户
            </MenuItem>
    </Menu>     -->
    <el-row class="tac">
    <el-col :span="12">
      <!-- <h5 class="mb-2">Default colors</h5> -->
      <el-menu
        default-active="2"
        class="el-menu-vertical-demo"
        @open="handleOpen"
        @close="handleClose" style="width: 250px;margin-left: 36px;">
        <router-link to="/personal/MyInfo">
            <el-menu-item index="2" >
                <el-icon><document /></el-icon>
                <span>个人信息</span>
            </el-menu-item>
        </router-link>
        <router-link to="/personal/Order">
            <el-menu-item index="3" >
                <el-icon><Discount /></el-icon>
                <span>我的订单</span>
            </el-menu-item>
        </router-link>
        <router-link to="/personal/UsualAddress">
            <el-menu-item index="4" >
                <el-icon><Location/></el-icon>
                <span>管理地址</span>
            </el-menu-item>
        </router-link>
        
        <router-link to="/personal/Content">
            <el-menu-item index="5">
                <el-icon><More /></el-icon>
                <span>更多内容</span>
            </el-menu-item>
        </router-link>
      </el-menu>
    </el-col>
    
  </el-row>
    
        </div>
        <div class="col-xl-9 col-md-9 col-sm-12 youbian">
           <router-view></router-view>
        </div>
    </div>
</template>

<script>
import '@/assets/css/personal.css'
import '@/assets/lib/css/bootstrap.min.css'
import '@/assets/IconFont/iconfont.css'


export default {

  name: 'Personal',
  props: {
    msg: String
  },
  setup() {

    return {
      theme: 'light'
    };
  },
  created() {
    this.$emit('updateHeadersStatus', true);
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
a{
    /* color: #333; */
     text-decoration:none; 
}

</style>
