<template>
    <div class="a1" style="margin-bottom: 20px;height: 380px;">
                <p><b>地址管理</b></p>
                <hr />

                 <el-table :data="filterTableData" style="width: 100%"  :key="KeyDate" >
                    <el-table-column label="Name" prop="name" />
                    <el-table-column prop="city" label="City" width="200px" />
                    <el-table-column prop="address" label="Address" width="320px"/>
                    <el-table-column align="right">
                    <template #header>
                      <el-input v-model="search" size="small" placeholder="Type to search" />
                    </template>
                    <template #default="scope">
                      <el-button text @click="dialogFormVisible = true">
                      <!-- open a Form nested Dialog -->
                      <el-button size="small" @click="handleEdit(scope.$index, scope.row)" style="width: 50px;">Edit</el-button>
                    </el-button>
       
        
          <el-popconfirm
    confirm-button-text="Yes" confirm-button-type="info" 
    cancel-button-text="No" cancel-button-type="plain"
    :icon="InfoFilled"
    icon-color="#626AEF"
    title="Are you sure to delete this?"
    @confirm.prevent="confirmEvent(scope.$index, scope.row)"
    @cancel="cancelEvent"
  >
    <template #reference>
      <el-button style="width: 50px;height: 24px;">
        <!-- <el-button size="small"  type="danger" @click="handleDelete(scope.$index, scope.row)" style="width: 50px;" >Delete</el-button> -->
        <el-button size="small"  type="danger" style="width: 50px;" >Delete</el-button>
      </el-button>
    </template>
  </el-popconfirm>
          
      </template>
    </el-table-column>
    
  </el-table>


   <!-- Form -->
  

  <el-dialog v-model="dialogFormVisible" title="Shipping address" >
    <el-form :model="form">
      <el-form-item label="Promotion name" :label-width="formLabelWidth">
        <el-input v-model="form.name" autocomplete="off" style="width: 400px;" />
      </el-form-item>
      <el-form-item label="Zones" :label-width="formLabelWidth">
        <el-select v-model="form.region" placeholder="Please select a zone" style="width: 400px;">
          <el-option label="Zone No.1" value="shanghai" />
          <el-option label="Zone No.2" value="beijing" />
        </el-select>
      </el-form-item>
      <el-form-item label="Promotion name" :label-width="formLabelWidth">
        <el-input v-model="form.address" autocomplete="off"  style="width: 400px;"/>
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogFormVisible = false" style="width:100px;">Cancel</el-button>
        <el-button type="info" @click="dialogFormVisible = false" style="width: 100px;">
          Confirm
        </el-button>
      </span>
    </template>
  </el-dialog>
    </div>
</template>

<script lang="ts" setup>
import '@/assets/lib/css/bootstrap.min.css'
import '@/assets/IconFont/iconfont.css'

import { computed, ref,reactive,onUpdated } from 'vue'
import { InfoFilled } from '@element-plus/icons-vue'

interface User {
  city: string
  name: string
  address: string
}


const search = ref('')
const filterTableData = computed(() =>
  tableData.filter(
    (data) =>
      !search.value ||
      data.name.toLowerCase().includes(search.value.toLowerCase())
  )
)
const handleEdit = (index: number, row: User) => {
  form.name = row.name
  form.region = row.city
  form.address = row.address
  console.log(index, row)
}
const handleDelete = (index: number, row: User) => {
  // tableData.value.splice(index,1)
  console.log(tableData);
  console.log(index, row)
}


const tableData: User[] = [
  {
    name: 'Tom',
    city: '2016-05-03',
    address: 'No. 189, Grove St, Los Angeles',
  },
  {
    name: 'John',
    city: '2016-05-03',
    address: 'No. 189, Grove St, Los Angeles',
  },
  {

    name: 'Morgan',
    city: '2016-05-03',
    address: 'No. 189, Grove St, Los Angeles',
  },
  {
    name: 'Jessy',
    city: '2016-05-03',
    address: 'No. 189, Grove St, Los Angeles',
  },
]

const form = reactive({
  name: '',
  region: '',
  address:'',
  date1: '',
  date2: '',
  delivery: false,
  type: [],
  resource: '',
  desc: '',
})
const dialogFormVisible = ref(false)
const formLabelWidth = '140px'

const KeyDate = {
  Date: ref(0)
}
const confirmEvent = (index: number, row: User) => {
  // console.log(tableData.values);
  console.log(index,row);
  
  
  console.log('confirm!')
}
const cancelEvent = () => {
  console.log('cancel!')
}
</script>


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
