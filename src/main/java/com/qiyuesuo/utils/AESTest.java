package com.qiyuesuo.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.junit.Test;



/*
 *Administrator
 *
 *
*/
public class AESTest {
	private static final String KEY_AES = "AES";
	private static String key = KeyValue16();
	 
	
	//加密
    public static String encrypt(String src) throws Exception {
        if (key == null || key.length() != 16) {
            throw new Exception("key不满足条件");
        }
        byte[] raw = key.getBytes();
        SecretKeySpec skeySpec = new SecretKeySpec(raw, KEY_AES);
        Cipher cipher = Cipher.getInstance(KEY_AES);
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
        byte[] encrypted = cipher.doFinal(src.getBytes());
        return byte2hex(encrypted);
    }
 
    //用户输入需要解密的数据以及密匙进行解密
    public static String decrypt(String src,String theKey) throws Exception {
        if (theKey == null || theKey.length() != 16) {
            throw new Exception("key不满足条件");
        }
        byte[] raw = theKey.getBytes();
        SecretKeySpec skeySpec = new SecretKeySpec(raw, KEY_AES);
        Cipher cipher = Cipher.getInstance(KEY_AES);
        cipher.init(Cipher.DECRYPT_MODE, skeySpec);
        byte[] encrypted1 = hex2byte(src);
        byte[] original = cipher.doFinal(encrypted1);
        String originalString = new String(original);
        return originalString;
    }
 
    public static byte[] hex2byte(String strhex) {
        if (strhex == null) {
            return null;
        }
        int l = strhex.length();
        if (l % 2 == 1) {
            return null;
        }
        byte[] b = new byte[l / 2];
        for (int i = 0; i != l / 2; i++) {
            b[i] = (byte) Integer.parseInt(strhex.substring(i * 2, i * 2 + 2),
                    16);
        }
        return b;
    }
 
    public static String byte2hex(byte[] b) {
        StringBuilder hs = new StringBuilder();
        String stmp = "";
        for (int n = 0; n < b.length; n++) {
            stmp = (Integer.toHexString(b[n] & 0XFF));
            if (stmp.length() == 1) {
                hs.append("0").append(stmp);
            } else {
                hs.append(stmp);
            }
        }
        return hs.toString().toUpperCase();
    }
    
    /**生成16位的密钥**/
    public static String KeyValue16(){
        //定义一个字符串（A-Z，a-z，0-9）即62位；
        String str="zxcvbnmlkjhgfdsaqwertyuiopQWERTYUIOPASDFGHJKLZXCVBNM1234567890";
        //由Random生成随机数
        Random random=new Random();
        StringBuffer sb=new StringBuffer();
        //长度为几就循环几次
        for(int i=0; i<16; ++i){
            //产生0-61的数字
            int number=random.nextInt(62);
            //将产生的数字通过length次承载到sb中
            sb.append(str.charAt(number));
        }
        //将承载的字符转换成字符串
        return sb.toString();
    }
    
    //用于获取加密的相关数据
    public static Map<String, String> getInformation(String s) throws Exception{
    	Map<String, String> map = new HashMap<String, String>();
    	map.put("key", key);
    	map.put("aesString",encrypt(s));
		return map;
    	
    }
    
    //测试aes对称加密
    @Test
    public void run1() throws Exception{
    	String content = "78678275275275";
        System.out.println("key:"+key);
        System.out.println("原内容 = " + content);
        String encrypt = AESTest.encrypt(content);
        System.out.println("加密后 = " + encrypt);
        String decrypt = AESTest.decrypt(encrypt,key);
        System.out.println("解密后 = " + decrypt);
    }
}
