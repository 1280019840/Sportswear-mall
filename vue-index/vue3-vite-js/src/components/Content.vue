<template>
    <div class="a3" style="margin-bottom: 20px;">
                <!-- <p><b>更多</b></p>
						<hr />
						更多内容敬请期待！ -->

                <p><b>更多内容</b></p>
                <hr />

                <!-- 左边文案 -->
                <div style="float: left;width: 160px;line-height: 50px;">
                    <p style="line-height: 110px;">头像:</p>
                    <p>名称:</p>
                    <p>联系电话:</p>
                    <p>邮箱地址:</p>
                    <p>地区:</p>
                    <p>详细地址:</p>
                    <p>个性签名:</p>

                </div>
                <!-- 右边输入框 -->
                <div class="shuru">
                    <div><img src="../assets/img/头像.png"></div>
                    <div><input class="inputda" type="text" value="张三" /></div>
                    <div><input class="inputda" type="text" value="13712345678" /></div>
                    <div><input class="inputda" type="text" value="12345678@qq.com" /></div>
                    <div class="d-flex mb-3">
                        <div class=" flex-fill selcetc">
                            <select>
										<option value="null">省份</option>
										<option value="bj" selected = "selected">广州</option>
										<option value="sh">深圳</option>
										<option value="gz">北京</option>
										<option value="sz">上海</option>
									</select>
                        </div>
                        <div class=" flex-fill selcetc">
                            <select>
										<option value="null">地区</option>
										<option value="bj">天河区</option>
										<option value="sh">黄埔区</option>
										<option value="gz">越秀区</option>
										<option value="sz" selected = "selected">增城区</option>
									</select>
                        </div>
                        <div class=" flex-fill selcetc">
                            <select>
										<option value="null">街道</option>
										<option value="bj">友爱路</option>
										<option value="sh" selected = "selected">中新路</option>
										<option value="gz">广州大道</option>
										<option value="sz">嘉禾路</option>
									</select>
                        </div>
                    </div>

                    <div><input class="inputda" type="text" value="中新路农工商职业技术学院" /></div>
                    <div><input class="inputda" type="text" value="这个人很懒!什么都没有写" /></div>
                </div>
    </div>
</template>

<script>
import '@/assets/lib/css/bootstrap.min.css'
import '@/assets/IconFont/iconfont.css'
export default {
  name: 'Content',
  props: {
    msg: String
  }
}
</script>
<style scoped src="@/assets/css/personal.css"></style>

