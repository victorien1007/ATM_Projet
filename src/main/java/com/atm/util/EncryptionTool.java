package com.atm.util;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptionTool {

	public static String getMD5Info(String str)
	{
		try 
		{  
            // ����һ��MD5���ܼ���ժҪ  
            MessageDigest md = MessageDigest.getInstance("MD5");  
            // ����md5����  
            md.update(str.getBytes("UTF-8"));  
            // digest()���ȷ������md5 hashֵ������ֵΪ8Ϊ�ַ�������Ϊmd5 hashֵ��16λ��hexֵ��ʵ���Ͼ���8λ���ַ�  
            // BigInteger������8λ���ַ���ת����16λhexֵ�����ַ�������ʾ���õ��ַ�����ʽ��hashֵ  
            return new BigInteger(1, md.digest()).toString(16);  
        } 
		catch (Exception e)
		{  
           return "";  
        }  
     
	}
	
	
	
	public static String getSHA256Info(String str)
	{
		MessageDigest messageDigest;
		
		String encodeStr = "";
		try 
		{
			messageDigest = MessageDigest.getInstance("SHA-256");
			messageDigest.update(str.getBytes("UTF-8"));
			encodeStr = byte2Hex(messageDigest.digest());
		}
		catch (NoSuchAlgorithmException e) 
		{
		} 
		catch(UnsupportedEncodingException e) 
		{
		}
		return encodeStr;
	}
	
	public static String byte2Hex(byte[] bytes){
		
		StringBuffer stringBuffer = new StringBuffer();
		
		String temp = null;
		
		for (int i=0;i<bytes.length;i++)
		{
			temp = Integer.toHexString(bytes[i] & 0xFF);
				if (temp.length()==1)
				{
					//1�õ�һλ�Ľ��в�0����
					stringBuffer.append("0");
				}
			stringBuffer.append(temp);
		}
		
		return stringBuffer.toString();
	}
	
	
	public static void main(String[] args) {
		
		System.out.println(EncryptionTool.getMD5Info("e10adc3949ba59abbe56e057f20f883e"));
		System.out.println(EncryptionTool.getSHA256Info("password"));
	}
}
