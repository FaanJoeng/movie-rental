#### 说明
 1. 数据库配置文件位置 `src/main/resources/jdbc.properties`


#### API 
* 主页 
  * `/` 展示 Customer管理 及 Film设置 页
* 登录注销相关
  * `GET /session` 返回登录页 
  * `POST /session` 处理登录信息
  * `DELETE /session` 注销登录
  
* 客户业务处理
  * `GET /customers` 获取用户列表
  * `POST /customers` 添加用户信息
  * `PUT /customers/{id}` 更新用户信息 
  * `DELETE /customers/{id}` 删除用户信息

* 其他
  * `GET /address` 获取地址列表
  
  
  #### 涉及技术
  
  