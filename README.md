# livegoods
## 项目介绍
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


