package com.IsteMySql.Util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;

public class VeriTabaniUtil {

	static Connection conn = null;
	
	public static Connection baglan() {
		try {
			
			//"jdbc:mysql://ServerIpAdresi/db_ismi" , "kullanici","sifre"
			conn = DriverManager.getConnection("jdbc:mysql://localhost/projemdb","root","ozkan");
			return conn;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage().toString());
			return null;
		}
	}
	
	
	//MD5 sifrelemesi
	public static String mD5Sifrelemesi(String icerik)
	{
		try {
			MessageDigest mDigest =MessageDigest.getInstance("MD5");
		
			//byte olarak oku
			byte[] sifrelenmis = mDigest.digest(icerik.getBytes());
			
			BigInteger nBigInteger=new BigInteger(1,sifrelenmis);
			//hex hesapla
			String hashIcerik= nBigInteger.toString(16);
			while (hashIcerik.length()<32) {
				hashIcerik = "0"+hashIcerik;
				
			}
				return hashIcerik;
		
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}
	
}
