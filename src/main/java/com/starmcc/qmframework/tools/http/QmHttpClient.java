package com.starmcc.qmframework.tools.http;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @author starmcc
 * @version 2018年11月24日 上午2:01:35
 * QmHttpClient工具类
 */
public class QmHttpClient {

    private static final Logger LOG = LoggerFactory.getLogger(QmHttpClient.class);

    /**
     * POST
     */
    public final static String POST = "POST";
    /**
     * GET
     */
    public final static String GET = "GET";
    /**
     * 默认字符编码
     */
    public final static String ENCODING_UTF_8 = "UTF-8";


    /**
     * POST提交
     *
     * @param url      url
     * @param headers  请求头
     * @param params   请求参数
     * @param encoding 编码方式
     * @return Returns the specified data according to the method
     */
    private static String sendPost(String url,
                                   Map<String, String> headers,
                                   Map<String, Object> params,
                                   String encoding) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost post = new HttpPost(url);
        if (headers != null && headers.size() != 0) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                post.setHeader(entry.getKey(), entry.getValue());
            }
        }
        if (null != params) {
            // 处理参数
            HttpEntity entity = handleParam(params, encoding);
            // 添加参数
            post.setEntity(entity);
        }
        CloseableHttpResponse response = null;
        String content = null;
        try {
            response = httpClient.execute(post);
            content = EntityUtils.toString(response.getEntity());
        } catch (Exception e) {
            LOG.error("请求异常: {}", e);
        } finally {
            close(response, httpClient);
        }
        LOG.info(content);
        return content;
    }

    /**
     * GET提交
     *
     * @param url      请求url
     * @param headers  请求头
     * @param params   请求参数
     * @param encoding 编码方式
     * @return 响应数据
     */
    private static String sendGet(String url,
                                  Map<String, String> headers,
                                  Map<String, Object> params,
                                  String encoding) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet get = new HttpGet(url);
        if (null != headers && headers.size() != 0) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                get.setHeader(entry.getKey(), entry.getValue());
            }
        }
        if (null != params) {
            // 处理参数
            HttpEntity entity = handleParam(params, encoding);
            try {
                String paramStr = EntityUtils.toString(entity);
                get = new HttpGet(url + "?" + paramStr);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        CloseableHttpResponse response = null;
        String content = null;
        try {
            response = httpClient.execute(get);
            content = EntityUtils.toString(response.getEntity(), encoding);
        } catch (Exception e) {
            LOG.error("请求异常: {}", e);
        } finally {
            close(response, httpClient);
        }
        LOG.info(content);
        return content;
    }

    /**
     * 处理参数
     *
     * @param params   请求参数
     * @param encoding 请求编码格式
     * @return HttpEntity
     */
    private static HttpEntity handleParam(Map<String, Object> params, String encoding) {
        List<NameValuePair> pList = new ArrayList<NameValuePair>();
        Iterator<Map.Entry<String, Object>> entrys = params.entrySet().iterator();
        while (entrys.hasNext()) {
            Map.Entry<String, Object> entry = entrys.next();
            String key = entry.getKey();
            Object value = entry.getValue();
            try {
                // 处理数组
                Object[] objs = (Object[]) value;
                for (Object obj : objs) {
                    pList.add(new BasicNameValuePair(key, obj.toString()));
                }
            } catch (Exception e) {
                // 处理普通类型
                pList.add(new BasicNameValuePair(key, value.toString()));
            }
        }
        UrlEncodedFormEntity uefEntity = null;
        try {
            uefEntity = new UrlEncodedFormEntity(pList, encoding);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return uefEntity;
    }

    /**
     * 关闭连接
     *
     * @param response   CloseableHttpResponse
     * @param httpClient CloseableHttpClient
     */
    private static void close(CloseableHttpResponse response, CloseableHttpClient httpClient) {
        try {
            if (null != response) {
                response.close();
            }
            if (null != httpClient) {
                httpClient.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ===========================================封装===========================

    /**
     * 提交请求
     *
     * @param method   请求类型
     * @param url      请求URL
     * @param headers  请求头
     * @param params   请求参数
     * @param encoding 请求编码格式
     * @return String  响应数据
     */
    public static String request(String method, String url,
                                 Map<String, String> headers, Map<String, Object> params, String encoding) {
        if (POST.equalsIgnoreCase(method)) {
            return QmHttpClient.sendPost(url, headers, params, encoding);
        } else if (GET.equalsIgnoreCase(method)) {
            return QmHttpClient.sendGet(url, headers, params, encoding);
        } else {
            return QmHttpClient.sendGet(url, headers, params, encoding);
        }
    }

    /**
     * 提交请求
     *
     * @param method 请求类型
     * @param url    请求URL
     * @param params 请求参数
     * @return String 响应数据
     */
    public static String request(String method, String url, Map<String, Object> params) {
        return QmHttpClient.request(method, url, null, params, ENCODING_UTF_8);
    }

    /**
     * 提交请求
     *
     * @param method  请求类型
     * @param url     请求URL
     * @param headers 请求头
     * @param params  请求参数
     * @return String 响应数据
     */
    public static String request(String method, String url, Map<String, String> headers, Map<String, Object> params) {
        return QmHttpClient.request(method, url, headers, params, ENCODING_UTF_8);
    }

    /**
     * 提交请求
     *
     * @param method   请求类型
     * @param url      请求URL
     * @param params   请求参数
     * @param encoding 请求编码格式
     * @return String 响应数据
     */
    public static String request(String method, String url, Map<String, Object> params, String encoding) {
        return QmHttpClient.request(method, url, null, params, encoding);
    }

    @Deprecated
    public static String send(String method, String url,
                              Map<String, String> headers, Map<String, Object> params, String encoding) {
        return QmHttpClient.request(method, url, headers, params, encoding);
    }

    @Deprecated
    public static String send(String method, String url, Map<String, Object> params) {
        return QmHttpClient.send(method, url, null, params, ENCODING_UTF_8);
    }

    @Deprecated
    public static String send(String method, String url, Map<String, String> headers, Map<String, Object> params) {
        return QmHttpClient.send(method, url, headers, params, ENCODING_UTF_8);
    }

    @Deprecated
    public static String send(String method, String url, Map<String, Object> params, String encoding) {
        return QmHttpClient.send(method, url, null, params, encoding);
    }


    /**
     * 根据手机号获取手机号归属地
     * {
     * mts:'1585078',
     * province:'江苏',
     * catName:'中国移动',
     * telString:'15850781443',
     * areaVid:'30511',
     * ispVid:'3236139',
     * carrier:'江苏移动'
     * }
     *
     * @param mobile 手机号
     * @return 手机归属地字符串
     */
    public static String mobilePhoneHome(String mobile) {
        String url = "https://tcc.taobao.com/cc/json/mobile_tel_segment.htm";
        Map<String, Object> map = new HashMap<>(16);
        map.put("tel", mobile);
        String response = QmHttpClient.send("GET", url, map);
        return response;
    }

    /**
     * 获取ip地址的归属地
     * localAddress={city:"广州市", province:"广东省"}
     *
     * @param ip IP地址
     * @return 归属地字符串
     */
    public static String getIpAddress(String ip) {
        Map<String, Object> params = new HashMap<String, Object>(16);
        params.put("ip", ip);
        String content = QmHttpClient.send("GET", "http://ip.ws.126.net/ipquery", params);
        String str = content.substring(content.indexOf("{") + 1, content.indexOf("}"));
        String[] st = str.split(",");
        String pro = st[1].substring(st[1].indexOf("province:\"") + 10, st[1].lastIndexOf("\""));
        String city = st[0].substring(st[0].indexOf("city:\"") + 6, st[0].lastIndexOf("\""));
        return pro + "-" + city;
    }

    /**
     * 获取用户真实IP地址，不使用request.getRemoteAddr();的原因是有可能用户使用了代理软件方式避免真实IP地址,
     * 可是，如果通过了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP值，究竟哪个才是真正的用户端的真实IP呢？
     * 答案是取X-Forwarded-For中第一个非unknown的有效IP字符串。
     * 如：X-Forwarded-For：192.168.1.110, 192.168.1.120,
     * 192.168.1.130,192.168.1.100
     * 用户真实IP为： 192.168.1.110
     *
     * @param request
     * @return Returns the specified data according to the method
     */
    public static String getHttpIp(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        String unknown = "unknown";
        if (null == ip || ip.length() == 0 || unknown.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (null == ip || ip.length() == 0 || unknown.equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (null == ip || ip.length() == 0 || unknown.equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (null == ip || ip.length() == 0 || unknown.equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (null == ip || ip.length() == 0 || unknown.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

}
