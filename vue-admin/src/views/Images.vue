<template>
  <div>

    <div style="margin: 10px 0">
      <el-upload :action="'http://'+ serverIp +':8082/images'" :show-file-list="false"
                 :on-success="handleFileUploadSuccess" style="display: inline-block">
        <el-button type="success" round size="medium" class="ml-5">上传图片 <i class="el-icon-top"></i></el-button>
      </el-upload>
      <el-popconfirm
          class="ml-5"
          confirm-button-text='确定'
          cancel-button-text='我再想想'
          icon="el-icon-info"
          icon-color="red"
          title="您确定批量删除所选项吗？"
          @confirm="delBatch">
        <el-button type="danger" round size="medium" class="ml-5" slot="reference">批量删除</el-button>
      </el-popconfirm>
    </div>
    <el-table :data="tableData"   :header-cell-class-name="headerBg"
              @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55"></el-table-column>
      <el-table-column prop="id" label="ID" width="100"></el-table-column>
      <el-table-column prop="url" label="滚播图" align="center">
        <template slot-scope="scope">
          <img :src="scope.row.url" :min-width="70" :height="70"/>
        </template>
      </el-table-column>
      <el-table-column prop="createTime" label="创建时间"></el-table-column>
      <el-table-column label="操作" width="200" align="center">
        <template slot-scope="scope">
          <el-button type="primary" @click="handleEdit(scope.row)">编辑 </el-button>
          <el-popconfirm
              class="ml-5"
              confirm-button-text='确定'
              cancel-button-text='我再想想'
              icon="el-icon-info"
              icon-color="red"
              title="您确定删除吗？"
              @confirm="del(scope.row.id)">
            <el-button type="danger" slot="reference">删除 </el-button>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>
    <div class="block" style="padding: 10px 0;text-align: center">
      <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="pageNum"
          :page-sizes="[5, 10, 20, 30]"
          :page-size="pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total">
      </el-pagination>
    </div>
    <el-dialog title="图片信息" :visible.sync="dialogFormVisible" width="30%">

      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="save">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {serverIp} from "../../public/config";

export default {
  name: "User",
  data() {
    return {
      serverIp:serverIp,
      tableData: [],
      total: 0,
      pageNum: 1,
      pageSize: 10,
      name: "",
      form: {},
      multipleSelection: [],
      dialogFormVisible: false,
      headerBg: 'headerBg',
    }
  },
  created() {
    // 请求分页查询数据
    this.load()
  },
  methods: {
    handleFileUploadSuccess() {
      // console.log(res)
      this.$message.success("上传成功!")
      this.load()
    },
    handleSelectionChange(val) {
      this.multipleSelection = val
    },
    delBatch() {
      let ids = this.multipleSelection.map(v => v.id)  //[{},{},{}]==>[1,2,3]
      this.request.post("/images/del/batch", ids).then(res => {
        if (res.code === '200') {
          this.$message.success("批量删除成功！")
          this.load()
        } else {
          this.$message.error("批量删除失败！")
        }
      })
    },
    del(id) {
      this.request.delete("/images/" + id).then(res => {
        if (res.code === '200') {
          this.$message.success("删除成功！")
          this.load()
        } else {
          this.$message.error("删除失败！")
        }
      })
    },
    handleEdit(row) {
      this.form = row
      this.dialogFormVisible = true
    },
    handleAdd() {
      this.dialogFormVisible = true
      this.form = {}
    },
    save() {
      this.request.post("/images", this.form).then(res => {
        if (res.code === '200') {
          this.$message.success("保存成功！")
          this.dialogFormVisible = false
          this.load()
        } else {
          this.$message.error("保存失败！")
        }
      })
    },
    load() {
      this.request.get("/images/page", {
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          name: this.name
        }
      })
          .then(res => {
            this.tableData = res.data.records
            this.total = res.data.total
          })
    },
    reset() {
      this.name = ""
      this.load()
    },
    handleSizeChange(pageSize) {
      this.pageSize = pageSize
      this.load()
    },
    handleCurrentChange(pageNum) {
      this.pageNum = pageNum
      this.load()
    },

  }
}
</script>

<style>
.headerBg {
  background: rgb(236, 238, 239) !important;
}
</style>