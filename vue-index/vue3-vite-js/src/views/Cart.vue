<template>
    <div class=" gouwhce" style="font-size: 18px;min-height: 450px;margin-bottom: 180px;">
				<div class="jiesuan " align="center">
					<!-- 第一行 -->
					<div class="row f ipone2"  align="left" style="width: 95%;color: #a7a7aa;" >
						<div class="col-xl-4 col-lg-4 col-md-4 col-sm-4 col-4">
							已选商品
						</div>
						<div class="col-xl-2 col-lg-2 col-md-2 col-sm-2 col-2">
							单价
						</div>
						<div class="col-xl-3 col-lg-3 col-md-3 col-sm-3 col-3">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;数量
						</div>
						<div  class="col-xl-3 col-lg-3 col-md-3 col-sm-3 col-3">
							金额
						</div>
					</div>
					
					
					<!-- 第二行 -->
					<div class="row two f ipone2"  align="left" style="width: 95%;" v-for="cart in cartList" :key="cart">
						<div class="col-xl-4 col-lg-4 col-md-4 col-sm-4 col-4">
							<div class="d-flex  ">
							    <div class="p-2 "><img :src="cart.images" style="width: 80px;border-radius:10px;"></div>
							    <div class="p-2 align-self-center" style="line-height: 5px;color: #cdcdcd;">
									<p style="line-height: 40px;">{{cart.goodName}}</p>
									
								</div>

							  </div>
							
						</div>
						<div class="col-xl-2 col-lg-2 col-md-2 col-sm-2 col-2 align-self-center" style="color: #bf9955;">
              {{cart.price}}
						</div>
						<div class="col-xl-3 col-lg-3 col-md-3 col-sm-3 col-3 align-self-center" style="color: #bf9955;">
							<img class="jian" src="../assets/img/加.jpg" @click="red(cart.id,cart.num-1)">
							&nbsp;&nbsp;&nbsp;&nbsp;<span class="num">{{cart.num}}</span>&nbsp;&nbsp;&nbsp;&nbsp;
							<img class="jia" src="../assets/img/减.jpg"  @click="red(cart.id,cart.num+1)">
						</div>
						<div class="col-xl-2 col-lg-2 col-md-2 col-sm-2 col-2 align-self-center" style="color: #bf9955;">
							<div class="d-flex">
							    <div class="p-2 flex-fill">{{ (cart.price*cart.num).toFixed(2)}}</div>
							    <div class="p-2  flex-fill" style="color: #cdcdcd;" align="right">
									<span class="iconfont icon-shanchu" @click="del(cart.id)"></span>
								</div>
							
							</div>
						</div>
					</div>


					<!-- 第二三行移动端 -->
<!--					<div class="ipone">-->
<!--					<div class="row two f "  align="left" style="width: 95%;"  v-for="cart in cartList">-->
<!--						<div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-6 align-self-center">-->
<!--							<div class="d-flex  ">-->
<!--							    <div class="p-2 "><img :src="cart.images"  style="width: 80px;border-radius:10px;"></div>-->
<!--							    <div class="p-2 align-self-center" style="line-height: 5px;color: #cdcdcd;">-->
<!--									<p style="line-height: 40px;">{{ cart.goodName }}</p>-->

<!--								</div>-->

<!--							  </div>-->

<!--						</div>-->

<!--						<div class="col-xl-4 col-lg-4 col-md-4 col-sm-4 col-4 align-self-center" style="color: #bf9955;">-->
<!--								<p style="line-height: 45px;">￥299.00</p>-->
<!--								<p>-->
<!--									<img class="jian" src="../assets/img/加.jpg">-->
<!--									&nbsp;&nbsp;&nbsp;&nbsp;<span class="num">{{cart.num}}</span>&nbsp;&nbsp;&nbsp;&nbsp;-->
<!--									<img class="jia" src="../assets/img/减.jpg">-->
<!--								</p>-->
<!--								<p>￥{{cart.price}}</p>-->
<!--						</div>-->
<!--						<div class="col-xl-2 col-lg-2 col-md-2 col-sm-2 col-2 align-self-center" style="color: #cdcdcd;" >-->
<!--							<span class="iconfont icon-shanchu"></span>-->
<!--						</div>-->
<!--					</div>-->
<!--					</div>-->
					
				
					
<!--					&lt;!&ndash; 第四行 &ndash;&gt;-->
<!--					<div class="f ipone2"  align="left" style="width: 95%;color: #a7a7aa;" >-->
<!--						<div>-->
<!--							下单备注-->
<!--						</div>-->
<!--						<div style="margin-top: 10px;">-->
<!--							<input value="保密发货" type="text" style="background-color: #1a191e;border-radius: 30px;color: #a7a7aa;width: 100%;border: 0px;padding: 5px 20px;" />-->
<!--						</div>-->
<!--					</div>-->

<!--					&lt;!&ndash; 第八行 &ndash;&gt;-->
<!--          <div class="row f ipone2" align="left" style="width: 95%; color: #a7a7aa;">-->
<!--            <div class="col-1 align-self-center" style="padding: 0px;">-->
<!--              <span class="clickable-item" @click="toggleBackground">顺丰/邮政</span>-->
<!--            </div>-->
<!--            <div class="d-flex col-9" align="center">-->
<!--              <div class="p-2 flex-fill">-->
<!--                <div-->
<!--                    class="clickable-item"-->
<!--                    :style="{ backgroundColor: selectedOption === '顺丰' ? '#851b24' : '', color: selectedOption === '顺丰' ? '#fff' : '', padding: '10px', width: '60%' }"-->
<!--                    @click="selectOption('顺丰')"-->
<!--                >-->
<!--                  顺丰-->
<!--                </div>-->
<!--              </div>-->
<!--              <div class="p-2 flex-fill">-->
<!--                <div-->
<!--                    class="clickable-item"-->
<!--                    :style="{ backgroundColor: selectedOption === '邮政' ? '#851b24' : '', color: selectedOption === '邮政' ? '#fff' : '', padding: '10px', width: '60%' }"-->
<!--                    @click="selectOption('邮政')"-->
<!--                >-->
<!--                  邮政-->
<!--                </div>-->
<!--              </div>-->
<!--            </div>-->
<!--          </div>-->

          <!-- 第九行 -->
					<div class="row f ipone2" align="left" style="font-size: 30px; background-color: #1a191e;color: #a7a7aa;width: 100%;">
						<div class="col-2 align-self-center" >
<!--							&nbsp;&nbsp;&nbsp;&nbsp;<span class="iconfont icon-shanchu"></span>-->
						</div>
						
						<div align="center" class="col-7 align-self-center" style="font-size: 16px;">
							已选择
							<span style="color: #bf9955;font-size: 20px;">{{totalNum}}</span>
							件,共计
							<span style="color: #bf9955;font-size: 20px;">{{totalPrice}}</span>元,
							优惠
							<span style="color: #bf9955;font-size: 20px;">0</span>
							元,
              应付<span style="color: #bf9955;font-size: 20px;">{{ totalPrice }}</span>
							元
						</div>
						
						<div class="col-3"  align="right" style="padding: 0px;">
							<router-link to="/submitorder"><img  src="../assets/img/jiesuan.jpg"></router-link>
						</div>
						
					</div>
					
					
					<!-- 第九行移动端 -->
<!--					<div class="ipone">-->
<!--					<div class=" row f " align="left" style="font-size: 30px; background-color: #1a191e;color: #a7a7aa;width: 100%;">-->
<!--						<div class="col-2 align-self-center" >-->
<!--							&nbsp;&nbsp;&nbsp;&nbsp;<span class="iconfont icon-shanchu"></span>-->
<!--						</div>-->

<!--						<div align="center" class="col-6 align-self-center" style="font-size: 16px;">-->
<!--							已选择-->
<!--							<span style="color: #bf9955;font-size: 20px;">{{totalNum}}</span>-->
<!--							件,应付-->
<!--							<span style="color: #bf9955;font-size: 20px;">{{totalPrice}}</span>-->
<!--							元-->
<!--						</div>-->

<!--						<div class="col-4"  align="right" style="padding: 0px;">-->
<!--							<img src="../assets/img/jiesuan.jpg">-->
<!--						</div>-->

<!--					</div>-->
<!--					</div>-->
				</div>
	</div>
</template>

<script>

// import '@/assets/css/cart.css'
import '@/assets/lib/css/bootstrap.min.css'
import '@/assets/IconFont/iconfont.css'
import {ElMessage} from "element-plus";
export default {
  name: 'Cart',
  props: {
    msg: String
  },data(){
    return{
      cartList:[],
      user:{},
      totalPrice:0,
      totalNum:0
    }
  },
  created() {
    this.$emit('updateHeadersStatus', true);
    this.user=JSON.parse(localStorage.getItem("user")) ? JSON.parse(localStorage.getItem("user")) :null
    this.loadCartByUserId();
  },methods:{
    del(id){
      this.axios.get("/cart/delete/"+id).then(res => {
        if (res.data.code === '200') {
          this.loadCartByUserId();
          ElMessage({
            showClose: true,
            message: '删除商品成功！',
            type: 'success',
          })
        } else {
          ElMessage({
            showClose: true,
            message: '增加/减少 件数失败',
            type: 'error',
          })

        }
      })

    },
    red(id,num){
      if(num===0){
        ElMessage({
          showClose: true,
          message: '不能在减少了',
          type: 'error',
        })
        return
      }

      this.axios.get("/cart/update/"+id+"/"+num).then(res => {
        if (res.data.code === '200') {
          this.loadCartByUserId();
        } else {
          ElMessage({
            showClose: true,
            message: '增加/减少 件数失败',
            type: 'error',
          })

        }
      })

    },
    loadCartByUserId(){
      if(this.user==null){
        ElMessage({
          showClose: true,
          message: '还未登陆',
          type: 'error',
        })
        return;
      }

      this.axios.get("/cart/byUserId/"+this.user.id).then(res => {
        if (res.data.code === '200') {
          this.cartList=res.data.data

           const totalPrice = this.cartList.reduce((accumulator, currentItem) => {
            return accumulator + currentItem.price * currentItem.num;
          }, 0);
          this.totalPrice = totalPrice.toFixed(2);

          this.totalNum = this.cartList.reduce((accumulator, currentItem) => {
            return accumulator + currentItem.num;
          }, 0);

        } else {
          ElMessage({
            showClose: true,
            message: '加载购物车失败',
            type: 'error',
          })

        }
      })
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped src="@/assets/css/cart.css">

</style>
