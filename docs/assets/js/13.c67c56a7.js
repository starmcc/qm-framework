(window.webpackJsonp=window.webpackJsonp||[]).push([[13],{129:function(t,a,s){"use strict";s.r(a);var e=s(0),n=Object(e.a)({},function(){var t=this,a=t.$createElement,s=t._self._c||a;return s("ContentSlotsDistributor",{attrs:{"slot-key":t.$parent.slotKey}},[s("h1",{attrs:{id:"请求响应消息"}},[s("a",{staticClass:"header-anchor",attrs:{href:"#请求响应消息","aria-hidden":"true"}},[t._v("#")]),t._v(" 请求响应消息")]),t._v(" "),s("h2",{attrs:{id:"配置说明"}},[s("a",{staticClass:"header-anchor",attrs:{href:"#配置说明","aria-hidden":"true"}},[t._v("#")]),t._v(" 配置说明")]),t._v(" "),s("div",{staticClass:"language-properties extra-class"},[s("pre",{pre:!0,attrs:{class:"language-properties"}},[s("code",[s("span",{pre:!0,attrs:{class:"token comment"}},[t._v("#※※※※※※※※※请求响应设置※※※※※※※※※")]),t._v("\n"),s("span",{pre:!0,attrs:{class:"token comment"}},[t._v("#请求数据时，根据该key名解析数据(rest风格)")]),t._v("\n"),s("span",{pre:!0,attrs:{class:"token attr-name"}},[t._v("body.request.key")]),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("=")]),s("span",{pre:!0,attrs:{class:"token attr-value"}},[t._v("value")]),t._v("\n"),s("span",{pre:!0,attrs:{class:"token comment"}},[t._v("#返回数据时，使用的最外层key名(rest风格)")]),t._v("\n"),s("span",{pre:!0,attrs:{class:"token attr-name"}},[t._v("body.response.key")]),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("=")]),s("span",{pre:!0,attrs:{class:"token attr-value"}},[t._v("value")]),t._v("\n"),s("span",{pre:!0,attrs:{class:"token comment"}},[t._v("#返回数据时，默认message的语言 EN/CN")]),t._v("\n"),s("span",{pre:!0,attrs:{class:"token attr-name"}},[t._v("body.response.message.lang")]),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("=")]),s("span",{pre:!0,attrs:{class:"token attr-value"}},[t._v("CN")]),t._v("\n")])])]),s("h2",{attrs:{id:"请求格式"}},[s("a",{staticClass:"header-anchor",attrs:{href:"#请求格式","aria-hidden":"true"}},[t._v("#")]),t._v(" 请求格式")]),t._v(" "),s("div",{staticClass:"language-json extra-class"},[s("pre",{pre:!0,attrs:{class:"language-json"}},[s("code",[s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("{")]),t._v("\n    "),s("span",{pre:!0,attrs:{class:"token property"}},[t._v('"value"')]),s("span",{pre:!0,attrs:{class:"token operator"}},[t._v(":")]),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("{")]),t._v("\n        "),s("span",{pre:!0,attrs:{class:"token comment"}},[t._v("// ... 请求数据")]),t._v("\n    "),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("}")]),t._v("\n"),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("}")]),t._v("\n")])])]),s("blockquote",[s("p",[t._v("如果设置了body.request.key则按照设置的值作为key，否则使用"),s("code",[t._v("value")])])]),t._v(" "),s("h2",{attrs:{id:"返回格式"}},[s("a",{staticClass:"header-anchor",attrs:{href:"#返回格式","aria-hidden":"true"}},[t._v("#")]),t._v(" 返回格式")]),t._v(" "),s("div",{staticClass:"language-json extra-class"},[s("pre",{pre:!0,attrs:{class:"language-json"}},[s("code",[s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("{")]),t._v("\n    "),s("span",{pre:!0,attrs:{class:"token property"}},[t._v('"value"')]),s("span",{pre:!0,attrs:{class:"token operator"}},[t._v(":")]),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("{")]),t._v("\n        "),s("span",{pre:!0,attrs:{class:"token property"}},[t._v('"msg"')]),s("span",{pre:!0,attrs:{class:"token operator"}},[t._v(":")]),s("span",{pre:!0,attrs:{class:"token string"}},[t._v('"Success"')]),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(",")]),t._v("\n        "),s("span",{pre:!0,attrs:{class:"token property"}},[t._v('"code"')]),s("span",{pre:!0,attrs:{class:"token operator"}},[t._v(":")]),s("span",{pre:!0,attrs:{class:"token number"}},[t._v("1")]),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v(",")]),t._v("\n        "),s("span",{pre:!0,attrs:{class:"token property"}},[t._v('"data"')]),s("span",{pre:!0,attrs:{class:"token operator"}},[t._v(":")]),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("{")]),t._v("\n            "),s("span",{pre:!0,attrs:{class:"token comment"}},[t._v("// ... 返回数据")]),t._v("\n        "),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("}")]),t._v("\n    "),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("}")]),t._v("\n"),s("span",{pre:!0,attrs:{class:"token punctuation"}},[t._v("}")]),t._v("\n")])])]),s("blockquote",[s("p",[t._v("如果设置了body.response.key则按照设置的值作为key，否则使用`value")])]),t._v(" "),s("h2",{attrs:{id:"服务端状态码规范"}},[s("a",{staticClass:"header-anchor",attrs:{href:"#服务端状态码规范","aria-hidden":"true"}},[t._v("#")]),t._v(" 服务端状态码规范")]),t._v(" "),s("table",[s("thead",[s("tr",[s("th",{staticStyle:{"text-align":"center"}},[t._v("状态码")]),t._v(" "),s("th",{staticStyle:{"text-align":"center"}},[t._v("说明")])])]),t._v(" "),s("tbody",[s("tr",[s("td",{staticStyle:{"text-align":"center"}},[t._v("1~99")]),t._v(" "),s("td",{staticStyle:{"text-align":"center"}},[t._v("业务编码，在书写业务时常用该范围编码")])]),t._v(" "),s("tr",[s("td",{staticStyle:{"text-align":"center"}},[t._v("100~199")]),t._v(" "),s("td",{staticStyle:{"text-align":"center"}},[t._v("全局编码，应用全局返回时使用该范围编码")])]),t._v(" "),s("tr",[s("td",{staticStyle:{"text-align":"center"}},[t._v("400~499")]),t._v(" "),s("td",{staticStyle:{"text-align":"center"}},[t._v("访问编码，应用全局访问时出现异常使用该范围编码")])]),t._v(" "),s("tr",[s("td",{staticStyle:{"text-align":"center"}},[t._v("500~999")]),t._v(" "),s("td",{staticStyle:{"text-align":"center"}},[t._v("服务器异常编码，应用全局服务器异常时使用该范围编码")])])])])])},[],!1,null,null,null);a.default=n.exports}}]);