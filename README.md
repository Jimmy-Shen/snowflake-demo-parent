# snowflake-demo-parent
雪花算法，生成唯一ID

## 使用步骤 ##

#### - 创建数据库 ####
  > 启动脚本路径：
  > \snowflake-demo-parent\snowflake-spring\src\main\java\scripts\WORKER_NODE.sql
          
#### - 配置数据库 ####
  > 配置Demo工程的配置文件：
  > \snowflake-demo-parent\snowflake-demo\src\main\resources\application.properties

#### - 启动demo ####
  > 启动demo工程 ``snowflake-demo``

## 项目顺序 ##

- 第一步：snowflake-spring<br/>
    该工程是由百度[uid-generator](https://github.com/baidu/uid-generator.git)提供
    
- 第二步：snowflake-spring-boot-starter<br/>
    该工程是提供starter脚手架，达到开箱即用的目的<br/>
    这里的配置为：<br/>
    `1.` 年限长度为30bits：2的30次方=1,073,741,824秒=34.048年<br/>
    `2.` 机器Id长度为20bits：2的20次方=1,048,576次‬‬=约为105万次<br/>
    `3.` 每秒下的并发序列13bits：2的13次方=每秒8192个并发。<br/>
    如需更改，可以缩短或增长30bits，对应代码更改位置：
    > com.snowflake.springboot.config.UiAutoConfiguration
    > // 支持32年
    > cachedUidGenerator.setTimeBits(30);
    > cachedUidGenerator.setSeqBits(13);
    > // 可重启100万次
    > cachedUidGenerator.setWorkerBits(20);
                                                                                                                                                                                                                                                                                 
    总结：snowflake算法是64bit，默认首位是0<br/>
    
- 第三步：snowflake-demo<br/>
    该工程是业务demo，里面包含2个database配置，分别为业务数据库和雪花算法数据库