package alkomat;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;

import org.apache.commons.io.FileUtils;



public class PwCheck {
	File hashTxt = new File("./hash.txt");
		
	
	public void  changePW(String pw) throws UnsupportedEncodingException, IOException{
		List<String> hashcodes = new ArrayList<String>();
		hashcodes.add(get_SHA_512_SecurePassword(pw, "alkomat"));
		hashcodes.add(get_SHA_512_SecurePassword(String.valueOf(pw.length()), "alkomat"));
		FileUtils.writeLines(hashTxt, hashcodes);
	}
	
	public String get_SHA_512_SecurePassword(String passwordToHash, String   salt) throws UnsupportedEncodingException{
		String generatedPassword = null;
		    try {
		         MessageDigest md = MessageDigest.getInstance("SHA-512");
		         md.update(salt.getBytes("UTF-8"));
		         byte[] bytes = md.digest(passwordToHash.getBytes("UTF-8"));
		         StringBuilder sb = new StringBuilder();
		         for(int i=0; i< bytes.length ;i++){
		            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
		         }
		         generatedPassword = sb.toString();
		        } 
		       catch (NoSuchAlgorithmException e){
		        e.printStackTrace();
		       }
		    return generatedPassword;
		}
	
	public boolean checkPW(String pw) throws IOException{
		String hash = new String();
		BufferedReader reader;
				
		reader=new BufferedReader(new FileReader(hashTxt));
		hash = reader.readLine();
		reader.close();
			
		if(get_SHA_512_SecurePassword(pw, "alkomat").equals(hash)){
			return true;
		}
		
		else
			return false;

	}
	
	public boolean checkLength(String pw) throws IOException{
		List<String> hash = new ArrayList<String>();
		hash = Files.readAllLines(Paths.get(hashTxt.getAbsolutePath()), StandardCharsets.UTF_8);
		if(get_SHA_512_SecurePassword(String.valueOf(pw.length()), "alkomat").equals(hash.get(1))){
			return true;
		}
		
		else
			return false;
		
	}
	

}