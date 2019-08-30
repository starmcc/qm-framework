(window.webpackJsonp=window.webpackJsonp||[]).push([[7],{133:function(t,a,s){"use strict";s.r(a);var n=s(0),e=Object(n.a)({},function(){var t=this,a=t.$createElement,s=t._self._c||a;return s("ContentSlotsDistributor",{attrs:{"slot-key":t.$parent.slotKey}},[s("h1",{attrs:{id:"aop-控制代理层"}},[s("a",{staticClass:"header-anchor",attrs:{href:"#aop-控制代理层","aria-hidden":"true"}},[t._v("#")]),t._v(" AOP 控制代理层")]),t._v(" "),s("h2",{attrs:{id:"描述"}},[s("a",{staticClass:"header-anchor",attrs:{href:"#描述","aria-hidden":"true"}},[t._v("#")]),t._v(" 描述")]),t._v(" "),s("h3",{attrs:{id:"前言"}},[s("a",{staticClass:"header-anchor",attrs:{href:"#前言","aria-hidden":"true"}},[t._v("#")]),t._v(" 前言")]),t._v(" "),s("p",[s("code",[t._v("QmControllerAgentAgency")]),t._v("在AOP上也做了一些封装，他能够代理所有继承了"),s("code",[t._v("QmController")]),t._v("的类，这样做的好处是我们可以对所有的控制接口进行代理操作，比如我们需要做一个接口访问的日志功能实现，我们可以直接使用这些封装进行一系列的拓展。")]),t._v(" "),s("h3",{attrs:{id:"实现逻辑"}},[s("a",{staticClass:"header-anchor",attrs:{href:"#实现逻辑","aria-hidden":"true"}},[t._v("#")]),t._v(" 实现逻辑")]),t._v(" "),s("p",[s("code",[t._v("QmControllerAgentAgency")]),t._v("是代理中心，它实现了切入的相关逻辑，基础会进行接口进入响应的日志信息。")]),t._v(" "),s("p",[s("code",[t._v("QmControllerAgent")]),t._v("是代理接口，它提供给调用者进行一系列的业务拓展。")]),t._v(" "),s("h2",{attrs:{id:"使用"}},[s("a",{staticClass:"header-anchor",attrs:{href:"#使用","aria-hidden":"true"}},[t._v("#")]),t._v(" 使用")]),t._v(" "),s("h3",{attrs:{id:"添加aop-maven依赖"}},[s("a",{staticClass:"header-anchor",attrs:{href:"#添加aop-maven依赖","aria-hidden":"true"}},[t._v("#")]),t._v(" 添加AOP Maven依赖")]),t._v(" "),s("blockquote",[s("p",[t._v("Spring boot 的 AOP 支持")])]),t._v(" "),s("div",{staticClass:"language-xml extra-class"},[s("pre",{pre:!0,attrs:{class:"language-xml"}},[s("code",[s("span",{pre:!0,attrs:{class:"token tag"}},[s("span",{pre:!0,attrs:{class:"token tag"}},[s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("<")]),t._v("dependency")]),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(">")])]),t._v("\n    "),s("span",{pre:!0,attrs:{class:"token tag"}},[s("span",{pre:!0,attrs:{class:"token tag"}},[s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("<")]),t._v("groupId")]),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(">")])]),t._v("org.springframework.boot"),s("span",{pre:!0,attrs:{class:"token tag"}},[s("span",{pre:!0,attrs:{class:"token tag"}},[s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("</")]),t._v("groupId")]),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(">")])]),t._v("\n    "),s("span",{pre:!0,attrs:{class:"token tag"}},[s("span",{pre:!0,attrs:{class:"token tag"}},[s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("<")]),t._v("artifactId")]),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(">")])]),t._v("spring-boot-starter-aop"),s("span",{pre:!0,attrs:{class:"token tag"}},[s("span",{pre:!0,attrs:{class:"token tag"}},[s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("</")]),t._v("artifactId")]),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(">")])]),t._v("\n"),s("span",{pre:!0,attrs:{class:"token tag"}},[s("span",{pre:!0,attrs:{class:"token tag"}},[s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("</")]),t._v("dependency")]),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(">")])]),t._v("\n")])])]),s("h3",{attrs:{id:"import导入"}},[s("a",{staticClass:"header-anchor",attrs:{href:"#import导入","aria-hidden":"true"}},[t._v("#")]),t._v(" @Import导入")]),t._v(" "),s("p",[t._v("在标注了"),s("code",[t._v("@SpringBootApplication")]),t._v("的类中使用"),s("code",[t._v("@Import")]),t._v("导入"),s("code",[t._v("QmControllerAgentAgency")])]),t._v(" "),s("div",{staticClass:"language-java extra-class"},[s("pre",{pre:!0,attrs:{class:"language-java"}},[s("code",[s("span",{pre:!0,attrs:{class:"token annotation punctuation"}},[t._v("@SpringBootApplication")]),t._v("\n"),s("span",{pre:!0,attrs:{class:"token annotation punctuation"}},[t._v("@Import")]),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("(")]),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("{")]),t._v("\n        "),s("span",{pre:!0,attrs:{class:"token class-name"}},[t._v("QmFrameworkApplication")]),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(".")]),s("span",{pre:!0,attrs:{class:"token keyword"}},[t._v("class")]),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(",")]),t._v("\n        "),s("span",{pre:!0,attrs:{class:"token class-name"}},[t._v("QmControllerAgentAgency")]),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(".")]),s("span",{pre:!0,attrs:{class:"token keyword"}},[t._v("class")]),t._v("\n"),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("}")]),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(")")]),t._v("\n"),s("span",{pre:!0,attrs:{class:"token keyword"}},[t._v("public")]),t._v(" "),s("span",{pre:!0,attrs:{class:"token keyword"}},[t._v("class")]),t._v(" "),s("span",{pre:!0,attrs:{class:"token class-name"}},[t._v("DemoApplication")]),t._v(" "),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("{")]),t._v("\n    "),s("span",{pre:!0,attrs:{class:"token keyword"}},[t._v("public")]),t._v(" "),s("span",{pre:!0,attrs:{class:"token keyword"}},[t._v("static")]),t._v(" "),s("span",{pre:!0,attrs:{class:"token keyword"}},[t._v("void")]),t._v(" "),s("span",{pre:!0,attrs:{class:"token function"}},[t._v("main")]),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("(")]),s("span",{pre:!0,attrs:{class:"token class-name"}},[t._v("String")]),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("[")]),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("]")]),t._v(" args"),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(")")]),t._v(" "),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("{")]),t._v("\n        "),s("span",{pre:!0,attrs:{class:"token class-name"}},[t._v("SpringApplication")]),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(".")]),s("span",{pre:!0,attrs:{class:"token function"}},[t._v("run")]),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("(")]),s("span",{pre:!0,attrs:{class:"token class-name"}},[t._v("DemoApplication")]),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(".")]),s("span",{pre:!0,attrs:{class:"token keyword"}},[t._v("class")]),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(",")]),t._v(" args"),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(")")]),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(";")]),t._v("\n    "),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("}")]),t._v("\n"),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("}")]),t._v("\n\n")])])]),s("blockquote",[s("p",[t._v("实现"),s("code",[t._v("QmControllerAgent")]),t._v("并注册到Spring IOC容器中。")])]),t._v(" "),s("blockquote",[s("p",[t._v("示例代码：")])]),t._v(" "),s("div",{staticClass:"language-java extra-class"},[s("pre",{pre:!0,attrs:{class:"language-java"}},[s("code",[s("span",{pre:!0,attrs:{class:"token annotation punctuation"}},[t._v("@Component")]),t._v("\n"),s("span",{pre:!0,attrs:{class:"token keyword"}},[t._v("public")]),t._v(" "),s("span",{pre:!0,attrs:{class:"token keyword"}},[t._v("class")]),t._v(" "),s("span",{pre:!0,attrs:{class:"token class-name"}},[t._v("MyAgent")]),t._v(" "),s("span",{pre:!0,attrs:{class:"token keyword"}},[t._v("implements")]),t._v(" "),s("span",{pre:!0,attrs:{class:"token class-name"}},[t._v("QmControllerAgent")]),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("{")]),t._v("\n\n    "),s("span",{pre:!0,attrs:{class:"token annotation punctuation"}},[t._v("@Override")]),t._v("\n    "),s("span",{pre:!0,attrs:{class:"token keyword"}},[t._v("public")]),t._v(" "),s("span",{pre:!0,attrs:{class:"token keyword"}},[t._v("void")]),t._v(" "),s("span",{pre:!0,attrs:{class:"token function"}},[t._v("before")]),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("(")]),s("span",{pre:!0,attrs:{class:"token class-name"}},[t._v("JoinPoint")]),t._v(" jp"),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(")")]),t._v(" "),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("{")]),t._v("\n        "),s("span",{pre:!0,attrs:{class:"token comment"}},[t._v("// 当进入接口时，调用该方法。")]),t._v("\n    "),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("}")]),t._v("\n\n    "),s("span",{pre:!0,attrs:{class:"token annotation punctuation"}},[t._v("@Override")]),t._v("\n    "),s("span",{pre:!0,attrs:{class:"token keyword"}},[t._v("public")]),t._v(" "),s("span",{pre:!0,attrs:{class:"token keyword"}},[t._v("void")]),t._v(" "),s("span",{pre:!0,attrs:{class:"token function"}},[t._v("afterReturning")]),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("(")]),s("span",{pre:!0,attrs:{class:"token class-name"}},[t._v("JoinPoint")]),t._v(" jp"),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(",")]),t._v(" "),s("span",{pre:!0,attrs:{class:"token class-name"}},[t._v("Object")]),t._v(" result"),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(",")]),t._v(" "),s("span",{pre:!0,attrs:{class:"token class-name"}},[t._v("Long")]),t._v(" responseTime"),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(")")]),t._v(" "),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("{")]),t._v("\n        "),s("span",{pre:!0,attrs:{class:"token comment"}},[t._v("// 当接口返回数据时，调用该方法。")]),t._v("\n    "),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("}")]),t._v("\n"),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("}")]),t._v("\n")])])]),s("h2",{attrs:{id:"说明"}},[s("a",{staticClass:"header-anchor",attrs:{href:"#说明","aria-hidden":"true"}},[t._v("#")]),t._v(" 说明")]),t._v(" "),s("blockquote",[s("p",[t._v("上述的"),s("code",[t._v("JoinPoint")]),t._v("对象封装了"),s("code",[t._v("SpringAop")]),t._v("中切面方法的信息")])]),t._v(" "),s("table",[s("thead",[s("tr",[s("th",{staticStyle:{"text-align":"center"}},[t._v("方法名")]),t._v(" "),s("th",{staticStyle:{"text-align":"center"}},[t._v("说明")])])]),t._v(" "),s("tbody",[s("tr",[s("td",{staticStyle:{"text-align":"center"}},[t._v("Signature getSignature();")]),t._v(" "),s("td",{staticStyle:{"text-align":"center"}},[t._v("获取封装了署名信息的对象,在该对象中可以获取到目标方法名,所属类的Class等信息")])]),t._v(" "),s("tr",[s("td",{staticStyle:{"text-align":"center"}},[t._v("Object[] getArgs();")]),t._v(" "),s("td",{staticStyle:{"text-align":"center"}},[t._v("获取传入目标方法的参数对象")])]),t._v(" "),s("tr",[s("td",{staticStyle:{"text-align":"center"}},[t._v("Object getTarget();")]),t._v(" "),s("td",{staticStyle:{"text-align":"center"}},[t._v("获取被代理的对象")])]),t._v(" "),s("tr",[s("td",{staticStyle:{"text-align":"center"}},[t._v("Object getThis();")]),t._v(" "),s("td",{staticStyle:{"text-align":"center"}},[t._v("获取代理对象")])])])]),t._v(" "),s("blockquote",[s("p",[t._v("上述的"),s("code",[t._v("QmControllerAgent")]),t._v("接口具体的方法说明")])]),t._v(" "),s("table",[s("thead",[s("tr",[s("th",{staticStyle:{"text-align":"center"}},[t._v("方法名")]),t._v(" "),s("th",{staticStyle:{"text-align":"center"}},[t._v("说明")])])]),t._v(" "),s("tbody",[s("tr",[s("td",{staticStyle:{"text-align":"center"}},[t._v("void before(JoinPoint jp)")]),t._v(" "),s("td",{staticStyle:{"text-align":"center"}},[t._v("执行请求方法前执行该方法(前置增强)")])]),t._v(" "),s("tr",[s("td",{staticStyle:{"text-align":"center"}},[t._v("void afterReturning(JoinPoint jp, Object result, Long responseTime);")]),t._v(" "),s("td",{staticStyle:{"text-align":"center"}},[t._v("当请求方法执行完毕时执行该方法(后置增强)")])])])]),t._v(" "),s("blockquote",[s("p",[t._v("接口源码说明")])]),t._v(" "),s("div",{staticClass:"language-java extra-class"},[s("pre",{pre:!0,attrs:{class:"language-java"}},[s("code",[s("span",{pre:!0,attrs:{class:"token comment"}},[t._v("/**\n * 执行请求方法前执行该方法(前置增强)\n *\n * @param jp JoinPoint 切入点对象\n */")]),t._v("\n"),s("span",{pre:!0,attrs:{class:"token keyword"}},[t._v("void")]),t._v(" "),s("span",{pre:!0,attrs:{class:"token function"}},[t._v("before")]),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("(")]),s("span",{pre:!0,attrs:{class:"token class-name"}},[t._v("JoinPoint")]),t._v(" jp"),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(")")]),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(";")]),t._v("\n\n\n"),s("span",{pre:!0,attrs:{class:"token comment"}},[t._v("/**\n * 当请求方法执行完毕时执行该方法(后置增强)\n *\n * @param jp           JoinPoint 切入点对象\n * @param result       返回结果\n * @param responseTime 响应时间\n */")]),t._v("\n"),s("span",{pre:!0,attrs:{class:"token keyword"}},[t._v("void")]),t._v(" "),s("span",{pre:!0,attrs:{class:"token function"}},[t._v("afterReturning")]),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("(")]),s("span",{pre:!0,attrs:{class:"token class-name"}},[t._v("JoinPoint")]),t._v(" jp"),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(",")]),t._v(" "),s("span",{pre:!0,attrs:{class:"token class-name"}},[t._v("Object")]),t._v(" result"),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(",")]),t._v(" "),s("span",{pre:!0,attrs:{class:"token class-name"}},[t._v("Long")]),t._v(" responseTime"),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(")")]),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(";")]),t._v("\n")])])])])},[],!1,null,null,null);a.default=e.exports}}]);