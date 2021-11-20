package com.starmcc.qmframework.tools.operation;

import com.starmcc.qmframework.config.AesConfiguration;
import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Date;

/**
 * @author starmcc
 * AES对称加密技术
 */
public class QmAesUtil {

    private static final Logger LOG = LoggerFactory.getLogger(QmAesUtil.class);


    /**
     * 加密封装(使用配置的key和次数)
     *
     * @param data 数据
     * @return {@link String}
     * @throws Exception 异常
     */
    public static String encryptAes(String data) throws Exception {
        return encryptAes(data, AesConfiguration.getNumber(), AesConfiguration.getKey());
    }


    /**
     * 解密封装(使用配置的key和次数)
     *
     * @param data 秘文
     * @return {@link String}
     * @throws Exception 异常
     */
    public static String decryptAes(String data) throws Exception {
        return decryptAes(data, AesConfiguration.getNumber(), AesConfiguration.getKey());
    }

    /**
     * 加密封装
     *
     * @param data 加密数据
     * @param num  加密次数
     * @param key  秘钥
     * @return Returns the specified data according to the method
     * @throws Exception
     */
    public static String encryptAes(String data, int num, String key) throws Exception {
        final Date date = new Date();
        for (int i = 0; i < num; i++) {
            data = encryptAes(data, key);
        }
        LOG.debug("EncryptAes Elapsed Time {}", (System.currentTimeMillis() - date.getTime()));
        return data;
    }


    /**
     * 解密aes
     *
     * @param data 数据
     * @param num  加密次数
     * @param key  秘钥
     * @return {@link String}
     * @throws Exception 异常
     */
    public static String decryptAes(String data, int num, String key) throws Exception {
        final Date date = new Date();
        for (int i = 0; i < num; i++) {
            data = decryptAes(data, key);
        }
        LOG.debug("DecryptAes Elapsed Time {}", (System.currentTimeMillis() - date.getTime()));
        return data;
    }


    /**
     * aes加密
     *
     * @param data 数据
     * @param key  秘钥
     * @return {@link String}
     * @throws Exception 异常
     */
    private static String encryptAes(String data, String key) throws Exception {
        byte[] encryptedData = getCipher(key).doFinal(data.getBytes(AesConfiguration.getEncoding()));
        return Base64.encodeBase64String(encryptedData);
    }


    /**
     * 解密aes
     *
     * @param data 数据
     * @param key  秘钥
     * @return {@link String}
     * @throws Exception 异常
     */
    private static String decryptAes(String data, String key) throws Exception {
        byte[] decryptedData = getCipher(key).doFinal(Base64.decodeBase64(data));
        return new String(decryptedData, AesConfiguration.getEncoding());
    }

    /**
     * 得到密码器
     *
     * @param key 关键
     * @return {@link Cipher}
     * @throws NoSuchAlgorithmException     没有这样的算法异常
     * @throws UnsupportedEncodingException 不支持的编码异常
     * @throws NoSuchPaddingException       没有这样的填充例外
     * @throws InvalidKeyException          无效的关键例外
     */
    private static Cipher getCipher(String key) throws NoSuchAlgorithmException, UnsupportedEncodingException, NoSuchPaddingException, InvalidKeyException {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
        secureRandom.setSeed(key.getBytes(AesConfiguration.getEncoding()));
        kgen.init(128, secureRandom);
        SecretKey skey = kgen.generateKey();
        byte[] raw = skey.getEncoded();
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, skeySpec);
        return cipher;
    }


}
