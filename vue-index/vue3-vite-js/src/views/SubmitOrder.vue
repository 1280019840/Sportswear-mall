<template>
    <!-- 内容部分 -->
			<div class="wai row" >
				<!-- 左边div -->
				<div class="col-xl-8 col-sm-12 zuobian" >
					<div style="margin-bottom: 20px;">
						<p><b>选择收货地址</b></p>
						<hr />
						<!-- 左边文案 -->
						<div style="float: left;width: 160px;line-height: 50px;">
              <p ><span style="color: red;">*</span> 选择收货人:</p>
							<p><span style="color: red;">*</span> 收货人:</p>
							<p><span style="color: red;">*</span> 联系电话:</p>
							<p><span style="color: red;">*</span> 邮箱地址:</p>
							
              <p style="margin-top: 15px">快递:</p>
              <p style="margin-top: 10px">支付方式:</p>
							<p >备注:</p>
						
						</div>
						<!-- 右边输入框 -->
						<div class="shuru">
               <el-form-item label="" label-position="left">
                    <el-select v-model="order.id" placeholder="选择收货地址"  @change="selectApparatus"  style="width: 100%;">
                      <!-- <el-option label="男" value="男" style="width: 100%;margin-left: 20px"/> -->
                      <el-option v-for="address in addressList" :key="address.id" :value="address.id" :label="address.consignee" style="width: 100%;margin-left: 20px"></el-option>
                    </el-select>
                  </el-form-item>
							<div><input class="inputda" type="text" placeholder="输入收货人" v-model="order.consignee"/></div>
							<div><input class="inputda" type="text" placeholder="输入联系电话" v-model="order.phone"/></div>
							<div><input class="inputda" type="text" placeholder="输入邮箱地址" v-model="order.address"/></div>
							<div class="d-flex mb-3" style="width: 50%;">
							    <div class=" flex-fill selcetc">
									<!-- <select v-for="address in addressList" v-model="order.address" :key="address">
										<option  style="width: 50%;" :value="address.address">{{address.address}}</option>
									</select> -->
                  <!-- <select v-for="address in addressList" v-model="order.address" :key="address">
										<option  style="width: 50%;" :value="address.consignee">{{address.consignee}}</option>
									</select> -->
                 
								</div>
							  </div>

							<div>
                <el-radio-group v-model="order.express">
                  <el-radio label="邮政" value="邮政"/>
                  <el-radio label="顺丰" value="顺丰"/>
                  <el-radio label="京东" value="京东"/>
                  <el-radio label="圆通" value="圆通"/>
                </el-radio-group>
              </div>
              <div>
                <el-radio-group v-model="order.pay">
                  <el-radio label="微信" value="微信"/>
                <el-radio label="支付宝" value="支付宝"/>
                  <el-radio label="银行卡" value="银行卡"/>
              </el-radio-group>
              </div>
              <div><input class="inputda" type="text" placeholder=" " v-model="order.remark"/></div>
						</div>
					</div>
					<div style="background-color: #f2f3f7;height: 50px;width: 105%;position: relative;left: -20px;"></div>
					<div style="margin-top: 20px;">
						<p><b>确认订单信息</b></p>
						<hr />
						<div class="row">
						  <div class="col-6">商品</div>
						  <div class="col-2">价格</div>
						  <div class="col-2">数量</div>
						  <div class="col-2">小计</div>
						</div>
						<hr />
						<div class="row" v-for="cart in cartList" :key="cart">
						  <div class="col-2 align-self-center" >
							<img style="width: 100%;" :src="cart.images" />
						  </div>
						  <div class="col-4 align-self-center">
							{{cart.goodName}}
						  </div>
						  <div class="col-2 align-self-center">{{cart.price}}</div>
						  <div class="col-2 align-self-center">{{cart.num}}</div>
						  <div class="col-2 align-self-center">{{(cart.price*cart.num).toFixed(2)}}</div>
						</div>
					</div>
				</div>
				<!-- 右边div -->
				<div  class="col-xl-4 col-sm-12 youbiandiv" >
					<div class="youbian" align="center" style="width: 100%;">
						<p><b>商品 ({{totalNum}}件)</b></p>
						<hr />
						<div style="width: 100%;">
							<div align="left" style="float: left;">合计:</div>
							<div align="right">RMB￥{{totalPrice}}</div>
						</div>
						<div style="width: 100%;">
							<div align="left" style="float: left;">运费:</div>
							<div align="right">RMB￥0.00</div>
						</div>
						<hr />
						<div align="left">
							<div align="left" style="float: left;">
								<span style="font-size: 20px; font-weight: bold;">总价:</span>
							</div>
							<div align="right">
								<span style="font-size: 20px; font-weight: bold;color: #e74c3c;">RMB￥{{totalPrice}}</span>
							</div>
							
						</div>
						<div style="background-color: #000;color: #fff;padding: 10px;" @click="submitOrder">
							提交订单
						</div>
            <router-link to="/cart">
              <div style="background-color: #f2f2f2;color: #777;padding: 10px;" >
                返回购物车
              </div>
            </router-link>

					</div>
				</div>
			</div>
</template>

<script>
import '@/assets/css/close.css'
import '@/assets/lib/css/bootstrap.min.css'
import '@/assets/IconFont/iconfont.css'
import {ElMessage} from "element-plus";
export default {
  name: 'SubmitOrder',
  props: {
    msg: String
  },data(){
    return{
      cartList:[],
      user:{},
      totalPrice:0,
      totalNum:0,
      addressList:[],
      order:{},
      goodList:[]
    }
  },
  created() {
    this.$emit('updateHeadersStatus', true);
    this.user=JSON.parse(localStorage.getItem("user")) ? JSON.parse(localStorage.getItem("user")) :null
    this.loadCartByUserId();
    this.loadAddressByUserId();
  },methods:{
    submitOrder(){
      this.order.userId=this.user.id;
      this.order.username=this.user.username;
      this.order.num=this.totalNum;
      this.order.total=this.totalPrice;
      this.order.goodId=this.goodList;

      this.axios.post("/order/save", this.order).then(res => {
        if (res.data.code === '200') {
          ElMessage({
            showClose: true,
            message: '下单成功',
            type: 'success',
          })
          this.order={}
          this.loadCartByUserId()
        } else {
          ElMessage({
            showClose: true,
            message: '下单失败',
            type: 'error',
          })
        }
      })


    },selectApparatus(){
      const item = this.addressList.find(address => address.id === this.order.id)
      console.log(item);
      this.order.consignee = item.consignee;
      this.order.phone = item.phone;
      this.order.address = item.address;
    },
    loadAddressByUserId(){
      if(this.user==null){
        ElMessage({
          showClose: true,
          message: '还未登陆',
          type: 'error',
        })
        return;
      }
      this.axios.get("/address/byUserId/"+this.user.id).then(res => {
        console.log(res)
        if (res.data.code === '200') {
          this.addressList=res.data.data
          console.log(res.data.data);
        } else {
          ElMessage({
            showClose: true,
            message: '加载购物车失败',
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
        console.log(res)
        if (res.data.code === '200') {
          this.cartList=res.data.data

          const totalPrice = this.cartList.reduce((accumulator, currentItem) => {
            return accumulator + currentItem.price * currentItem.num;
          }, 0);
          this.totalPrice = totalPrice.toFixed(2);

          this.totalNum = this.cartList.reduce((accumulator, currentItem) => {
            return accumulator + currentItem.num;
          }, 0);

          this.cartList.forEach((currentItem) => {

            const goodInfo = [
               currentItem.goodId,
                currentItem.num
            ];

            this.goodList.push(goodInfo);
          });

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
<style scoped>

</style>
