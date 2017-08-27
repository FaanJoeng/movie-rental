#### 说明
 1. 数据库配置文件位置 `src/main/resources/jdbc.properties`
 2. 新插入数据`store_id`默认为`1`，删除通过置`customer`表`active`字段为`0`实现

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

#### 返回码约定
* `200` 处理成功默认返回
* `300` 处理失败默认返回 
* `400` 服务器异常
  
  