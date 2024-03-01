<template>
  <div>
    <div style="margin: 10px 0">
      <el-input style="width: 260px" placeholder="请输入名称" suffix-icon="el-icon-search"
                v-model="username"></el-input>
      <el-button class="ml-5" type="success" round size="medium" @click="load">搜索</el-button>
      <el-button style="margin-left: 5px" round size="medium" type="primary" @click="reset">重置</el-button>
    </div>
    <div style="margin: 10px 0">
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
      <el-table-column prop="id" label="ID" ></el-table-column>
      <el-table-column prop="ono" label="订单ID" ></el-table-column>
      <el-table-column prop="userId" label="用户ID" ></el-table-column>
      <el-table-column prop="username" label="用户名称" ></el-table-column>
      <el-table-column prop="num" label="件数" ></el-table-column>
      <el-table-column prop="total" label="总金额" ></el-table-column>
      <el-table-column prop="pay" label="支付方式"></el-table-column>
      <el-table-column prop="phone" label="手机号"></el-table-column>
      <el-table-column prop="email" label="邮箱"></el-table-column>
      <el-table-column prop="express" label="快递"></el-table-column>
      <el-table-column prop="state" label="状态"></el-table-column>
      <el-table-column prop="createTime" label="创建时间"></el-table-column>
      <el-table-column prop="remark" label="备注"></el-table-column>

      <el-table-column label="操作" width="200" align="center">
        <template slot-scope="scope">
          <el-button type="primary" @click="handleEdit(scope.row)">改变状态 </el-button>

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

    <el-dialog title="用户信息" :visible.sync="dialogFormVisible" width="30%">
      <el-form label-width="80px" size="small">
        <el-form-item label="订单ID">
          <el-input v-model="form.id" autocomplete="off" disabled></el-input>
        </el-form-item>
        <el-form-item label="选择状态">
          <el-select clearable placeholder="请选择" v-model="form.state" style="width: 100%">
            <el-option label="待发货" value="待发货" />
            <el-option label="已发货" value="已发货" />
            <el-option label="已完成" value="已完成" />
            <el-option label="已取消" value="已取消" />
          </el-select>
        </el-form-item>

      </el-form>
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
      username: "",
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
    handleEdit(row) {
      this.form = row
      this.dialogFormVisible = true
    },
    handleSelectionChange(val) {
      this.multipleSelection = val
    },
    delBatch() {
      let ids = this.multipleSelection.map(v => v.id)  //[{},{},{}]==>[1,2,3]
      this.request.post("/order/del/batch", ids).then(res => {
        if (res.code === '200') {
          this.$message.success("批量删除成功！")
          this.load()
        } else {
          this.$message.error("批量删除失败！")
        }
      })
    },save() {
      this.request.post("/order/update", this.form).then(res => {
        if (res.code === '200') {
          this.$message.success("保存成功！")
          this.dialogFormVisible = false
          this.load()
        } else {
          this.$message.error("保存失败！")
        }
      })
    },
    del(id) {
      this.request.delete("/order/" + id).then(res => {
        if (res.code === '200') {
          this.$message.success("删除成功！")
          this.load()
        } else {
          this.$message.error("删除失败！")
        }
      })
    },

    load() {
      this.request.get("/order/page", {
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          username: this.username
        }
      })
          .then(res => {
            this.tableData = res.data.records
            this.total = res.data.total
          })
    },
    reset() {
      this.username = ""
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