<template>
    <div class="a1" style="margin-bottom: 20px;">
                <p style="height: 35px;"><b>个人信息</b>
                    <el-button  style="float: right;width: 80px;margin-right: 15px;" @click="save">{{ msg }}</el-button>
                </p> 
                
                <hr />
     <el-form
    :label-position="labelPosition"
    label-width="100px"
    :model="user"
    style="max-width: 460px;margin-top: 20px;" 
  >
  <el-form-item label="头像：" label-position="left" >
      <div >
        <el-upload
            class="avatar-uploader"
            :action="'http://'+ serverIp +':8082/file/upload'"
            :show-file-list="false"
            :on-success="handleAvatarSuccess"
        >
          <img v-if="user.avatarUrl" :src="user.avatarUrl" class="avatar" style="width: 100px;height: 100px;border-radius: 50px ">
          <i v-else class="el-icon-plus avatar-uploader-icon"></i>
        </el-upload>
    </div>
    </el-form-item>
    <el-form-item label="用户名：" label-position="left">
      <el-input v-model="user.username" disabled/>
    </el-form-item>
    <el-form-item label="性别：" label-position="left">
      <el-select v-model="user.sex" placeholder="选择性别" style="width: 100%;">
        <el-option label="男" value="男" style="width: 100%;margin-left: 20px"/>
        <el-option label="女" value="女" style="width: 100%;margin-left: 20px"/>
      </el-select>
    </el-form-item>
    <el-form-item label="邮箱：">
      <el-input v-model="user.email" />
    </el-form-item>
    <el-form-item label="电话：" >
      <el-input v-model="user.phone" />
    </el-form-item>
  </el-form>
    </div>
</template>

<script>
import '@/assets/lib/css/bootstrap.min.css'
import '@/assets/IconFont/iconfont.css'
import { ElMessage } from 'element-plus'

export default {
  name: 'MyInfo',
  props: {
    msg: String
  },data(){
    return{
      user:{},
      dis:true,
      msg:"保存"
    }
  },
  created() {

    this.$emit('updateHeadersStatus', true);
    this.user=JSON.parse(localStorage.getItem("user")) ? JSON.parse(localStorage.getItem("user")) :null


    this.loadUserInfoByUserName();
  },methods:{
    loadUserInfoByUserName(){
      if(this.user===null){
        ElMessage({
          showClose: true,
          message: '请先登陆',
          type: 'error',
        })
      }
      this.axios.get("/user/username/" + this.user.username).then(res =>{
        //从新赋值
        this.user = res.data.data
      })
    },
    handleAvatarSuccess(res) {
      this.form.avatarUrl = res
    },
    save() {
      this.axios.post("/user", this.user).then(res => {
        if (res.data) {
          ElMessage({
            showClose: true,
            message: '保存成功',
            type: 'success',
          })
          this.loadUserInfoByUserName()
        } else {
          ElMessage({
            showClose: true,
            message: '保存失败',
            type: 'error',
          })
        }
      })
    },
  }
}


</script >

<style scoped src="@/assets/css/personal.css">
.avatar-uploader .avatar {
  width: 178px;
  height: 178px;
  display: block;
}

.avatar-uploader .el-upload {
  border: 1px dashed var(--el-border-color);
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
  transition: var(--el-transition-duration-fast);
}

.avatar-uploader .el-upload:hover {
  border-color: var(--el-color-primary);
}

.el-icon.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  text-align: center;
}
</style>


