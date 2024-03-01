<template>
  <!-- 内容部分 -->
    <div class="row neirong">
        <!-- 左边 -->
        <div class="col-xl-4 col-md-4 col-12 zuoneir">
            <div style="font-size: 35px; color: #000;font-weight: bold;">登录</div>
            <div style="font-size: 15px;">
                <p><span style="color: red;line-height: 40px;">*</span>账号</p>
                <!-- <input class="inputda" v-model="user.username" type="text" placeholder="请输入账号" /> -->
                <el-input  v-model="user.username"  minlength="6" maxlength="10"  placeholder="请输入账号" show-word-limit type="text" style="height: 38px;" prefix-icon="User"/>
            </div>
            <div style="font-size: 15px;">
                <p><span style="color: red;line-height: 40px;">*</span>密码</p>
                <!-- <input class="inputda" v-model="user.password" type="password" placeholder="请输入密码" /> -->
                <el-input  v-model="user.password"  minlength="6"  maxlength="10" placeholder="请输入账号" show-password type="password" style="height: 38px;"  prefix-icon="Lock"/>
            </div>
<!--            <div style="font-size: 15px;">-->
<!--                <p><span style="color: red;line-height: 40px;">*</span>验证码</p>-->
<!--                <input class="inputda" type="text" placeholder="请输入验证码" style="width: 70%;" />-->
<!--                <img src="../assets/img/yzm.png">-->
<!--            </div>-->
            <div style="background-color: #000;color: #fff;padding: 10px;font-size: 14px;" align="center" @click="login">立即登录</div>
<!--            <div style="font-size: 14px;" align="right">忘记密码？</div> -->
        </div>
        <!-- 中间 -->
        <div class="col-xl-3 col-md-3 col-0 zhongjian" align="center">
            <img style="margin-top: 100px;" src="../assets/img/分割线.jpg" />
        </div>
        <!-- 右边 -->
        <div class="col-xl-5 col-md-5 col-12 youneir" style="font-size: 15px;">
            <div style="font-size: 35px; color: #555;font-weight: bold;margin-top: 100px;">您还没注册吗？</div>
            <div>为什么注册？</div>
            <div>只需要一分钟创建一个帐户，您可以访问您的会员中心。体验功能，如收藏夹和购物车和商品交易</div>
            <!-- <div><b><a href="register.html">立即注册</a></b></div> -->
            <router-link to="/register">立即注册</router-link>
            <div><img src="../assets/img/注册图标.jpg"></div>

        </div>
    </div>
</template>

<script >

import '@/assets/css/login.css'
import '@/assets/lib/css/bootstrap.min.css'
import '@/assets/IconFont/iconfont.css'
import { getCurrentInstance } from 'vue'
import axios from 'axios'
import router from '../router'
import { ref,reactive,toRefs,toRaw,markRaw} from "vue";
import {ElMessage} from "element-plus";
export default {

    setup() {
        const instance = getCurrentInstance() // vue3提供的方法，创建类似于this的实例
        let user = reactive({
            username:"",
            password:"",
        })

        async function login(){

          axios.post("/user/login", this.user).then(res => {
            if (res.data.code === "200") {
              localStorage.setItem("user",JSON.stringify(res.data.data))
              router.push("/")
              ElMessage({
                showClose: true,
                message: '登陆成功',
                type: 'success',
              })
            } else {
              ElMessage({
                showClose: true,
                message: '登陆失败',
                type: 'error',
              })
            }
          })
            // let token = window.sessionStorage.getItem('token')
            // console.log(window.sessionStorage.getItem('token'));
            // const {data:res} = await axios.post('users/login',{
            //     "uname": this.username,
            //     "password": this.password,
            //     "token":token
            //    
            // })
            // console.log(res);
            // if(res.code !== 20011) return  instance.proxy.$message({ type: 'error', text: '登录失败！' })
            // instance.proxy.$message({ type: 'success', text: '登录成功！' }) // 类似于this.$message()
            // window.sessionStorage.setItem('uname',this.username)
            // window.sessionStorage.setItem('token',res.data)
            // router.push('/product')
        }

        return {
            user,
            ...toRefs(user),
            login
        }
    }
    
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped src="@/assets/css/login.css">

</style>
