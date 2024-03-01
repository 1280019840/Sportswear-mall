<script>

import {ElMessage} from "element-plus";

export default {
  name: 'Order',
  props: {}, data() {
    return {
      user:{},
      orderList: [],
      // tabTypes: ["全部", "待发货", "已发货", "已完成","待评论", "已取消"],
      tabTypes: ["全部", "待发货", "已发货", "待评论", "已取消"],
      activeName:"",
      dialogFormVisible: false,
      textarea: '',
      // form: {
      //     name: '',
      //     region: '',
      //     date1: '',
      //     date2: '',
      //     delivery: false,
      //     type: [],
      //     resource: '',
      //     desc: ''
      //   },
      form:{},
        formLabelWidth: '120px'
    };
  }, created() {
    this.user=JSON.parse(localStorage.getItem("user")) ? JSON.parse(localStorage.getItem("user")) :null
    this.$emit('updateHeadersStatus', true);
    this.loadOrder()
  }, methods: {
    updateOrder(state,id){
      console.log(id);
      this.axios.get("/order/updateOrder",{
        params: {
          state: state=='已完成'?'待评论':state,
          id:id
        }
      }).then(res => {
        if (res.data.code === '200') {
          this.orderList=res.data.data
          ElMessage({
            showClose: true,
            message: state+"成功",
            type: 'success',
          })
          this.loadOrder()
        } else {
          ElMessage({
            showClose: true,
            message: "失败",
            type: 'error',
          })
        }
      })
    },
    loadOrder(){
      if(this.user==null){
        ElMessage({
          showClose: true,
          message: "请先登陆",
          type: 'error',
        })
        return;
      }

      const type = this.activeName==="0" ? "":this.tabTypes[this.activeName];
      this.axios.get("/order",{
        params: {
          state: type,
          userId:this.user.id
        }
      }).then(res => {
        if (res.data.code === '200') {
          this.orderList=res.data.data
          console.log(this.orderList);
          // console.log(this.orderList[1].goodList[0].id);
        } else {
          ElMessage({
            showClose: true,
            message: "失败",
            type: 'error',
          })
        }
      })
    },
    commentGoods(id){
       this.dialogFormVisible = false
      this.form.username = this.user.username
      this.form.goodId = id
      this.form.content = this.textarea
      console.log(this.form);
      this.axios.post("/comment/commentGoods", this.form).then(res => {
        if (res.data) {
          console.log(res.data);
          ElMessage({
            showClose: true,
            message: '评论成功',
            type: 'success',
          })
          this.updateOrder("已评论",id)
          this.textarea=''
        } else {
          ElMessage({
            showClose: true,
            message: '评论失败',
            type: 'error',
          })
        }
      })
    },
    getGoodsId(id){
      this.dialogFormVisible = true
      console.log(id); 
    },
  }
}

</script>

<template>
  <div class="order-container">
    <el-tabs @tab-change="loadOrder" v-model="activeName">
      <!-- tab切换 -->
      <el-tab-pane v-for="item in tabTypes" :key="item"  :label="item" ></el-tab-pane>

      <div class="main-container">
        <div class="holder-container" v-if="orderList.length === 0">
          <el-empty description="暂无订单数据" />
        </div>
        <div v-else>
          <!-- 订单列表 -->
          <div class="order-item" v-for="order in orderList" :key="order.id">
            <div class="head">
              <span>下单时间：{{ order.createTime }}</span>
              <span>订单编号：{{ order.ono }}</span>
              <!-- 未付款，倒计时时间还有 -->
<!--              <span class="down-time" v-if="order.orderState === 1">-->
<!--                <i class="iconfont icon-down-time"></i>-->
<!--                <b>付款截止: {{ order.countdown }}</b>-->
<!--              </span>-->
            </div>
            <div class="body">
              <div class="column goods" >
                <ul >
                  <li v-for="item in order.goodList" :key="item" >
                    <a class="image" href="javascript:;">
                      <img :src="item.images" alt="" style="width: 70px;height: 70px;margin-top: -10px"/>
                    </a>
                    <div class="info" >
                      <p class="name ellipsis-2" style="margin-top: -10px">
                        <!-- {{ item.name }} -->
                      </p>
                      <!-- <div class="price" style="margin-top: -10px">¥{{ item.price?.toFixed(2) }}</div> -->
                    </div>
                    <div>
                    <p  style="margin-top: -10px;">邮寄快递：{{order.express}}</p>
                      <br>
                    <p style="margin-top: -10px">支付方式：{{order.pay}}</p>
                    </div>
                  </li>
                </ul>
              </div>
              <div class="column amount">
                <p class="red">¥{{ order.total?.toFixed(2) }}</p>
                <p>（含运费：¥{{ order.total?.toFixed(2) }}）</p>
                <p>在线支付</p>
              </div>
              <div class="column action">
                <p><a href="javascript:;">{{ order.state }}</a></p>
                <br>
                <el-button v-if="order.state !== '已完成'&&order.state !== '已取消'&&order.state!=='待评论'&&order.state!=='已评论'" type="primary" size="small" @click="updateOrder('已完成',order.id)">
                  确认收货
                </el-button>
                <br>
                <br>
                <el-button v-if="order.state !== '已完成'&&order.state !== '已取消'&&order.state!=='待评论'&&order.state!=='已评论'" type="primary" size="small" @click="updateOrder('已取消',order.id)">
                  取消订单
                </el-button>
                <el-button v-if="order.state =='待评论'" type="primary" size="small"  @click="dialogFormVisible = true" >
                  点击评论
                </el-button>
              </div>
              <!-- Form -->
              <!-- <el-button type="text" @click="dialogFormVisible = true">打开嵌套表单的 Dialog</el-button> -->

              <el-dialog title="评价商品" v-model="dialogFormVisible">
                <el-input
                      type="textarea"
                      placeholder="请输入内容"
                      v-model="textarea"
                      maxlength="30"
                      show-word-limit
                    >
                    </el-input>
                <div style="margin: 20px 0;"></div>
                <div slot="footer" class="dialog-footer">
                  <el-button @click="dialogFormVisible = false">取 消</el-button>
                  <!-- <el-button type="primary"  @click="updateOrder('评论',order.id)">确 定</el-button> -->
                  <el-button type="primary"  @click="commentGoods(order.id)">确 定</el-button>
                </div>
              </el-dialog>
            </div>
          </div>

        </div>
      </div>

    </el-tabs>
  </div>
</template>



<style scoped lang="scss">

.order-container {
  padding: 10px 20px;

  .pagination-container {
    display: flex;
    justify-content: center;
  }

  .main-container {
    min-height: 500px;

    .holder-container {
      min-height: 500px;
      display: flex;
      justify-content: center;
      align-items: center;
    }
  }
}

.order-item {
  margin-bottom: 20px;
  border: 1px solid #f5f5f5;

  .head {
    height: 50px;
    line-height: 50px;
    background: #f5f5f5;
    padding: 0 20px;
    overflow: hidden;

    span {
      margin-right: 20px;

      &.down-time {
        margin-right: 0;
        float: right;

        i {
          vertical-align: middle;
          margin-right: 3px;
        }

        b {
          vertical-align: middle;
          font-weight: normal;
        }
      }
    }

    .del {
      margin-right: 0;
      float: right;
      color: #999;
    }
  }

  .body {
    display: flex;
    align-items: stretch;

    .column {
      border-left: 1px solid #f5f5f5;
      text-align: center;
      padding: 20px;

      >p {
        padding-top: 10px;
      }

      &:first-child {
        border-left: none;
      }

      &.goods {
        flex: 1;
        padding: 0;
        align-self: center;

        ul {
          li {
            border-bottom: 1px solid #f5f5f5;
            padding: 10px;
            display: flex;

            &:last-child {
              border-bottom: none;
            }

            .image {
              width: 70px;
              height: 70px;
              border: 1px solid #f5f5f5;
            }

            .info {
              width: 220px;
              text-align: left;
              padding: 0 10px;

              p {
                margin-bottom: 5px;

                &.name {
                  height: 38px;
                }

                &.attr {
                  color: #999;
                  font-size: 12px;

                  span {
                    margin-right: 5px;
                  }
                }
              }
            }

            .price {
              width: 100px;
            }

            .count {
              width: 80px;
            }
          }
        }
      }

      &.state {
        width: 120px;

        .green {
          // color: $xtxColor;
          color: green;
        }
      }

      &.amount {
        width: 200px;

        .red {
          // color: $priceColor;
          // color: red;
          color: green;

        }
      }

      &.action {
        width: 140px;

        a {
          display: block;

          &:hover {
            // color: $xtxColor;
            color: rgb(123, 117, 212);
          }
        }
      }
    }
  }
}



</style>