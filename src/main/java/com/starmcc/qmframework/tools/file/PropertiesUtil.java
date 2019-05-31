package com.starmcc.qmframework.tools.file;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

/**
 * properties工具类，默认读取config.properties配置文件
 *
 * @Author qm
 * @Date 2018年11月24日 上午2:09:11
 */
public class PropertiesUtil {

    /**
     * 如果需要读取其他配置文件请指定fileName的值;
     */
    public static String fileName = null;
    private static Properties properties;

    private PropertiesUtil() {
    }

    static {
        properties = new Properties();
        InputStreamReader inStream = null;
        try {
            // 读取properties文件,使用InputStreamReader字符流防止文件中出现中文导致乱码
            inStream = new InputStreamReader
                    (PropertiesUtil.class.getClassLoader().getResourceAsStream("config.properties"),
                            "UTF-8");
            properties.load(inStream);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inStream != null) {
                try {
                    inStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }


    /**
     * 从properties文件中读取出一个key对应的value
     *
     * @param key key
     * @return value
     */
    public static String get(String key) {
        String value = properties.getProperty(key);
        return value;
    }

    /**
     * 从properties文件中读取出一个key对应的value, 如果该value为空返回默认文本
     *
     * @param key key
     * @param defaultValue 如果获取不到返回该默认值
     * @return value
     */
    public static String get(String key, String defaultValue) {
        String value = properties.getProperty(key, defaultValue);
        if ("".equals(value.trim())) {
            value = defaultValue;
        }
        return value;
    }

    /**
     * 根据文件名读取key
     *
     * @param key key
     * @param fileName 文件名
     * @return
     */
    public static String getValueByFileName(String key, String fileName) {
        Properties pro = new Properties();
        InputStreamReader inStream = null;
        String value = null;
        try {
            // 读取properties文件,使用InputStreamReader字符流防止文件中出现中文导致乱码
            inStream = new InputStreamReader
                    (PropertiesUtil.class.getClassLoader().getResourceAsStream(fileName),
                            "UTF-8");
            pro.load(inStream);
            value = pro.getProperty(key);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inStream != null) {
                try {
                    inStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return value;
    }

}