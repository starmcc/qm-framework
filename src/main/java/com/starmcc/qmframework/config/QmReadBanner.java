package com.starmcc.qmframework.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * 读取Banner
 *
 * @Author: qm
 * @Date: 2019/6/3 15:56
 */
public class QmReadBanner {

    private static final Logger LOG = LoggerFactory.getLogger(QmReadBanner.class);

    /**
     * 读取Banner
     *
     * @param fileName
     * @return
     */
    public static String getBanner(String fileName) {
        try {
            InputStream inputStream = QmReadBanner.class.getResourceAsStream(fileName);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
            StringBuffer sb = new StringBuffer();
            String temp = null;
            while ((temp = bufferedReader.readLine()) != null) {
                sb.append(temp + "\n");
            }
            return sb.toString();
        } catch (Exception e) {
            LOG.debug("[" + fileName + "]读取异常！");
        }
        return null;
    }


}
