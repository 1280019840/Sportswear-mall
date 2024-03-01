<template>
    <div class="a1" style="margin-bottom: 20px;height: 380px;">
      <div style="display: flex;margin-bottom: 10px">
        <p><b>地址管理</b></p>
        <el-button  type="primary" size="small" style="margin-left: 100px" @click="add">添加地址</el-button>
      </div>
            <hr />

                 <el-table :data="address" style="width: 100%"   >
                     <el-table-column  label="Name"   prop="consignee"/>
                      <el-table-column  label="Phone"   prop="phone"></el-table-column>
                      <el-table-column label="地址" prop="address" />
                    <el-table-column align="right">
                    <template #header>
                      <el-input v-model="search" size="small" placeholder="Type to search" />
                    </template>
                    <template #default="scope">
                      <el-button text @click="dialogFormVisible = true">
                      <el-button size="small" @click="handleEdit(scope.$index, scope.row)" style="width: 50px;">编辑</el-button>
                    </el-button>
          <el-popconfirm
    confirm-button-text="确认" confirm-button-type="info"
    cancel-button-text="取消" cancel-button-type="plain"
    :icon="InfoFilled"
    icon-color="#626AEF"
    title="确认删除吗？"
    @confirm.prevent="del(scope.$index, scope.row)"
    @cancel="cancelEvent"
  >
    <template #reference>
      <el-button style="width: 50px;height: 24px;">
        <!-- <el-button size="small"  type="danger" @click="handleDelete(scope.$index, scope.row)" style="width: 50px;" >Delete</el-button> -->
        <el-button size="small"  type="danger" style="width: 50px;" >删除</el-button>
      </el-button>
    </template>
  </el-popconfirm>
          
      </template>
    </el-table-column>
    
  </el-table>


   <!-- Form -->
  

  <el-dialog v-model="dialogFormVisible" title="添加/修改地址" >
    <el-form :model="form">
      <el-form-item label="Name：" >
        <el-input v-model="form.consignee" type="textarea" autocomplete="off"  style="width: 100%;margin-left: 15px;"/>
      </el-form-item>
      <el-form-item label="Phone：" >
        <el-input v-model="form.phone" type="textarea" autocomplete="off"  style="width: 100%;margin-left: 15px;"/>
      </el-form-item>
      <el-form-item label="Address：" >
        <el-input v-model="form.address" type="textarea" autocomplete="off"  style="width: 100%;margin-left: 5px;"/>
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogFormVisible = false" style="width:100px;">取消</el-button>
        <el-button type="info" @click="save" style="width: 100px;">
          提交
        </el-button>
      </span>
    </template>
  </el-dialog>
    </div>
</template>

<script>
import '@/assets/lib/css/bootstrap.min.css'
import '@/assets/IconFont/iconfont.css'
import { ElMessage } from 'element-plus'

export default {
  name: 'address',
  props: {
    msg: String
  },data(){
    return{
      address:[],
      form: {},
      dialogFormVisible:false,
      search:''
    }
  },
  created() {
    this.$emit('updateHeadersStatus', true);
    this.user=JSON.parse(localStorage.getItem("user")) ? JSON.parse(localStorage.getItem("user")) :null
    this.loadAddressByUserName();
  },methods:{
    handleEdit(index,row){
      this.form=row;
      this.dialogFormVisible=true
    },
    loadAddressByUserName(){
      if(this.user===null){
        ElMessage({
          showClose: true,
          message: '请先登陆',
          type: 'error',
        })
      }
      this.axios.get("/address/byUserId/" + this.user.id).then(res =>{
        //从新赋值
        console.log(res.data.data);
        this.address = res.data.data
      })
    },
    add(){
      this.form= {};
      this.dialogFormVisible=true
    },
    del(index,row){
      this.axios.delete("/address/" + row.id).then(res => {
        if (res.data.code === '200') {
          ElMessage({
            showClose: true,
            message: '删除成功',
            type: 'success',
          })
          this.loadAddressByUserName()
        } else {
          ElMessage({
            showClose: true,
            message: '删除失败',
            type: 'error',
          })
        }
      })
    },
    save() {
      this.dialogFormVisible=false
      this.form.userId=this.user.id
      this.axios.post("/address", this.form).then(res => {
        console.log("formformformformformform"+this.form);
        if (res.data) {
          ElMessage({
            showClose: true,
            message: '保存成功',
            type: 'success',
          })
          this.loadAddressByUserName()
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
.el-button--text {
  margin-right: 15px;
}
.el-select {
  width: 300px;
}
.el-input {
  width: 300px;
}
.dialog-footer button:first-child {
  margin-right: 10px;
}
</style>
