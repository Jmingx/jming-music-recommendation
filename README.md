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

# TODO
### 问题描述：
爬取数据后保存数据库失败
### 原因分析：
在controller加了事务
### 解决方法：
移除事务


