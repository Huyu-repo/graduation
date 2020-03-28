# graduation 
       项目代码结构简易说明
       src/main/java
       cn.xsshome.mvcdo
       ├── common                                //相关常量类   
       ├── controller                            
       │       └── ai                            //AI模块Controller层       
       │       └── blog                           //博客模块Controller层          
       │       └── rest                            //小程序接口Controller层       
       │       └── system                        //系统管理模块Controller层  
       │       └── wechat                           //微信模块Controller层                      
       ├── dao                                   
       │       └── ai                            //AI模块Dao层       
       │       └── blog                           //博客模块Dao层               
       │       └── system                        //系统管理模块Dao层        
       ├── interceptor
       │       └── BDFactory                     //单例加载工厂类       
       │       └── MvcDoInterceptor              //拦截器
       ├── mapping                               
       │       └── ai                            //AI模块数据库操作层       
       │       └── blog                           //博客模块数据库操作层               
       │       └── system                        //系统管理模块数据库操作层        
       ├── pojo
       │       └── ai                            //AI模块对象Bean  dbo为数据库实体对象    po为接口返回的实体对象
       │       └── blog                           //博客模块数据库对象Bean               
       │       └── system                        //系统管理模块数据库对象Bean   
       ├── service
       │       └── ai                            //AI模块Service层
       │       └── blog                           //博客模块Service层               
       │       └── system                        //系统管理模块Service层  
       ├── util                                     //相关工具类
       └── vo                                    //接口返回的json实体对象
       项目配置文件说明
       src/main/resources
       ├── spring-mvc.xml                        //注解配置 静态资源 拦截等配置  
       ├── spring-mybatis.xml                    //数据库连接池等相关配置
       ├── spring.xml                               //注解配置
       ├── xai-constant.properties               //第三方以及验证相关常量配置文件
       ├── xai-jdbc.properties                      //数据库信息配置文件                            
       └── xai-log4j.properties                  //日志配置文件
