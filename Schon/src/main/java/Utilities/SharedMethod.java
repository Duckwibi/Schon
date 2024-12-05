package Utilities;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Locale;
import java.util.stream.Collectors;

import com.google.gson.Gson;

public class SharedMethod {
	
	public static boolean stringCheck(String str, int size, boolean alowSpecialChar) {
		if(str == null)
			return false;
		
		if(str.equals(""))
			return false;
		
		if(str.length() > size)
			return false;
		
		if(!alowSpecialChar)
			for(int i = 0; i < str.length(); i++)
				if((int)str.charAt(i) < 48 || (int)str.charAt(i) > 57)
					if((int)str.charAt(i) < 65 || (int)str.charAt(i) > 90)
						if((int)str.charAt(i) < 97 || (int)str.charAt(i) > 122)
							return false;
		
		return true;
	}
	
	public static boolean booleanCheck(String str) {
		if(str == null)
			return false;
		
		return (!str.equals("false") && !str.equals("true")) ? false : true;
	}
	
	public static boolean intCheck(String str, int min, int max) {
		
		boolean check = true;
		
		try {
			int num = Integer.parseInt(str);
			
			check = (num < min || num > max) ? false : true;
			
		} catch (Exception e) {
			check = false;
		}
		
		return check;
	}
	
	public static boolean doubleCheck(String str, double min, double max) {
		boolean check = true;
		
		try {
			double num = Double.parseDouble(str);
			
			check = (num < min || num > max) ? false : true;
			
		} catch (Exception e) {
			check = false;
		}
		
		return check;
	}
	
	public static boolean intArrayCheck(String str) {
		boolean check = true;
		
		try {
			new Gson().fromJson(str, int[].class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return check;
	}
	
	public static String getMD5Password(String password) {
		
		String MD5Password = "";
		
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(password.getBytes());
			byte[] digest = md.digest();

			BigInteger no = new BigInteger(1,digest);
			
			MD5Password = no.toString(16);
			while(MD5Password.length() < 32)
				MD5Password = "0" + MD5Password;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return MD5Password;
	}
	
	public static String moneyFormat(double num) {
		
		Currency usd = Currency.getInstance("USD");
		
		NumberFormat usdFormatter = NumberFormat.getCurrencyInstance(Locale.US); 
		usdFormatter.setCurrency(usd);
		
		return usdFormatter.format(num).replace("$", "");
	}
	
	public static String dateFormat(String str, Timestamp ts) {
		
		Date date = new Date(ts.getTime());
		
		return new SimpleDateFormat(str).format(date).replace("am", "AM").replace("pm", "PM");
	}
	
	public static String dateFormat(String str, Date date) {
		
		return new SimpleDateFormat(str).format(date);
	}
	
	public static <T> ArrayList<T> takeFromList(ArrayList<T> list, int num){
		
		return new ArrayList<T>(list.stream().limit(num).collect(Collectors.toList()));
	}
	
	public static int getPageCount(int rowCount) {
		if(rowCount == 0)
			return 1;
		else {
			double doublePageCount = rowCount / 9.0d;
			int intPageCount = (int)doublePageCount;
			return doublePageCount == intPageCount ? intPageCount : intPageCount + 1;
		}
	}
	
	public static int getNextThreePage(int currentPage, int pageCount) {
		return currentPage + 3 <= pageCount ? currentPage + 3 : pageCount;
	}
}
