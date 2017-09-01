package com.imgscoop.scoops;

import org.mindrot.jbcrypt.BCrypt;

public class Cryptor {
	
	public static String encrypt(String pass) {
		try{
			return BCrypt.hashpw(pass, BCrypt.gensalt());
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static boolean authPassword(String pass, String hashed){
		if (BCrypt.checkpw(pass, hashed))
			return true;
		else
			return false;
	}
}
