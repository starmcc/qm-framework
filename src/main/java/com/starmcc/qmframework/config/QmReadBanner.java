package com.starmcc.qmframework.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author starmcc
 * @version 2019/6/3 15:56
 * 读取Banner
 */
public class QmReadBanner {

    private static final Logger LOG = LoggerFactory.getLogger(QmReadBanner.class);

    /**
     * 读取Banner
     *
     * @param fileName
     * @return Returns the specified data according to the method
     */
    public static String getBanner(String fileName) {
        try {
            InputStream inputStream = QmReadBanner.class.getResourceAsStream(fileName);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
            StringBuffer sb = new StringBuffer();
            String temp = null;
            while (null != (temp = bufferedReader.readLine())) {
                sb.append(temp + "\n");
            }
            return sb.toString();
        } catch (Exception e) {
            LOG.debug("[" + fileName + "]读取异常！");
        }
        return null;
    }


}
