## 电影租赁管理系统
#### 配置说明
 1. 数据库配置文件位置 `src/main/resources/jdbc.properties`
 2. 新插入数据`store_id`默认为`1`，删除通过置`customer`表`active`字段为`0`实现

#### 运行说明
在本项目根目录下运行 
1. `mvn clean`
2. `mvn compile`
3. `mvn jetty:run`

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
1. 成功
* `200` 处理成功默认
* `210` 登录成功
* `211` 注销成功
* `220` 获取客户列表成功
* `221` 新增客户成功
* `222` 更新客户成功
* `223` 删除客户成功
* `230` 获取地址列表成功

2. 失败
* `300` 处理失败默认
* `310` 用户不存在
* `311` 用户名错误
* `312` 注销失败
* `320` 客户列表为空
* `321` 新增用户失败
* `322` 更新用户失败
* `323` 删除用户失败

3. 服务器异常
* `400` 服务器异常
  
  