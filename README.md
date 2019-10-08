#纯净的SSM框架
---


写在最前：感谢肖大侠近半个月无私的教导，制作出一个纯净的SSM框架并分离了配置文件，期望对初学者有帮助。
  
扩展性：我目前只做了最基础的MVC配置，静态资源放行，数据库配置。你可以在这基础上做任何天马行空的操作。

阅读方法： 使用IDEA阅读本文档，网站上阅读这玩意markdown有问题
***
- 使用到的包
- IDEA自己下载的Spring-MVC-4.3.18.RELEASE
- IDEA默认的Web Application勾选

+ druid-1.1.20                      dataSource
+ mybatis-3.5.2                     数据库操作类
+ mybatis-spring-2.0.2              spring支持
+ mysql-connector-java-8.0.17       连接mysql的支持类

* javax.servlet-api-4.0.1           java请求操作支持类
* jstl-1.2                          这货是模版语言标签支持
* taglibs-standard-impl-1.2.5       这货是模版语言标签支持
---
##了解顺序  

###1.目录结构   

- doc 一些文档文件，还有数据库文件
- lib 已经引入的jar包
- src 主程序目录:
    - main          主程序目录，包括一坨坨网站的逻辑和操作
    - resources     配置文件和SQL操作实现xml
- web   包含JSP、静态文件、配置文件:
    - WEB-INF emmm...都在这里面
    - index.jsp 项目入口JSP。懒得改
---
###2.配置文件
- 1.application.properties:
    - 这货里面我就放了数据库配置，可以增加其它配置，写法比较简单。
- 2.applicationContext.xml:
    - 这货里面放了数据库的基本配置方法和spring-mvc的注册和托管，记住用了哪些bean:
    * com.alibaba.druid.pool.DruidDataSource:
        - 数据库连接池
    * org.mybatis.spring.SqlSessionFactoryBean:
        - 找xml的
    * org.mybatis.spring.mapper.MapperScannerConfigurer
        - 扫描dao的
    * org.springframework.jdbc.datasource.DataSourceTransactionManager:
        - 注册事务（数据库操作的事务）
    - 里面还有两个mvc 和一个 tx的配置注意一下，有注释
- 3.dispatcher-servlet.xml:
    - 这货是mvc的配置文件，用来扫描控制器和配置视图层的路径（View）
    - 追加了一个放行
- 4.web.xml
    - 这货引入了上面的applicationContext.xml，并利用监听器使他生效。
        * 并且定义了路径分隔符
        * 并且在最开始的地方我追加了一个filter进行编码集声明
---
###3.开发顺序
- 1.Controller
    - 在main里面找到controller，这就是控制器的package
        * 写的时候需要声明这个类是@Controller
        * 每一个类有一个统一的@RequestMapping 也就是类的路由
        * 每一个方法有一个独立的@RequestMapping 方法的路由
        * 如果不声明@RequestMapping 将不会被访问到，但是可以调用
        * 正常的方法返回的字符串将会从dispatcher-servlet.xml的View配置自行拼接JSP文件名并返回JSP页面的内容
        * 如果不期望返回JSP页面，需要声明@ResponseBody即直接返回字符串到Response中。
        * 业务上的操作通常交给Service操作，所以需要声明变量 
        `@Autowired 
        private IndexService indexService;`
- 2.Dao
    - 找到dao这个package
        * 里面定义了一个个的对应表/功能的操作接口，即定义输入输出的参数
        * 这货将在后面的mapper里面实现。
        * 这货通常在Service里面被调用。
- 3.Entity
    - 这货就是抽象的实体，可以是一张表，或者是其它操作的单元，"面向对象"，面对疾风吧！
        * 写的时候注意别忘记加set get 方法。
        * 写的时候注意别忘记加set get 方法。
        * 写的时候注意别忘记加set get 方法。
- 4.Service
    - 这货是实现网站业务功能的操作类。
        * 其中Impl是具体实现的类，需要引用外面的对应接口
        * 通常会`@Autowired
        private UserDao userDao;`来引入需要做的数据操作dao
        * 外面放的Service接口用来定义方法的输入输出参数
- 5.resources.mapper.*.xml
    - 这货是你上面的dao的具体实现xml。
        * 需要指定namespace.
        * 需要指定返回类型resultType
- 6. 静态文件读取方法
    - 在WEB-INF下有个assets 我在dispatcher-servlet.xml里面设置了放行。会将这里面的静态文件放行直接送出。
---
###4. Do it yourself!
