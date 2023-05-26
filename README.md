# livegoods
## 1.项目介绍
1. 这是一个微服务项目，使用的版本是SpringCloud 2022.0.2以及SpringBoot 3.0.5版本
2. 使用的技术，后端有SpringBoot + Spring Cloud + SpringMVC + SpringSecurity + MongoDB + Redis + ElasticSearch + Maven + FastDFS + Docker 前端： Vue + NodeJS
3. 项目结构，有以下几个部分
   - livegoods 父工程
   - livegoods_config_server 公共配置管理中心
   - livegoods_gateway 网关，负责服务路由转发
   - livegoods_banner 轮播图展示
   - livegoods_buyaction 预定商品并发送消息
   - livegoods_buyaction_message_consumer 预定后更新商品并消费消息
   - livegoods_buytime 查询商品预定时间
   - livegoods_cache_redis redis配置模块
   - livegoods_comment 新增或查询商品评论
   - livegoods_common 公共模块
   - livegoods_detail 查询商品详情
   - livegoods_hot_product 查询热销商品
   - livegoods_login 登录模块
   - livegoods_mongodb_dao mongodb连接配置
   - livegoods_order 查询用户订单
   - livegoods_recommendation 查询推荐商品
   - livegoods_search 搜索商品模块
4. 在login模块使用阿里云的短信验证
## 2.项目使用

1. 先在CentOS7虚拟机上，安装FastDFS、MongoDB、Redis、RabbitMQ以及Elasticsearch和kibana服务，
然后将Eureka模块的POM文件进行配置，修改成自己虚拟机的ip地址和port端口号，然后进行打包，然后打开docker2375端口，对eureka模块的docker的docker:build进行双击，即可将eureka上传至docker容器中，在docker容器进行启动。
2. 在虚拟机中安装完后，需要在对应的模块中配置主机地址等等，即使用ip和端口号，帐号密码等等
3. 将前端和MongoDB数据库中的文件解压后，将livegoods.vue在VSCODE中打开，然后在终端启动
4. 在MongoDB上面创建livegoods数据库，然后将数据库文件导入。
5. 短信验证模块，需要在login模块的utils包下的SMSUtils中，设置你的accessKeyId和accessKeySecret和setSignName以及你的VALIDATE_CODE。
6. 像配置文件是bootstrap.yml文件，需要自己创建一个配置仓库，创建后需要在config模块中进行配置，zfw-cloud-config-master.zip内的文件上传至gitee即可使用配置中心的配置文件，每个模块的bootstrap.yml文件都需要配置config配置中心。
7. 先在虚拟机运行docker上的eureka服务，然后运行其他服务。
8. 最后在IDEA中先启动Config配置模块，再开启Gateway网关模块，然后启动其他模块。

