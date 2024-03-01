<template>
  <div class="address-page">
    <h2>用户地址</h2>
    <button @click="addAddress" >添加地址</button>

    <ul>
      <li v-for="(address, index) in addresses" :key="index">
        <div>{{ address.name }}</div>
        <div>{{ address.address }}</div>
        <div>{{ address.phone }}</div>
        <button @click="editAddress(index)">编辑</button>
        <button @click="deleteAddress(index)">删除</button>
      </li>
    </ul>

    <!-- 编辑地址弹窗 -->
    <div v-if="showEditModal">
      <h3>编辑地址</h3>
      <form @submit.prevent="saveAddress">
        <label>
          姓名：
          <input type="text" v-model="editAddressData.name" required />
        </label>
        <label>
          地址：
          <input type="text" v-model="editAddressData.address" required />
        </label>
        <label>
          电话：
          <input type="text" v-model="editAddressData.phone" required />
        </label>
        <button type="submit">保存</button>
        <button @click="cancelEditAddress">取消</button>
      </form>
    </div>
  </div>
</template>

<script>
import { reactive } from 'vue';

export default {
  data() {
    return {
      addresses: [], // 地址数据
      showEditModal: false, // 是否显示编辑地址弹窗
      editAddressIndex: null, // 当前编辑的地址索引
      editAddressData: { // 当前编辑的地址数据
        name: '',
        address: '',
        phone: ''
      }
    };
  },
  mounted() {
    this.loadAddressByUser();
  },
  methods: {
    // 请求地址数据
    loadAddressByUser() {
      // 发起请求获取地址数据，这里使用示例数据
      this.addresses = [
      ];
    },

    // 添加地址
    addAddress() {
      this.editAddressData = {
        name: '',
        address: '',
        phone: ''
      };
      this.showEditModal = true;
    },

    // 编辑地址
    editAddress(index) {
      this.editAddressIndex = index;
      this.editAddressData = {
        ...this.addresses[index]
      };
      this.showEditModal = true;
    },

    // 删除地址
    deleteAddress(index) {
      this.addresses.splice(index, 1);
      // 发起请求保存修改后的地址数据，这里省略请求部分
    },

    // 保存地址
    saveAddress() {
      if (this.editAddressIndex !== null) {
        // 修改地址
        this.addresses.splice(this.editAddressIndex, 1, this.editAddressData);
      } else {
        // 新增地址
        this.addresses.push(this.editAddressData);
      }
      // 发起请求保存修改后的地址数据，这里省略请求部分

      this.cancelEditAddress();
    },

    // 取消编辑地址
    cancelEditAddress() {
      this.showEditModal = false;
      this.editAddressIndex = null;
    }
  }
};
</script>

<style>
.address-page {
  margin: 20px;
}

h2 {
  margin-bottom: 10px;
}

ul {
  padding-left: 0;
  list-style-type: none;
}

li {
  margin-bottom: 10px;
}

button {
  margin-right: 10px;
}
</style>
