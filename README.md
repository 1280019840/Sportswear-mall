# 项目介绍
* [编者序](README.md)
#### 系统开发采用了前后端分离：
本系统设计主要从对爱运动的人群和喜欢穿运动服装的人群需求入手，设计并实现一个基于Web平台，开设一家专业的运动服装商城是满足人们购物需求的有效方式，用户可以直接高效的浏览方式，系统帮助消费者节约了时间成本，使其在快节奏的生活中更加便利地进行购物。<br>

下面对用户和系统管理员的需求进行简要分析。用户的主要设计模块有用户登录注册模块，用户中心模块、地址管理模块、购物车模块、浏览商品模块，查看商品详情模块，查看商品评论模块，健身讨论区模块，商品分类模块。<br>

管理员的主要设计模块有管理员登录模块，商品管理模块，用户管理模块、健身讨论区管理模块，公告管理模块，统计数据模块，商品分类管理模块，轮播图管理模块。<br>


系统的角色分为用户和系统管理员，系统后端采用当前比较流行的SpringBoot框架，前端交互效果采用Vue框架，数据库采用MySQL，通过以上这三种关键技术来完成一个完整的系统，系统的技术实现
* SpringBoot
* Vue.js
* MySQL
* ElementUI
* Ajax
* MyBatisPlus
* Jwt等等
  
## 文件介绍
#### 后端SpringBoot文件如下：
* comm：用于设置请求返回的代码，以及设置返回结果等<br>
* config：用于设置系统的一些配置，比如MybatisPlus的配置等<br>
* controller：控制器文件夹<br>
* entity：实体文件夹<br>
* mapper：持久层接口文件夹<br>
* service：业务文件夹<br>
* utils：自定义的一些工具文件<br>

![image](https://github.com/1280019840/Sportswear-mall/raw/main/img/SpringBoot.png)

#### 用户端文件如下：

![image](https://github.com/1280019840/Sportswear-mall/raw/main/img/vue-user.png)

#### 后台管理员端文件如下：

![image](https://github.com/1280019840/Sportswear-mall/raw/main/img/vue-admin.png)

#### 数据库表如下：

![image](https://github.com/1280019840/Sportswear-mall/raw/main/img/MySQL.png)
