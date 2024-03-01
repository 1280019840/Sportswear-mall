<template>
  <!-- 页尾部 -->
			<div style="background-color: #363636;">
			<div class="container footer">
				<ul class="nav justify-content-center">
					<li class="nav-item">
						<a class="nav-link active" href="#">
							<span class="iconfont icon-mianfeizixunfuwu" aria-hidden="true"></span>联系我们
						</a>
					</li>
					<li class="nav-item">
						<a class="nav-link active" href="#">
							<span class="iconfont icon-dingwei" aria-hidden="true"></span>站点地图
						</a>
					</li>
					<li class="nav-item">
						<a class="nav-link active" href="#">
							<span class="iconfont icon-QA" aria-hidden="true"></span>服务声明
						</a>
					</li>
				</ul>
				<div class="text-center">
					<!-- <p>Copyrights &copy; 2020 Bootstrapa响应式运动网站 | 版权所有 | <a href="#">广东广州增城 -->
					<p>Copyrights &copy; 2023 广东科技学院 | 版权所有 | <a href="#">广东省珠海市
					</a></p>
				</div>
			</div>
			</div>
			<div style="background-color: #000; height: 30px;"></div>
</template>

<script>
import '@/assets/css/index.css'
import '@/assets/lib/css/bootstrap.min.css'
import '@/assets/IconFont/iconfont.css'
export default {
  name: 'Footer',
  props: {
    msg: String
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
