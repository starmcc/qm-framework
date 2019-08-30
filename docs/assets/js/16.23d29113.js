(window.webpackJsonp=window.webpackJsonp||[]).push([[16],{186:function(t,a,s){"use strict";s.r(a);var e=s(0),r=Object(e.a)({},function(){var t=this,a=t.$createElement,s=t._self._c||a;return s("ContentSlotsDistributor",{attrs:{"slot-key":t.$parent.slotKey}},[s("h1",{attrs:{id:"版本控制"}},[s("a",{staticClass:"header-anchor",attrs:{href:"#版本控制","aria-hidden":"true"}},[t._v("#")]),t._v(" 版本控制")]),t._v(" "),s("h2",{attrs:{id:"介绍"}},[s("a",{staticClass:"header-anchor",attrs:{href:"#介绍","aria-hidden":"true"}},[t._v("#")]),t._v(" 介绍")]),t._v(" "),s("p",[t._v("在框架中包含了对版本控制的操作，我们可以在配置中开启它。")]),t._v(" "),s("h2",{attrs:{id:"配置"}},[s("a",{staticClass:"header-anchor",attrs:{href:"#配置","aria-hidden":"true"}},[t._v("#")]),t._v(" 配置")]),t._v(" "),s("blockquote",[s("p",[t._v("qm-application.properties文件中的配置")])]),t._v(" "),s("div",{staticClass:"language-properties extra-class"},[s("pre",{pre:!0,attrs:{class:"language-properties"}},[s("code",[s("span",{pre:!0,attrs:{class:"token comment"}},[t._v("#※※※※※※※※※版本控制※※※※※※※※※")]),t._v("\n"),s("span",{pre:!0,attrs:{class:"token comment"}},[t._v("#是否开启版本控制(ture时,每个请求需在header带上version参数,参数值version)")]),t._v("\n"),s("span",{pre:!0,attrs:{class:"token attr-name"}},[t._v("version.start")]),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("=")]),s("span",{pre:!0,attrs:{class:"token attr-value"}},[t._v("true")]),t._v("\n"),s("span",{pre:!0,attrs:{class:"token comment"}},[t._v("#系统目前版本编号")]),t._v("\n"),s("span",{pre:!0,attrs:{class:"token attr-name"}},[t._v("version.now")]),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("=")]),s("span",{pre:!0,attrs:{class:"token attr-value"}},[t._v("1.0.0")]),t._v("\n"),s("span",{pre:!0,attrs:{class:"token comment"}},[t._v("#系统容忍请求版本编号(默认允许当前版本)")]),t._v("\n"),s("span",{pre:!0,attrs:{class:"token attr-name"}},[t._v("version.allows-[0]")]),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(":")]),s("span",{pre:!0,attrs:{class:"token attr-value"}},[t._v("0.9.8")]),t._v("\n"),s("span",{pre:!0,attrs:{class:"token attr-name"}},[t._v("version.allows-[1]")]),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(":")]),s("span",{pre:!0,attrs:{class:"token attr-value"}},[t._v("0.9.9")]),t._v("\n")])])]),s("h2",{attrs:{id:"说明"}},[s("a",{staticClass:"header-anchor",attrs:{href:"#说明","aria-hidden":"true"}},[t._v("#")]),t._v(" 说明")]),t._v(" "),s("p",[t._v("需要开启版本控制时，设置"),s("code",[t._v("version.start")]),t._v("为"),s("code",[t._v("true")]),t._v("。")]),t._v(" "),s("p",[t._v("开启版本控制后，所有的请求必须在"),s("code",[t._v("header")]),t._v("中携带"),s("code",[t._v("key")]),t._v("为"),s("code",[t._v("version")]),t._v("的参数。")]),t._v(" "),s("p",[t._v("参数内容对应版本号，如果不符合配置中的版本，则直接拦截。")]),t._v(" "),s("p",[s("code",[t._v("version.allows-[0]")]),t._v("可以配置多项容忍版本，设置容忍版本则设置的这些版本号也允许通过版本校验。")])])},[],!1,null,null,null);a.default=r.exports}}]);