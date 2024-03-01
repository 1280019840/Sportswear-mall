<template>
    <!-- 内容部分 -->
    <div class="row neirong">
        <!-- 左边 -->
        <div class="zuoneir">
            <div style="font-size: 35px; color: #000;font-weight: bold;" align="center">注册</div>
            <div style="font-size: 15px;">
                <p><span style="color: red;line-height: 10px;">*</span>用户名</p>
                <el-input  v-model="user.username"  minlength="6" maxlength="16" placeholder="请输入用户名" show-word-limit type="text" style="height: 42px;margin-top: 4px;" prefix-icon="User"  @blur="userValid"/>
                <el-alert title="请输入以大小字母开头，长度为6~16位的用户名" v-show="userLength" type="error" />
            </div>
            <div style="font-size: 15px;">
                <p><span style="color: red;line-height: 0px;">*</span>性别</p>
                <el-radio-group v-model="user.sex" style="margin-left: 10px;margin-top: 5px;">
                    <el-radio :label="1" value="男">男</el-radio>
                    <el-radio :label="2" value="女">女</el-radio>
                 </el-radio-group>
            </div>
            <div style="font-size: 15px;">
                <p><span style="color: red;line-height: 10px;">*</span>邮箱地址</p>
                <el-input  v-model="user.email"  minlength="6" maxlength="18" placeholder="请输入邮箱地址" show-word-limit type="email" style="height: 42px;margin-top: 4px;" prefix-icon="Message" @blur="emailValid"/>
                <el-alert title="邮箱输入有误！请重新输入" v-show="emailLength" type="error" />
            </div>
            <div style="font-size: 15px;">
                <p><span style="color: red;line-height: 10px;">*</span>手机号码</p>
                <el-input  v-model.number="user.phone"  minlength="11" maxlength="11" placeholder="请手机号码" show-word-limit type="number" style="height: 42px;margin-top: 4px;"  οninput="value=value.replace(/[^0-9.]/g,'')" prefix-icon="Iphone" @blur="phoneValid"/>
                <el-alert title="格式有误！请重新输入11位手机号码" v-show="telephoneValid" type="error" />
            </div>
            <div style="font-size: 15px;">
                <p><span style="color: red;line-height: 10px;">*</span>密码</p>
                <el-input  v-model="user.password"  minlength="6"  maxlength="10" placeholder="请输入密码" show-password type="password" style="height: 42px;margin-top: 4px;"  prefix-icon="Lock" @blur="passlength"/>
                <el-alert title="请设置密码长度为6~10位" v-show="passwordLength" type="error" />
            </div>
            <div style="font-size: 15px;">
                <p><span style="color: red;line-height: 10px;">*</span>确认密码</p>
                <el-input  v-model="user.passwordCheck"  minlength="6"  maxlength="10" placeholder="请输入确认密码" show-password type="password" style="height: 42px;margin-top: 4px;"  prefix-icon="Lock" @blur="valid"/>
                <el-alert title="密码一致！" v-show="passwordSuucess" type="success" />
                <el-alert title="密码不一致！请重新输入" v-show="passwordError" type="error" />
            </div>
            <el-button type="primary" plain color="white" style="width: 500px;height: 42px;background-color: black;margin-top: 40px;" :disabled="disButton" @click="login">立即注册</el-button>

        </div>

    </div>
</template>

<script>
import '@/assets/css/register.css'
import '@/assets/lib/css/bootstrap.min.css'
import '@/assets/IconFont/iconfont.css'
import { getCurrentInstance } from 'vue'
import router from '../router'
import { ref,reactive,toRefs,toRaw,markRaw} from "vue";
import axios from 'axios'
import {ElMessage} from "element-plus";
export default {
  name: 'Register',
  data() {
    return {
      user :{
        username:"",
        password:"",
        email:'',
        phone:'',
        passwordCheck: '',
        sex: 1
      },
      userLength:false,
      passwordLength :ref(false),
      passwordError :ref(false),
      passwordSuucess : ref(false),
      telephoneValid : ref(false),
      emailLength : ref(false),
      userLength : ref(false),
      disButton : ref(true),
    }
  },
  methods: {
    login(){

          if(this.user.passwordCheck!==this.user.passwordCheck){
            ElMessage({
              showClose: true,
              message: '两次输入的密码不一致',
              type: 'error',
            })
            return
          }
          if(this.user.sex ==2){
            this.user.sex='女'
          }else{
            this.user.sex='男'
          }
          axios.post("/user/register", this.user).then(res => {
            if(res.data.code === '200') {
              ElMessage({
                showClose: true,
                message: '注册成功,快去登陆吧',
                type: 'success',
              })
            } else {
              ElMessage({
                showClose: true,
                message: '注册失败',
                type: 'error',
              })
            }
          })
        },valid(){
            if (this.user.passwordCheck !== this.user.password && this.user.passwordCheck !== '') {
                   this.passwordError = !this.passwordError
                   setInterval(()=>{
                    this.passwordError = false
                   },1500)
                } else if(this.user.passwordCheck !== '' && this.user.passwordCheck == this.user.password){
                    this.passwordSuucess = !this.passwordSuucess
                    this.disButton = !this.disButton
                    setInterval(()=>{
                    this.passwordSuucess = false
                   },1500)
                }
        },passlength(){
            if(this.user.password.length<6 || this.user.password.length>10){
                 this.passwordLength = ! this.passwordLength
                 setInterval(()=>{
                    this.passwordLength = false
                   },1500)
            }
        },phoneValid(){
            if (/^(13[0-9]|14[0-9]|15[0-9]|16[6]|18[0-9]|19[6,9]|17[0-9])\d{8}$/i.test(this.user.phone) == false) {
                this.telephoneValid = !this.telephoneValid
                setInterval(()=>{
                    this.telephoneValid = false
                   },1500)
            }
            
        },emailValid(){
            if(/^([a-zA-Z0-9]+[_|_|.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|_|.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/.test(this.user.email) == false){
                this.emailLength = !this.emailLength
                setInterval(()=>{
                    this.emailLength = false
                   },1500)
            }
        },userValid(){
            if(/^[[A-Za-z][A-Za-z1-9_-]{5,15}$/.test(this.user.username) == false){
              this.userLength = !this.userLength
                setInterval(()=>{
                    this.userLength = false
                   },1500)
            }
        }

    }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style >
 
input::-webkit-outer-spin-button,
    input::-webkit-inner-spin-button {
    -webkit-appearance: none;
}
input[type="number"]{
    -moz-appearance: textfield;
}
</style>
