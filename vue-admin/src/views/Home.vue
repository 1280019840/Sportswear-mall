<template>
  <div style="display: flex;justify-content: left;">
    <!-- 第一个饼图 -->
    <div ref="pieChart1" style="width: 400px; height: 400px;margin-left: 100px"></div>

    <!-- 第二个饼图 -->
    <div ref="pieChart2" style="width: 400px; height: 400px;margin-left: 100px"></div>
  </div>
</template>

<script>

import * as echarts from 'echarts'

export default {
  name: "Home",
  data() {
    return {
      // 数据示例，你可以根据实际情况修改数据
      data: [],
      data2: [
        { value: 1048, name: '上衣' },
        { value: 735, name: '裤子' },
        { value: 580, name: '短袖' },
        { value: 484, name: '休闲装' },
      ]
    };
  },
  created() {
    this.request.get("/order/dataOrderAndGood", this.form).then(res => {
      if (res.code === '200') {
        this.data = res.data
        this.initPieChart(this.$refs.pieChart1, this.data);
      } else {
        this.$message.error("加载数据失败！")
      }
    })

    this.request.get("/order/dataGoodType", this.form).then(res => {
      if (res.code === '200') {
        this.data2 = res.data
        this.initPieChart2(this.$refs.pieChart2, this.data2);
      } else {
        this.$message.error("加载数据失败！")
      }
    })
  },
  mounted() {

  },
  methods: {
    initPieChart(container, data) {
      // 使用 ECharts 初始化饼图
      const chart = echarts.init(container);

      // 配置项
      const options = {
        title: {
          text: '库存订单对比',
          left: 'center',
        },
        tooltip: {
          trigger: 'item',
          formatter: '{a} <br/>{b}: {c} ({d}%)',
        },
        legend: {
          orient: 'vertical',
          left: 'left',
          data: data.map(item => item.name),
        },
        series: [
          {
            name: '库存订单对比',
            type: 'pie',
            radius: '50%',
            center: ['50%', '60%'],
            data: data,
            emphasis: {
              itemStyle: {
                shadowBlur: 10,
                shadowOffsetX: 0,
                shadowColor: 'rgba(1, 232, 0, 0.5)',
              },
            },
          },
        ],
      };

      // 使用刚指定的配置项和数据显示图表。
      chart.setOption(options);
    },initPieChart2(container, data) {
      // 使用 ECharts 初始化饼图
      const chart = echarts.init(container);

      // 配置项
      const option = {
        title: {
          text: '各分类订单量',
          left: 'center',
        },
        tooltip: {
          trigger: 'item'
        },
        legend: {
          top: '5%',
          left: 'center'
        },
        series: [
          {
            type: 'pie',
            radius: ['40%', '70%'],
            avoidLabelOverlap: false,
            data: data,
            itemStyle: {
              borderRadius: 10,
              borderColor: '#fff',
              borderWidth: 2
            },
            label: {
              show: false,
              position: 'center'
            },
            emphasis: {
              label: {
                show: true,
                fontSize: 40,
                fontWeight: 'bold'
              }
            },
            labelLine: {
              show: false
            }
          }
        ]
      };
      // 使用刚指定的配置项和数据显示图表。
      chart.setOption(option);
    },
  },
};


</script>

<style scoped>

</style>
