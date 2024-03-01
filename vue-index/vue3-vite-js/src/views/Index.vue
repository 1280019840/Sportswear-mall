<template>
  <!-- <div class="hello">
    123456
  </div> -->
  <!-- <div class="heard-h";"></div> -->
  <!-- 搜索区 -->
			<div class="container-fluid">
<!--			<div class="search">-->
<!--				<div class="search-form-bg " align="center">-->
<!--					<form>-->
<!--						<div class="form-h"></div>-->
<!--						<div class="form-groud">-->
<!--							<select id="country" class="form-control">-->
<!--								<option value="null">请选择城市</option>-->
<!--								<option value="bj">北京</option>-->
<!--								<option value="sh">上海</option>-->
<!--								<option value="gz">广州</option>-->
<!--								<option value="sz">深圳</option>-->
<!--							</select>-->
<!--						</div>-->
<!--						<div class="form-groud">-->
<!--							<select id="dinner" class="form-control">-->
<!--								<option value="null">请选择实体店</option>-->
<!--								<option value="bj">广州荔湾店</option>-->
<!--								<option value="sh">广州新塘店</option>-->
<!--								<option value="gz">广州天河店</option>-->
<!--								<option value="sz">广州花都店</option>-->
<!--								<option value="bj">广州荔湾店</option>-->
<!--								<option value="sh">广州黄埔店</option>-->
<!--							</select>-->
<!--						</div>-->
<!--						-->
<!--						<div class="form-groud">-->
<!--							<div class="text-center">-->
<!--								<button type="sumbit" class="btn btn-success btn-lg">-->
<!--									&lt;!&ndash; <img src="img/search-icon.png" />&nbsp; &ndash;&gt;搜索-->
<!--								</button>-->
<!--							</div>-->
<!--						</div>-->
<!--					</form>-->
<!--				</div>-->
<!--			</div>-->
			<!-- 轮播图 -->
			<div class="constant" align="center" >
        <br>
        <br>
        <br>
				<div class="biaoti" >店内活动</div>
        <br><br>

				<div id="carousel" >
          <!-- <div style="margin-left: 10px;margin-top: 53px;height: 480px">
            <el-carousel indicator-position="outside" style="height: 480px"  autoplay>
              <el-carousel-item v-for="item in imagesList"  style="height: 480px" :key="item">
                <img :src="item.url" style="height: 480px;width: 90%;border-radius:  20px 20px 20px 20px;">
              </el-carousel-item>
            </el-carousel>
          </div> -->
		  <div style="margin-left: 10px;margin-top: 56px;">
            <el-carousel indicator-position="outside" style="height: 510px"  autoplay>
              <el-carousel-item v-for="item in imagesList"  style="height: 510px" :key="item">
                <img :src="item.url" style="height: 510px;width: 90%;border-radius:  20px 20px 20px 20px;">
              </el-carousel-item>
            </el-carousel>
          </div>
<!--				  &lt;!&ndash; 指示符 &ndash;&gt;-->
<!--				  <ul class="carousel-indicators">-->
<!--				    <li data-target="#carousel" data-slide-to="0" class="active"></li>-->
<!--				    <li data-target="#carousel" data-slide-to="1"></li>-->
<!--				    <li data-target="#carousel" data-slide-to="2"></li>-->
<!--					<li data-target="#carousel" data-slide-to="3"></li>-->
<!--				  </ul>-->
<!--				 -->
<!--				  &lt;!&ndash; 轮播图片 &ndash;&gt;-->
<!--				  <div class="carousel-inner p-0 mt-1">-->
<!--				    <div class="carousel-item active">-->
<!--				      <img src="../assets/img/carousel/01.jpg" alt="美食图片1">-->
<!--				    </div>-->
<!--				    <div class="carousel-item">-->
<!--				     <img src="../assets/img/carousel/02.jpg" alt="美食图片2">-->
<!--				    </div>-->
<!--				    <div class="carousel-item">-->
<!--				      <img src="../assets/img/carousel/03.jpg" alt="美食图片3">-->
<!--				    </div>-->
<!--					<div class="carousel-item">-->
<!--					  <img src="../assets/img/carousel/04.jpg" alt="美食图片4">-->
<!--					</div>-->
<!--					-->
<!--				  </div>-->
<!--				 -->
<!--				  &lt;!&ndash; 左右切换按钮 &ndash;&gt;-->
<!--				  <a class="carousel-control-prev" href="#carousel" data-slide="prev">-->
<!--				    <span class="carousel-control-prev-icon"></span>-->
<!--				  </a>-->
<!--				  <a class="carousel-control-next" href="#carousel" data-slide="next">-->
<!--				    <span class="carousel-control-next-icon"></span>-->
<!--				  </a>-->

				</div>
				<div class="biaoti" style="margin-top: 10px;" >热门商品</div>
			</div>
      <!-- 热卖商品 -->
			<div style="height:30px"></div>
			<div class="constant " align="center" >
				<div class="row" >

					<div class="dsn1 col-xl-2 col-lg-0 col-md-1 col-sm-0" ></div>
					<div class="remai col-xl-2  col-lg-3 col-md-6 col-sm-6" >
            <!-- <router-link to="/product" @click.native="scrollToTop"> -->
            <router-link to="/product" @click.enter="scrollToTop">
              <img src="../assets/img/details/a1.png">
            </router-link>
					</div>
					<div class="remai col-xl-2  col-lg-3 col-md-6 col-sm-6" >
            <router-link to="/product" @click.native="scrollToTop">
						  <img src="../assets/img/details/a2.png">
            </router-link>
					</div>
					<div class="dsn col-lg-0 col-md-1"></div>
					<div class="dsn col-lg-0 col-md-1"></div>
					<div class="remai col-xl-2  col-lg-3 col-md-6 col-sm-6" >
            <router-link to="/product" @click.native="scrollToTop">
              <img src="../assets/img/details/a3.png">
            </router-link>
					</div>
					<div class="remai col-xl-2  col-lg-3 col-md-6 col-sm-6" >
            <router-link to="/product" @click.native="scrollToTop">
              <img src="../assets/img/details/a4.png">
            </router-link>
					</div>
					<div class="dsn1 col-xl-2 col-lg-0 col-md-1 col-sm-0" ></div>


				</div>

				<div class="row" >

					<div class="dsn1 col-xl-2 col-lg-0 col-md-1 col-sm-0" ></div>
					<div class="remai col-xl-2  col-lg-3 col-md-6 col-sm-6" >
            <router-link to="/product" @click.native="scrollToTop">
              <img src="../assets/img/details/a5.png">
            </router-link>
					</div>
					<div class="remai col-xl-2  col-lg-3 col-md-6 col-sm-6" >
            <router-link to="/product" @click.native="scrollToTop">
              <img src="../assets/img/details/a6.png">
            </router-link>
					</div>
					<div class="dsn col-lg-0 col-md-1"></div>
					<div class="dsn col-lg-0 col-md-1"></div>
					<div class="remai col-xl-2  col-lg-3 col-md-6 col-sm-6" >
            <router-link to="/product" @click.native="scrollToTop">
              <img src="../assets/img/details/a7.png">
            </router-link>
					</div>
					<div class="remai col-xl-2  col-lg-3 col-md-6 col-sm-6" >
            <router-link to="/product" @click.native="scrollToTop">
              <img src="../assets/img/details/a8.png">
            </router-link>
					</div>
					<div class="dsn1 col-xl-2 col-lg-0 col-md-1 col-sm-0" ></div>


				</div>

			</div>
      </div>
</template>

<script>
import '@/assets/css/index.css'
import '@/assets/lib/css/bootstrap.min.css'
import '@/assets/IconFont/iconfont.css'
import '@/assets/lib/jquery/jquery.min.js'
import '@/assets/lib/js/bootstrap.bundle.min.js'
import '@/assets/lib/js/bootstrap.bundle.min.js'
import {Links} from "view-ui-plus";

export default {
  name: 'Index',
  components: {Links},
  props: {
    msg: String
  },data() {
    return {
      imagesList:[]
    }
  },created() {
    this.loadImages();
  },methods:{
    scrollToTop() {
      window.scrollTo(0, 0); // 将滚动位置设置为页面顶部
    },
    loadImages(){
      this.axios.get("/images").then(res => {
        if (res.data.code === '200') {
          this.imagesList=res.data.data
        } else {
          this.$message.error("加载图片失败！")
        }
      })
    },
  }
}

</script>


<style scoped>
.el-carousel__item h3 {
  line-height: 100vh;

}

</style>
