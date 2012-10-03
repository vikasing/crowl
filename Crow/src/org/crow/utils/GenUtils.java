/**
 * 
 */
package org.crow.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;

/**
 * @author viksin
 *
 */
public class GenUtils {
	
	public String getCurrentDateTime(String format)
	{
		Calendar cal = Calendar.getInstance();
	    SimpleDateFormat sdf = new SimpleDateFormat(format);
	    return sdf.format(cal.getTime());
	}
	
	public String generateSHAHashId(String str)
	{
		String hashId ="";
		try {
			MessageDigest mdigest = MessageDigest.getInstance("SHA1");
			mdigest.update(str.getBytes());
			byte[] output = mdigest.digest();
			hashId=bytesToHex(output);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		return hashId;
	}
	
	public static String bytesToHex(byte[] b) {
	      char hexDigit[] = {'0', '1', '2', '3', '4', '5', '6', '7','8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
	      StringBuffer buffer = new StringBuffer();
	      for (int j=0; j<b.length; j++) {
	    	  buffer.append(hexDigit[(b[j] >> 4) & 0x0f]);
	    	  buffer.append(hexDigit[b[j] & 0x0f]);
	      }
	      return buffer.toString();
	   }
	public void serializeObject(Object obj)
	{
	    
	}
	
	public String getPropertyValue(String key)
	{
	           Properties prop = new Properties();
	           String propertyValue=null;
	           try {
	                   prop.load(new FileInputStream("config.properties"));	    
	                   propertyValue= prop.getProperty(key);
	    
	           } catch (IOException ex) {
	                   ex.printStackTrace();
	           }
	    return propertyValue;
	}
}
