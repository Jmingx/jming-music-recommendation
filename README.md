# 工程简介
这是一个音乐推荐系统

# 模块介绍
## music-common
项目基础依赖库

## music-spider
音乐爬虫模块

# 算法介绍

# 错误日志
## 模块：music-spider
### 1. 描述：
Error creating bean with name 'basicAlbumServiceImpl': Unsatisfied dependency 
### 原因分析：
Spring boot集成mybatis plus报错
### 解决方法：
启动类中有加注解mapperscan

### 2. 描述：
Test ignored.
### 原因分析：
### 解决方法：

### 3. 描述：
java.lang.TypeNotPresentException: Type [unknown] not present
### 原因分析：
MapperScan所需要的包导入错误
应该导入org.mybatis.spring.annotation下的MapperScan
### 解决方法：
导入mybatis-spring-boot-starter依赖

### 4. 描述：
爬取专辑时用jsoup无法获取信息
### 原因分析
debug过程中发现albumId为null，导致返回的是https://www.kugou.com/album/null.html页面
### 解决方法
修改代码，确保albumId准确

### 5. 描述：
爬取数据过程中出现'img_address' doesn't have a default value
### 原因分析：
出现该错误是因为在表中设置了该字段为Not Null,又没有设置该字段的默认值
### 解决方法：
在表中设置为可空，或设置默认值

### 6. 问题描述：
爬取数据后保存数据库失败
### 原因分析：
在controller加了事务
### 解决方法：
移除事务

### 7. 问题描述：
某个接口，需要同时接收文件和实体，如何解决
### 原因分析：
contentType=json时并不能携带文件，想调用MultipartFile只能通过form表单的形式调用
### 解决方法：
前端：通过表单形式提交，把json转换成字符串形式
后端：@RequestParam获取表单数据，json字符串需要用fastjson进行转换，同时获取MultipartFile操作

### 8. 问题描述：
测试上传文件时出现跨域问题
### 原因分析：
浏览器限制，前端请求路径写错
### 解决方法：
添加了Cors过滤器

### 9. 问题描述：
vue前端的报错Uncaught TypeError: Cannot read properties of undefined (reading 'validate')
### 原因分析：
<el-form-item label="封面地址" prop="files">没有绑定好ref
### 解决方法：
<el-form-item ref="upload_attach_item" label="封面地址" prop="files">绑定好ref即可

### 10. 问题描述：
前端vue报错无法识别loading模块
### 原因分析：
没有导入loading模块
### 解决方法：
创建loading模块，导入

### 11. 问题描述：
Cannot read properties of undefined (reading 'post')
### 原因分析：
vue没有引入axios
### 解决方法：
下载依赖 $ npm install axios --save
注册到main.js 
import axios from ‘axios’
Vue.prototype.$axios = axios

### 12. 问题描述：
axios没有指定到正确的路径
### 原因分析：
axios默认为项目路径发送请求
### 解决方法：
修改axios的默认请求路径
配置请求根路径
axios.defaults.baseURL = 'http://localhost:8080/music-admin'

### 13. 问题描述：
修改完vue之后返回401
### 原因分析：
没有授权，因为修改完发送请求后没有把对应的token发送出去,重新修改发送请求方法，带上token
### 解决方法：
重新装配一下axios，携带上参数并配置其他东西，封装到@vue/utils/service下
1. 封装serviceRequest作为上传表单的工具；
2. 在main.js中引入
3. 像调用http一样调用service即可

### 14. 问题描述：
vue el-switch没有按正确要求显示，当showStatus=1时开启，否则关闭
### 原因分析：
绑定失败导致
### 解决方法：
如下绑定
:active-value=1
:inactive-value=0