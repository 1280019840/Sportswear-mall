<template>
  <div class="header">


    <nav class="navbar navbar-expand-md  navbar-light">
      <!-- Brand -->
      <a class="navbar-brand" href="#">
        <a href="index.html"><img src="../assets/img/logo.png" /></a>
      </a>

      <!-- Toggler/collapsibe Button -->
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
        <span class="iconfont icon-gengduo4" ></span>
      </button>

      <!-- Navbar links -->
      <div class="collapse navbar-collapse" id="collapsibleNavbar">
        <ul class="navbar-nav">
          <Dropdown class="nav-item dropdown" v-for="(items, category) in typeList" :key="category">
            <a href="javascript:void(0)" class="nav-link dropdown-toggle " id="navbardrop" data-toggle="dropdown" style="color: #fff;">
              {{category}}
            </a>
            <template #list>
              <DropdownMenu>
                <router-link class="dropdown-item" to="/product" v-for="item in items" :key="item" @click="typeClick(item.name)">{{ item.name }}</router-link>
              </DropdownMenu>
            </template>
          </Dropdown>
        </ul>
        <div >
          <router-link v-if="flag" to="/bodyBuilding" class="nav-link" data-toggle="dropdown" style="color: #fff;
               font-size: 18px!important; font-weight: bold;">健身讨论区</router-link>
        </div>
      </div>
      <!-- 搜索区 -->

      <div class="navbar-shopcart" align="right" >
        <!-- <a href="cart.html"><span style="color: #fff;" class="iconfont icon-gouwuche1"></span></a> -->
        <router-link to="/cart"><span style="color: #fff;" class="iconfont icon-gouwuche1"></span></router-link>
      </div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      <!-- 右侧登录或注册 -->
      <div class="navbar-login">
        <!-- 根据 flag 的值动态显示链接 -->
        <div style="margin-top: 20px">
          <router-link v-if="flag" to="/personal" class="nav-link" data-toggle="dropdown" style="color: #fff; font-size: 18px!important; font-weight: bold;">用户中心</router-link>
          <span v-if="flag"  class="nav-link" data-toggle="dropdown" style="color: #42b983; font-size: 18px !important; font-weight: bold;" @click="logOut">退出登录</span>

        </div>
        <div style="margin-top: -10px">
          <router-link v-if="!flag" to="/login" style="color: #fff; font-size: 18px!important; font-weight: bold;">登录</router-link>
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          <router-link v-if="!flag" to="/register" style="color: #fff; font-size: 18px!important; font-weight: bold;">注册</router-link>
        </div>
      </div>
    </nav>
  </div>
  <div style="width: 900px;margin-left:22%;margin-top: 20px;">
    <transition name="fade" mode="out-in">
      <div :key="currentIndex" class="card" style="height: 40px;padding-top: 10px;padding-left: 10px">
        {{ noticeList[currentIndex] }}
      </div>
    </transition>
  </div>
    <!-- 商品展示 -->
    <div class="box1" style="min-height: 500px;margin-top: 20px">

        <!-- 第一个盒子 -->
        <div class="boxone">
          <router-link to="/">首页</router-link>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;商品列表
            <p style="line-height: 50px;"><b>过滤方式</b></p>
            <p style="line-height: 50px;">价格</p>
          <div class="price" >
            <!-- 第一行 -->
            <div v-for="(priceRange, index) in priceRanges" :key="index">
              <label class="checkBox">
                <input type="checkbox" v-model="priceRange.selected">
              </label>
              <span class="pricetxt">{{ priceRange.label }}</span>
            </div>
          </div>

            <div class="prciekuan" align="center">
                RMB￥&nbsp;&nbsp;&nbsp;
                <input type="text"  v-model="beginPrice">
            </div>
            <div style="float: left;" align="center" >
                &nbsp;&nbsp;&nbsp;~&nbsp;&nbsp;&nbsp;
            </div>
            <div class="prciekuan" align="center">
                RMB￥&nbsp;&nbsp;&nbsp;
                <input type="text"  v-model="endPrice">
            </div>

            <div style="margin-top: 50px;">
                <button class="qued" type="button" @click="confirmSelection">确定</button>
            </div>
        </div>
        <!-- 第一个盒子 -->
        <div class="boxtwo">
            <!-- 第一行 -->
<!--            <div>-->
<!--                <span style="font-size: 25px;color: #44446e;">产品</span>-->
<!--                <div>-->
<!--                    <span style="font-size: 16px;color: #44446e;">排序方式</span>-->
<!--                    <select>-->
<!--								<option value="null">综合</option>-->
<!--								<option value="bj">销量</option>-->
<!--								<option value="sh">售价</option>-->
<!--								<option value="gz">新品</option>-->
<!--								<option value="sz">浏览量</option>-->
<!--							</select>-->
<!--                </div>-->
<!--            </div>-->
            <hr />
            <!-- 第一行 -->
            <div>
                <div class="row" align="center">
                  <div class="remai col-xl-4 col-md-6" v-for="good in goodList" :key="good">

                    <router-link :to="{ name: 'details', params: { id: good.id } }" >
                      <img :src="good.images" />
                      <p style="line-height: 40px;">{{good.name}}</p>
                      <p style="color: #e74c75;font-size: 20px;">{{good.price}}</p>
                    </router-link>
                  </div>

                </div>
            </div>

        </div>
    </div>
</template>

<script>
import '@/assets/css/view.css'
import '@/assets/lib/css/bootstrap.min.css'
import '@/assets/IconFont/iconfont.css'
import {ElMessage} from "element-plus";
export default {
  name: 'Product',
  props: {
    categoryData: {
      type: String,
      default: ''
    }
  },
  data() {
    return {
      noticeList: [],
      currentIndex: 0,
      intervalId: null,
      intervalDuration: 5000,
      user: JSON.parse(localStorage.getItem("user")) ? JSON.parse(localStorage.getItem("user")) :null,
      beginPrice:1,
      endPrice:999,
      goodList:[],
      typeList:[],
      flag: false,
      priceRanges: [
        { label: "不限", value: "0-9999", selected: false },
        { label: "RMB￥1-99", value: "1-99", selected: false },
        { label: "RMB￥100-199", value: "100-199", selected: false },
        { label: "RMB￥200-299", value: "200-299", selected: false },
        { label: "RMB￥300-499", value: "300-499", selected: false },
        { label: "RMB￥500-799", value: "500-799", selected: false },
        { label: "RMB￥800-999", value: "800-999", selected: false },
        { label: "RMB￥1000或以上", value: "1000-9999", selected: false },
      ],
    }
  },mounted() {
    this.startAutoPlay();
  },beforeDestroy() {
    this.stopAutoPlay();
  },created() {
    this.loadGood();
    this.loadType();
    this.loadNotice();
    
    this.user=JSON.parse(localStorage.getItem("user")) ? JSON.parse(localStorage.getItem("user")) :null
    if( this.user !=null  ){
      this.flag=true;
    }else {
      this.flag=false;
    }

    this.$emit('updateHeadersStatus', false);
  },
  methods:{
    logOut(){
      localStorage.removeItem("user");

      location.reload();
      ElMessage({
        showClose: true,
        message: "退出登录成功",
        type: 'success',
      })
    },
    nextPage() {
      if (this.currentIndex < this.noticeList.length - 1) {
        this.currentIndex++;
      } else {
        this.currentIndex = 0;
      }
    },
    startAutoPlay() {
      this.intervalId = setInterval(() => {
        this.nextPage();
      }, this.intervalDuration);
    },
    stopAutoPlay() {
      clearInterval(this.intervalId);
    },
    typeClick(name){
      this.axios.get("/good/type/"+name).then(res => {
        if (res.data.code === '200') {
          this.goodList=res.data.data
        } else {
          ElMessage({
            showClose: true,
            message: "失败",
            type: 'error',
          })
        }
      })
    },
    loadNotice(){
      this.axios.get("/notice").then(res => {
        if (res.data.code === '200') {
          this.noticeList=res.data.data.map(item => item.content);
        } else {
          this.$message.error("加载失败！")
        }
      })
    },confirmSelection() {
      // 收集选中的复选框的值
      const selectedValues = this.priceRanges
          .filter((priceRange) => priceRange.selected)
          .map((priceRange) => priceRange.value);

      if (selectedValues.length !== 0) {
        const sortedNumbers = selectedValues
            .map((value) => value.split("-").map(Number))
            .flat()  // 将二维数组扁平化
            .sort((a, b) => a - b);

        this.beginPrice = sortedNumbers[0];
        this.endPrice = sortedNumbers[sortedNumbers.length - 1];
      }

      this.axios.get("/good/price/"+this.beginPrice+"/"+this.endPrice).then(res => {
        if (res.data.code === '200') {
          this.goodList=res.data.data
        } else {
          ElMessage({
            showClose: true,
            message: "失败",
            type: 'error',
          })
        }
      })

    },
    loadType(){
      this.axios.get("/type/bySex").then(res => {
        if (res.data.code === '200') {
          this.typeList=res.data.data
          console.log(this.typeList)
        } else {
          this.$message.error("加载类型失败！")
        }
      })
    },
    loadGood(){
      this.axios.get("/good").then(res => {
        if (res.data.code === '200') {
          this.goodList=res.data.data
          console.log(this.goodList);
        } else {
          this.$message.error("搜索商品失败！")
        }
      })
    }
    }

}
</script>

<style scoped>

</style>
