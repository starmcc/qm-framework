(window.webpackJsonp=window.webpackJsonp||[]).push([[7],{188:function(t,a,s){"use strict";s.r(a);var n=s(0),e=Object(n.a)({},function(){var t=this,a=t.$createElement,s=t._self._c||a;return s("ContentSlotsDistributor",{attrs:{"slot-key":t.$parent.slotKey}},[s("h1",{attrs:{id:"配置文件模板"}},[s("a",{staticClass:"header-anchor",attrs:{href:"#配置文件模板","aria-hidden":"true"}},[t._v("#")]),t._v(" 配置文件模板")]),t._v(" "),s("blockquote",[s("p",[t._v("配置文件分为框架配置文件和数据源配置文件")])]),t._v(" "),s("h2",{attrs:{id:"框架配置文件"}},[s("a",{staticClass:"header-anchor",attrs:{href:"#框架配置文件","aria-hidden":"true"}},[t._v("#")]),t._v(" 框架配置文件")]),t._v(" "),s("div",{staticClass:"language-properties extra-class"},[s("pre",{pre:!0,attrs:{class:"language-properties"}},[s("code",[s("span",{pre:!0,attrs:{class:"token comment"}},[t._v("#※※※※※※※※※AES加密设置※※※※※※※※※")]),t._v("\n"),s("span",{pre:!0,attrs:{class:"token comment"}},[t._v("#是否启用aes对称加密传输")]),t._v("\n"),s("span",{pre:!0,attrs:{class:"token attr-name"}},[t._v("aes.start")]),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("=")]),s("span",{pre:!0,attrs:{class:"token attr-value"}},[t._v("false")]),t._v("\n"),s("span",{pre:!0,attrs:{class:"token comment"}},[t._v("#aes秘钥")]),t._v("\n"),s("span",{pre:!0,attrs:{class:"token attr-name"}},[t._v("aes.key")]),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("=")]),s("span",{pre:!0,attrs:{class:"token attr-value"}},[t._v("ohiah2019sDShdieub51h8910s")]),t._v("\n"),s("span",{pre:!0,attrs:{class:"token comment"}},[t._v("#统一使用的编码方式")]),t._v("\n"),s("span",{pre:!0,attrs:{class:"token attr-name"}},[t._v("aes.encoding")]),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("=")]),s("span",{pre:!0,attrs:{class:"token attr-value"}},[t._v("UTF-8")]),t._v("\n"),s("span",{pre:!0,attrs:{class:"token comment"}},[t._v("#加密次数")]),t._v("\n"),s("span",{pre:!0,attrs:{class:"token attr-name"}},[t._v("aes.number")]),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("=")]),s("span",{pre:!0,attrs:{class:"token attr-value"}},[t._v("1")]),t._v("\n"),s("span",{pre:!0,attrs:{class:"token comment"}},[t._v("#※※※※※※※※※请求响应设置※※※※※※※※※")]),t._v("\n"),s("span",{pre:!0,attrs:{class:"token comment"}},[t._v("#请求数据时，根据该key名解析数据(rest风格)")]),t._v("\n"),s("span",{pre:!0,attrs:{class:"token attr-name"}},[t._v("body.request.key")]),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("=")]),s("span",{pre:!0,attrs:{class:"token attr-value"}},[t._v("value")]),t._v("\n"),s("span",{pre:!0,attrs:{class:"token comment"}},[t._v("#返回数据时，使用的最外层key名(rest风格)")]),t._v("\n"),s("span",{pre:!0,attrs:{class:"token attr-name"}},[t._v("body.response.key")]),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("=")]),s("span",{pre:!0,attrs:{class:"token attr-value"}},[t._v("value")]),t._v("\n"),s("span",{pre:!0,attrs:{class:"token comment"}},[t._v("#返回数据时，默认message的语言 EN/CN")]),t._v("\n"),s("span",{pre:!0,attrs:{class:"token attr-name"}},[t._v("body.response.message.lang")]),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("=")]),s("span",{pre:!0,attrs:{class:"token attr-value"}},[t._v("CN")]),t._v("\n"),s("span",{pre:!0,attrs:{class:"token comment"}},[t._v("#※※※※※※※※※版本控制※※※※※※※※※")]),t._v("\n"),s("span",{pre:!0,attrs:{class:"token comment"}},[t._v("#是否开启版本控制(ture时,每个请求需在header带上version参数,参数值version)")]),t._v("\n"),s("span",{pre:!0,attrs:{class:"token attr-name"}},[t._v("version.start")]),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("=")]),s("span",{pre:!0,attrs:{class:"token attr-value"}},[t._v("false")]),t._v("\n"),s("span",{pre:!0,attrs:{class:"token comment"}},[t._v("#系统目前版本编号")]),t._v("\n"),s("span",{pre:!0,attrs:{class:"token attr-name"}},[t._v("version.now")]),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("=")]),s("span",{pre:!0,attrs:{class:"token attr-value"}},[t._v("1.0.0")]),t._v("\n"),s("span",{pre:!0,attrs:{class:"token comment"}},[t._v("#系统容忍请求版本编号(默认允许当前版本)")]),t._v("\n"),s("span",{pre:!0,attrs:{class:"token attr-name"}},[t._v("version.allows-[0]")]),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(":")]),s("span",{pre:!0,attrs:{class:"token attr-value"}},[t._v("0.9.9")]),t._v("\n"),s("span",{pre:!0,attrs:{class:"token comment"}},[t._v("#※※※※※※※※※特殊请求过滤※※※※※※※※※")]),t._v("\n"),s("span",{pre:!0,attrs:{class:"token comment"}},[t._v("#特殊请求不进行解析(包括版本控制和解析json等)")]),t._v("\n"),s("span",{pre:!0,attrs:{class:"token comment"}},[t._v("#该配置主要排除第三方API调用接口时特殊请求而框架自动解析json的问题")]),t._v("\n"),s("span",{pre:!0,attrs:{class:"token comment"}},[t._v("#适用于动态配置，例：/user/**")]),t._v("\n"),s("span",{pre:!0,attrs:{class:"token attr-name"}},[t._v("request.special.uri-[0]")]),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("=")]),t._v("\n")])])]),s("p",[t._v("上述配置为框架配置基础，贯穿整个框架的相关封装。")]),t._v(" "),s("ul",[s("li",[s("p",[s("a",{attrs:{href:"https://github.com/starmcc/qm-framework/wiki/Aes",target:"_blank",rel:"noopener noreferrer"}},[t._v("AES 双向对称加密"),s("OutboundLink")],1)])]),t._v(" "),s("li",[s("p",[s("a",{attrs:{href:"https://github.com/starmcc/qm-framework/wiki/ReqRes",target:"_blank",rel:"noopener noreferrer"}},[t._v("请求响应消息"),s("OutboundLink")],1)])]),t._v(" "),s("li",[s("p",[s("a",{attrs:{href:"https://github.com/starmcc/qm-framework/wiki/Version",target:"_blank",rel:"noopener noreferrer"}},[t._v("版本控制"),s("OutboundLink")],1)])]),t._v(" "),s("li",[s("p",[s("a",{attrs:{href:"https://github.com/starmcc/qm-framework/wiki/RequestFilter",target:"_blank",rel:"noopener noreferrer"}},[t._v("特殊请求过滤"),s("OutboundLink")],1)])])]),t._v(" "),s("h2",{attrs:{id:"数据源配置文件"}},[s("a",{staticClass:"header-anchor",attrs:{href:"#数据源配置文件","aria-hidden":"true"}},[t._v("#")]),t._v(" 数据源配置文件")]),t._v(" "),s("div",{staticClass:"language-properties extra-class"},[s("pre",{pre:!0,attrs:{class:"language-properties"}},[s("code",[s("span",{pre:!0,attrs:{class:"token comment"}},[t._v("#※※※※※※※※※※※※※※※※※※ 数据源配置 ※※※※※※※※※※※※※※※※※※")]),t._v("\n"),s("span",{pre:!0,attrs:{class:"token comment"}},[t._v("# 驱动名")]),t._v("\n"),s("span",{pre:!0,attrs:{class:"token attr-name"}},[t._v("driver-class-name")]),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("=")]),s("span",{pre:!0,attrs:{class:"token attr-value"}},[t._v("com.mysql.cj.jdbc.Driver")]),t._v("\n"),s("span",{pre:!0,attrs:{class:"token comment"}},[t._v("# 连接地址")]),t._v("\n"),s("span",{pre:!0,attrs:{class:"token attr-name"}},[t._v("url")]),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("=")]),s("span",{pre:!0,attrs:{class:"token attr-value"}},[t._v("jdbc:mysql://localhost:3306/test")]),t._v("\n"),s("span",{pre:!0,attrs:{class:"token comment"}},[t._v("# 用户名")]),t._v("\n"),s("span",{pre:!0,attrs:{class:"token attr-name"}},[t._v("username")]),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("=")]),s("span",{pre:!0,attrs:{class:"token attr-value"}},[t._v("root")]),t._v("\n"),s("span",{pre:!0,attrs:{class:"token comment"}},[t._v("# 密码")]),t._v("\n"),s("span",{pre:!0,attrs:{class:"token attr-name"}},[t._v("password")]),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("=")]),s("span",{pre:!0,attrs:{class:"token attr-value"}},[t._v("123")]),t._v("\n\n"),s("span",{pre:!0,attrs:{class:"token comment"}},[t._v("#※※※※※※※※※※※※※※※※※※ DRUID连接池配置 ※※※※※※※※※※※※※※※※※※")]),t._v("\n"),s("span",{pre:!0,attrs:{class:"token comment"}},[t._v("# 下面为连接池的补充设置，应用到上面所有数据源中")]),t._v("\n"),s("span",{pre:!0,attrs:{class:"token comment"}},[t._v("# 初始化大小，最小，最大")]),t._v("\n"),s("span",{pre:!0,attrs:{class:"token attr-name"}},[t._v("druid.initialSize")]),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("=")]),s("span",{pre:!0,attrs:{class:"token attr-value"}},[t._v("5")]),t._v("\n"),s("span",{pre:!0,attrs:{class:"token attr-name"}},[t._v("druid.minIdle")]),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("=")]),s("span",{pre:!0,attrs:{class:"token attr-value"}},[t._v("5")]),t._v("\n"),s("span",{pre:!0,attrs:{class:"token attr-name"}},[t._v("druid.maxActive")]),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("=")]),s("span",{pre:!0,attrs:{class:"token attr-value"}},[t._v("20")]),t._v("\n"),s("span",{pre:!0,attrs:{class:"token comment"}},[t._v("# 配置获取连接等待超时的时间")]),t._v("\n"),s("span",{pre:!0,attrs:{class:"token attr-name"}},[t._v("druid.maxWait")]),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("=")]),s("span",{pre:!0,attrs:{class:"token attr-value"}},[t._v("60000")]),t._v("\n"),s("span",{pre:!0,attrs:{class:"token comment"}},[t._v("# 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒")]),t._v("\n"),s("span",{pre:!0,attrs:{class:"token attr-name"}},[t._v("druid.timeBetweenEvictionRunsMillis")]),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("=")]),s("span",{pre:!0,attrs:{class:"token attr-value"}},[t._v("60000")]),t._v("\n"),s("span",{pre:!0,attrs:{class:"token comment"}},[t._v("# 配置一个连接在池中最小生存的时间，单位是毫秒")]),t._v("\n"),s("span",{pre:!0,attrs:{class:"token attr-name"}},[t._v("druid.minEvictableIdleTimeMillis")]),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("=")]),s("span",{pre:!0,attrs:{class:"token attr-value"}},[t._v("300000")]),t._v("\n"),s("span",{pre:!0,attrs:{class:"token attr-name"}},[t._v("druid.validationQuery")]),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("=")]),s("span",{pre:!0,attrs:{class:"token attr-value"}},[t._v("SELECT 1 FROM DUAL")]),t._v("\n"),s("span",{pre:!0,attrs:{class:"token attr-name"}},[t._v("druid.testWhileIdle")]),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("=")]),s("span",{pre:!0,attrs:{class:"token attr-value"}},[t._v("true")]),t._v("\n"),s("span",{pre:!0,attrs:{class:"token attr-name"}},[t._v("druid.testOnBorrow")]),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("=")]),s("span",{pre:!0,attrs:{class:"token attr-value"}},[t._v("false")]),t._v("\n"),s("span",{pre:!0,attrs:{class:"token attr-name"}},[t._v("druid.testOnReturn")]),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("=")]),s("span",{pre:!0,attrs:{class:"token attr-value"}},[t._v("false")]),t._v("\n"),s("span",{pre:!0,attrs:{class:"token comment"}},[t._v("# 打开PSCache，并且指定每个连接上PSCache的大小")]),t._v("\n"),s("span",{pre:!0,attrs:{class:"token attr-name"}},[t._v("druid.poolPreparedStatements")]),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("=")]),s("span",{pre:!0,attrs:{class:"token attr-value"}},[t._v("true")]),t._v("\n"),s("span",{pre:!0,attrs:{class:"token attr-name"}},[t._v("druid.maxPoolPreparedStatementPerConnectionSize")]),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("=")]),s("span",{pre:!0,attrs:{class:"token attr-value"}},[t._v("20")]),t._v("\n"),s("span",{pre:!0,attrs:{class:"token comment"}},[t._v("# 配置监控统计拦截的filters，去掉后监控界面sql无法统计，'wall'用于防火墙")]),t._v("\n"),s("span",{pre:!0,attrs:{class:"token attr-name"}},[t._v("druid.filters")]),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("=")]),s("span",{pre:!0,attrs:{class:"token attr-value"}},[t._v("stat,wall")]),t._v("\n"),s("span",{pre:!0,attrs:{class:"token comment"}},[t._v("# 通过connectProperties属性来打开mergeSql功能；慢SQL记录")]),t._v("\n"),s("span",{pre:!0,attrs:{class:"token attr-name"}},[t._v("druid.connectionProperties")]),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("=")]),s("span",{pre:!0,attrs:{class:"token attr-value"}},[t._v("stat.mergeSql=true;stat.slowSqlMillis=5000")]),t._v("\n"),s("span",{pre:!0,attrs:{class:"token comment"}},[t._v("# 合并多个DruidDataSource的监控数据")]),t._v("\n"),s("span",{pre:!0,attrs:{class:"token attr-name"}},[t._v("druid.useGlobalDataSourceStat")]),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("=")]),s("span",{pre:!0,attrs:{class:"token attr-value"}},[t._v("true")]),t._v("\n"),s("span",{pre:!0,attrs:{class:"token comment"}},[t._v("#白名单")]),t._v("\n"),s("span",{pre:!0,attrs:{class:"token attr-name"}},[t._v("druid.allow")]),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("=")]),s("span",{pre:!0,attrs:{class:"token attr-value"}},[t._v("localhost")]),t._v("\n"),s("span",{pre:!0,attrs:{class:"token comment"}},[t._v("#黑名单")]),t._v("\n"),s("span",{pre:!0,attrs:{class:"token attr-name"}},[t._v("druid.deny")]),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("=")]),t._v("\n"),s("span",{pre:!0,attrs:{class:"token comment"}},[t._v("#账号")]),t._v("\n"),s("span",{pre:!0,attrs:{class:"token attr-name"}},[t._v("druid.login.username")]),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("=")]),s("span",{pre:!0,attrs:{class:"token attr-value"}},[t._v("admin")]),t._v("\n"),s("span",{pre:!0,attrs:{class:"token comment"}},[t._v("#密码")]),t._v("\n"),s("span",{pre:!0,attrs:{class:"token attr-name"}},[t._v("druid.login.password")]),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("=")]),s("span",{pre:!0,attrs:{class:"token attr-value"}},[t._v("123")]),t._v("\n"),s("span",{pre:!0,attrs:{class:"token comment"}},[t._v("#是否可重置数据")]),t._v("\n"),s("span",{pre:!0,attrs:{class:"token attr-name"}},[t._v("druid.reset.enable")]),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("=")]),s("span",{pre:!0,attrs:{class:"token attr-value"}},[t._v("false")]),t._v("\n"),s("span",{pre:!0,attrs:{class:"token comment"}},[t._v("#用于排除一些不必要的url，比如.js,/jslib/等等。")]),t._v("\n"),s("span",{pre:!0,attrs:{class:"token attr-name"}},[t._v("druid.exclusions")]),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("=")]),s("span",{pre:!0,attrs:{class:"token attr-value"}},[t._v("*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*")]),t._v("\n"),s("span",{pre:!0,attrs:{class:"token comment"}},[t._v("#druid统计入口")]),t._v("\n"),s("span",{pre:!0,attrs:{class:"token attr-name"}},[t._v("druid.inMatchURL")]),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("=")]),s("span",{pre:!0,attrs:{class:"token attr-value"}},[t._v("/druid/*")]),t._v("\n")])])]),s("blockquote",[s("p",[t._v("上面是默认装配的配置，在没有配置的情况下，默认应用上面这一套配置方案。")]),t._v(" "),s("p",[t._v("您也可以重新定义某个节点的配置。")]),t._v(" "),s("p",[t._v("一般情况下我们只需要配置"),s("code",[t._v("url、username、password")]),t._v("即可。")]),t._v(" "),s("p",[t._v("如下所示：")])]),t._v(" "),s("div",{staticClass:"language-properties extra-class"},[s("pre",{pre:!0,attrs:{class:"language-properties"}},[s("code",[s("span",{pre:!0,attrs:{class:"token comment"}},[t._v("#※※※※※※※※※※※※※※※※※※ 数据源配置 ※※※※※※※※※※※※※※※※※※")]),t._v("\n"),s("span",{pre:!0,attrs:{class:"token comment"}},[t._v("# 连接地址")]),t._v("\n"),s("span",{pre:!0,attrs:{class:"token attr-name"}},[t._v("url")]),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("=")]),s("span",{pre:!0,attrs:{class:"token attr-value"}},[t._v("jdbc:mysql://localhost:3306/test")]),t._v("\n"),s("span",{pre:!0,attrs:{class:"token comment"}},[t._v("# 用户名")]),t._v("\n"),s("span",{pre:!0,attrs:{class:"token attr-name"}},[t._v("username")]),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("=")]),s("span",{pre:!0,attrs:{class:"token attr-value"}},[t._v("root")]),t._v("\n"),s("span",{pre:!0,attrs:{class:"token comment"}},[t._v("# 密码")]),t._v("\n"),s("span",{pre:!0,attrs:{class:"token attr-name"}},[t._v("password")]),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("=")]),s("span",{pre:!0,attrs:{class:"token attr-value"}},[t._v("123")]),t._v("\n")])])])])},[],!1,null,null,null);a.default=e.exports}}]);