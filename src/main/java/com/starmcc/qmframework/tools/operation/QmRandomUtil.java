package com.starmcc.qmframework.tools.operation;

import java.util.Random;
import java.util.UUID;

/**
 * @author starmcc
 * @version 2018年11月24日 上午2:06:55
 * 随机生成工具类
 */
public class QmRandomUtil {

    /**
     * 生成UUID
     *
     * @return uuid
     */
    public static String createUuid() {
        String str = UUID.randomUUID().toString();
        // 去掉"-"符号
        return str.substring(0, 8)
                + str.substring(9, 13)
                + str.substring(14, 18)
                + str.substring(19, 23)
                + str.substring(24);
    }

    /**
     * 获取随机数字符串
     *
     * @param size 长度
     * @return 随机数字符串
     */
    public static String createRandom(int size) {
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i <= size; i++) {
            sb.append(String.valueOf(random.nextInt(9)));
        }
        return sb.toString();
    }


}
