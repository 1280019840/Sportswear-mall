<template>
    <!-- 商品信息 -->
			<div align="center" class="goods-info ">
				<div class="row" align="left">
					<!-- 右边商品视频大图显示 -->
					<div class="rightgoods">
						<div class = 'list' >
							<div>
								<img  class = 'current' :src = "good.images" alt = "" @click="imgChange('1')">
							</div>
							<div>
								<img :src = "good.images1" alt = "" @click="imgChange('2')">
							</div>
							<div>
								<img :src = "good.images2" alt = "" @click="imgChange('3')">
							</div>
							<div>
								<img :src = "good.images3" alt = "" @click="imgChange('4')">
							</div>
							<div>
								<img :src = "good.images4" alt = "" @click="imgChange('5')">
							</div>

						</div>
						<!-- 第一行大图 -->
						<div class = 'itemarea'>

							<div class="video">
							<div class="chainput"></div>
								<video  rc="video/video.mp4" width="400px" height="400px" controls=""
                        name="media" poster="" id="video" class="m-auto" src="../assets/video/video.mp4">
								</video>
							</div>
							<div class = 'pic'>
								<div class="startinput"></div>
								   <img :src = "bigImg" alt = "">
								<div class="ccc">
									<div class = "cover"></div>
								</div>

							</div>


							<div class="ccc">
								<div class = "detail"></div>
							</div>
						 </div>


					</div>
					<!-- 左边商品信息 -->
					<div class="leftgoods">
						<!-- 文案 -->
						<div style="color: #6d6c74; font-family: '微软雅黑';width: 350px;">
							<p style="font-size: 28px; font-weight: bold;">{{ good.name }}</p>
							<p style="font-size: 21px; font-weight: bold;">FCESSNTL FLC HOODIEPO</p>
							<p style="font-size: 14px;">本店出售所有服饰配件类产品退货需商品干净整洁,吊牌
								包装齐全!如存在污渍,有吊牌摘除痕迹等影响商品二次销
								售的情况,不支持退款退货。</p>
						</div>
						<!-- 尺码 -->
						<div class="chima" style="color: #6d6c74; font-family: '微软雅黑'; font-weight: bold">
							<div align="center" style="float: left; line-height: 80px;">
								<p>SIZE</p>
								<p>COLOR</p>
							</div>
							<div  style="line-height: 65px;">
								<p class="ff1">
                  <!-- <a><img src="../assets/images/s11.png" ></a>
                  <a><img src="../assets/images/s2.png" ></a>
                  <a><img src="../assets/images/s3.png" ></a>
                  <a><img src="../assets/images/s4.png" ></a> -->



                  <!-- <el-radio v-model="radio" label="2"></el-radio> -->
                  <!-- <el-radio v-model="radio1" label="1" border><img src="../assets/images/s3.png" ></el-radio> -->
                  <!-- <el-radio v-model="radio" label="1"><img src="../assets/images/s11.png" ></el-radio>
                  <el-radio v-model="radio" label="2"><img src="../assets/images/s2.png" ></el-radio>
									<el-radio v-model="radio" label="2"><img src="../assets/images/s3.png" ></el-radio>
									<el-radio v-model="radio" label="2"><img src="../assets/images/s4.png" ></el-radio> -->
                  <el-radio-group v-model="radio1" style="margin-left: 15px;">
                    <el-radio-button label="S"></el-radio-button>
                    <el-radio-button label="M"></el-radio-button>
                    <el-radio-button label="L"></el-radio-button>
                    <el-radio-button label="XL"></el-radio-button>
                  </el-radio-group>
								</p>


								<p class="ff2">
									<img src="../assets/images/s51.png" >
									<img src="../assets/images/s6.png" >
								</p>
							</div>
						</div>
						<!-- 立即购买 -->
						<div style="margin-top: 30px">
              <span style="font-size: 25px;color: red; font-weight: bold;margin-right: 30px;margin-top: 30px; ">￥{{good.price}}</span>
              <!-- <span style="font-size: 25px;color: red; font-weight: bold; margin-right: 30px" @click="addCart">加入购物车</span> -->
              <el-button type="info" round @click="addCart" style="font-size: 20px;color: rgb(255, 255, 255); font-weight: bold; margin-right: 30px;width: 160px;height: 40px;background-color: #909399">加入购物车</el-button>
              <img class="aixin" :src="imgSrc" style="margin-top: -12px" @click="collection">
              <!-- <el-button circle></el-button> -->
						</div>
					</div>


				</div>
			</div>

			<div align="center" style="min-height: 230px">
				<div class="xiangq">
          <div>
            <div class="options-container" style="font-size: 30px;">
              <span class="option" @click="showDetails" style="margin-right: 250px">商品信息</span>
              <span class="option" @click="showComments">商品评论</span>
            </div>
            <div style="margin-top: 30px">
              <div v-if="showDetailsContent" >
                <!-- 商品信息的内容 -->
                <span style="font-size: 25px">{{ good.details }}</span>
                <!-- <div class="xqiang">
                  <img class="xqtu" src="../assets/details/1.jpg" >
                  <img class="xqtu" src="../assets/details/2.jpg" >
                  <img class="xqtu" src="../assets/details/3.jpg" >
                  <img class="xqtu" src="../assets/details/4.jpg" >
                  <img class="xqtu" src="../assets/details/5.jpg" >
                  <img class="xqtu" src="../assets/details/6.jpg" >
                  <img class="xqtu" src="../assets/details/7.jpg" >
                  <img class="xqtu" src="../assets/details/8.jpg" >
                </div>	 -->
              </div>
              <div v-if="showCommentsContent" >
                <el-card v-for="(commnet, index) in commnetList" :key="index" style="width: 60%;margin-bottom: 10px">
                  <!-- 第一行显示用户名和时间 -->
                  <div slot="header" style="padding-left: 1px !important; text-align: left;">
                    <span style="margin-left: 10px; font-size: 20px; display: inline-block;">{{ commnet.username }}</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <span style="color: #cccccc; display: inline-block;">{{ commnet.createTime }}</span>
                  </div>
                  <!-- 第二行显示评论内容 -->
                  <div style="text-align: left;margin-left: 10px;">
                    {{ commnet.content }}
                  </div>
                </el-card>

              </div>
            </div>
				 </div>
        </div>


				<router-view></router-view>
			</div>
</template>

<script>
// import '@/assets/css/list.css'
import '@/assets/lib/css/bootstrap.min.css'
import '@/assets/IconFont/iconfont.css'
import {ElMessage} from "element-plus";
import imgUnBright from '@/assets/images/s8.png'
import imgBright from '@/assets/images/s81.png'
export default {
  // emits: ['changeParentProps'],
  name: 'Details',
  props: {
    msg: String
  },data() {
    return {
      good:{},
      goodId:0,
      user: JSON.parse(localStorage.getItem("user")) ? JSON.parse(localStorage.getItem("user")) :null,
      showDetailsContent: true, // 默认显示商品信息
      showCommentsContent: false,
      commnetList:[],
       radio1: '',
       imgSrc: '',
       bigImg:''
    }
  },
  created() {

    this.user= JSON.parse(localStorage.getItem("user")) ? JSON.parse(localStorage.getItem("user")) :null;



    this.$emit('updateHeadersStatus', true);
    this.goodId=this.$route.params.id
    this.axios.get("/good/"+this.goodId).then(res => {
      console.log(res)
      if (res.data.code === '200') {
        this.good=res.data.data
        console.log(this.good);
        this.bigImg = this.good.images
      } else {
        ElMessage({
            showClose: true,
            message: "失败",
            type: 'error',
          })
      }
    })

    this.axios.get("/comment/"+this.goodId).then(res => {
      // console.log(res)
      if (res.data.code === '200') {
        this.commnetList=res.data.data
      } else {
        ElMessage({
            showClose: true,
            message: "失败",
            type: 'error',
          })
      }
    })


    if(this.user==null){
      ElMessage({
        showClose: true,
        message: '还未登陆',
        type: 'error',
      })
      return
    }

    this.axios.get("/application/selectCollection/"+this.goodId+"/"+this.user.id+"/1").then(res => {
        // console.log(res.data);
        if(res.data.code == '600'){
          this.imgSrc = imgBright
        }else{
          this.imgSrc = imgUnBright
        }
      })

  },methods:{
    collection(){
      if(this.user==null){
        ElMessage({
          showClose: true,
          message: '还没有登陆',
          type: 'error',
        })
        return
      }

      this.axios.get("/application/save/"+this.goodId+"/"+this.user.id+"/1").then(res => {
        console.log(res);
        if (res.data.code === '200') {
          this.imgSrc = imgBright
          ElMessage({
            showClose: true,
            message: '收藏成功',
            type: 'success',
          })
        } else {
          this.imgSrc = imgUnBright
          ElMessage({
            showClose: true,
            message: res.data.msg,
            type: 'error',
          })

        }
      })
    },
    addCart(){

      if(this.user==null){
        ElMessage({
          showClose: true,
          message: '还没有登陆',
          type: 'error',
        })
        return
      }

      this.axios.get("/cart/"+this.goodId+"/"+this.user.id).then(res => {
        if (res.data.code === '200') {
          ElMessage({
            showClose: true,
            message: '加入购物车成功',
            type: 'success',
          })
        } else {
          ElMessage({
            showClose: true,
            message: '加入购物车失败',
            type: 'error',
          })

        }
      })
    },
    showDetails() {
      this.showDetailsContent = true;
      this.showCommentsContent = false;
    },
    showComments() {
      this.showDetailsContent = false;
      this.showCommentsContent = true;
    },
    imgChange(a){
      // console.log(a);
      if(a=='1'){
        this.bigImg = this.good.images
      }else if(a=='2'){
        this.bigImg = this.good.images1
      }else if(a=='3'){
        this.bigImg = this.good.images2
      }else if(a=='4'){
        this.bigImg = this.good.images3
      }else if(a=='5'){
        this.bigImg = this.good.images4
      }
    },
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped src="@/assets/css/list.css">
.xiangq{
	color: black!important;;
}

.options-container {
  display: flex;
}

.option {
  text-align: center;
  cursor: pointer;
  border: 1px solid #ccc;
  padding: 10px;
}



</style>
