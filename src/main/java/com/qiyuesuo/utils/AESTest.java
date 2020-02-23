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
	 
	
	//����
    public static String encrypt(String src) throws Exception {
        if (key == null || key.length() != 16) {
            throw new Exception("key����������");
        }
        byte[] raw = key.getBytes();
        SecretKeySpec skeySpec = new SecretKeySpec(raw, KEY_AES);
        Cipher cipher = Cipher.getInstance(KEY_AES);
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
        byte[] encrypted = cipher.doFinal(src.getBytes());
        return byte2hex(encrypted);
    }
 
    //�û�������Ҫ���ܵ������Լ��ܳ׽��н���
    public static String decrypt(String src,String theKey) throws Exception {
        if (theKey == null || theKey.length() != 16) {
            throw new Exception("key����������");
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
    
    /**����16λ����Կ**/
    public static String KeyValue16(){
        //����һ���ַ�����A-Z��a-z��0-9����62λ��
        String str="zxcvbnmlkjhgfdsaqwertyuiopQWERTYUIOPASDFGHJKLZXCVBNM1234567890";
        //��Random���������
        Random random=new Random();
        StringBuffer sb=new StringBuffer();
        //����Ϊ����ѭ������
        for(int i=0; i<16; ++i){
            //����0-61������
            int number=random.nextInt(62);
            //������������ͨ��length�γ��ص�sb��
            sb.append(str.charAt(number));
        }
        //�����ص��ַ�ת�����ַ���
        return sb.toString();
    }
    
    //���ڻ�ȡ���ܵ��������
    public static Map<String, String> getInformation(String s) throws Exception{
    	Map<String, String> map = new HashMap<String, String>();
    	map.put("key", key);
    	map.put("aesString",encrypt(s));
		return map;
    	
    }
    
    //����aes�ԳƼ���
    @Test
    public void run1() throws Exception{
    	String content = "78678275275275";
        System.out.println("key:"+key);
        System.out.println("ԭ���� = " + content);
        String encrypt = AESTest.encrypt(content);
        System.out.println("���ܺ� = " + encrypt);
        String decrypt = AESTest.decrypt(encrypt,key);
        System.out.println("���ܺ� = " + decrypt);
    }
}
