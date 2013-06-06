package org.crow.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;


public class Utils {
	public static String bytesToHex(byte[] b) {
	    char hexDigit[] = {'0', '1', '2', '3', '4', '5', '6', '7','8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
	    StringBuffer buffer = new StringBuffer();
	    for (int j=0; j<b.length; j++) {
	        buffer.append(hexDigit[(b[j] >> 4) & 0x0f]);
	        buffer.append(hexDigit[b[j] & 0x0f]);
	    }
	    return buffer.toString();
	 }
	public static Properties getPropertyObj()
	{
	           Properties prop = new Properties();
	           FileInputStream fis = null;
	           try {
	        	   	fis = new FileInputStream("config.properties");
	                prop.load(fis);	    
	           } catch (IOException ex) {
	                   ex.printStackTrace();
	           }
	           finally{
	        	   try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
	           }
	    return prop;
	}
	public static String getPropertyValue(String key)
	{
	           Properties prop = new Properties();
	           String propertyValue=null;
	           FileInputStream fis = null;
	           try {
	        	   	fis = new FileInputStream("config.properties");
	                prop.load(fis);	    
	                propertyValue= prop.getProperty(key);   
	           } catch (IOException ex) {
	                   ex.printStackTrace();
	           }
	           finally{
	        	   try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
	           }
	    return propertyValue;
	}

	public HashMap<String, String> createMapFromKVFile(String fileName){
		Properties prop = new Properties();
		HashMap<String,String> map = new HashMap<String, String>();
	    try {
            prop.load(new FileInputStream(fileName));
            Set<Object> set = prop.keySet();
            Iterator<Object> it = set.iterator();
            while(it.hasNext()){
            	String key = (String) it.next();
            	map.put(key, prop.getProperty(key));
            }
	    } catch (IOException ex) {
	        ex.printStackTrace();
	    }
		return map;
	}
	public void downloadFile(String fileUrl, String filePath) {
		try {
			//"imagethumbslocation")+"/img.jpg"
			URL url = new URL(fileUrl);
			URLConnection urlConnection = url.openConnection();
			urlConnection.setReadTimeout(60000);
			urlConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 5.1; rv:11.0) Gecko/20120330 Firefox/11.0");
			BufferedInputStream in = new BufferedInputStream(urlConnection.getInputStream());
	        FileOutputStream fos = new FileOutputStream(filePath);
			BufferedOutputStream bout = new BufferedOutputStream(fos, 1024);
			byte[] data = new byte[1024];
			int x = 0;
			while ((x = in.read(data, 0, 1024)) >= 0) {
				bout.write(data, 0, x);
			}
			bout.close();
			in.close();
		} catch (MalformedURLException e) {
			System.err.println(e.toString());
		} catch (IOException e) {
			System.err.println(e.toString());
		}
	}
	/**
	 * 
	 * @param str <br>
	 * For generating a movie id use RELEASEYEAR_MOVIENAME format.
	 */
	public String generateHashId(String str){
		
		String hashId =null;
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

	public String getPageSource(URL url) throws IOException {
	    StringBuilder page = new StringBuilder();
	    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	    String inputLine=null;
	      while ((inputLine = in.readLine()) != null) {
	        page.append(inputLine);
	      }
	    in.close();
	    conn.disconnect();
	    return page.toString();
	  }
	public String removeHtmlTags(String html)
	{
	        String nohtml=null;
	        try 
	        {
	            html = html.replaceAll("<br>", " ");
	            html = html.replaceAll("<br />", " ");
	            html = html.replaceAll("<br/>", " ");
	            nohtml = html.replaceAll("\\<(.|\n)*?>", "");
	                //nohtml = nohtml.replaceAll("&nbsp;", "");
	        } catch (Exception e) {
	                e.printStackTrace();
	        }
	        return nohtml;
	}
	public String extractRating(String rating){
		float calcRating=0.0f;
		try {
			if (rating.contains("*")) {
				String rating2 =rating;
				int stars = rating.replaceAll("[^*]","").length();
				String fraction = rating2.replaceAll("\\*", "").trim();
				if (!fraction.isEmpty()) {
					if(fraction.contains("\\.")){
						calcRating=Float.valueOf(fraction);
					}
					else if (fraction.contains("/")) {
						String[] arr = fraction.split("/");
						calcRating = stars + Float.valueOf(arr[0])/Float.valueOf(arr[1]);
					}
					else if (fraction.contains("&amp;frac12;")){
						calcRating = stars + 0.5f;
					}
				}
				else {
					calcRating = stars;
				}
	
			}
			else if (rating.contains("/")) {
				String[] arr = rating.split("/");
				if (arr[1].equalsIgnoreCase("4")) {
					calcRating = Float.valueOf(arr[0])*(5.0f/4.0f);
				}
				if (arr[1].equalsIgnoreCase("5")) {
					calcRating = Float.valueOf(arr[0]);
				}
			}
			else if (rating.contains("A")) {
				calcRating = 5.0f;
			}
			else if (rating.contains("B")) {
				calcRating = 4.0f;
			}
			else if (rating.contains("C")) {
				calcRating = 3.0f;
			}
			else if (rating.contains("D")) {
				calcRating = 2.0f;
			}
			else if (rating.contains("E")) {
				calcRating = 1.0f;
			}
		} catch (NumberFormatException e) {
			calcRating=-1;
			e.printStackTrace();
		}	
		return Float.toString(calcRating);
	}
	public String refineTweet(String tweet, Map<String,String> abrMap){
		Set<String> keySet = abrMap.keySet();
		Iterator<String> iterator = keySet.iterator();
		while (iterator.hasNext()) {
			String key = iterator.next();
			if (tweet.contains(" "+key+" ")) {
				tweet = tweet.replaceAll(" "+key+" ", " "+abrMap.get(key)+" ");
			}
		}
		tweet = tweet.replaceAll("@.*?\\s+", " ");
		tweet = tweet.replaceAll("#", "");
		return tweet;
	}
	
	public <K,V extends Comparable<? super V>> SortedSet<Map.Entry<K,V>> entriesSortedByValues(Map<K,V> map) {
	    SortedSet<Map.Entry<K,V>> sortedEntries = new TreeSet<Map.Entry<K,V>>(
	        new Comparator<Map.Entry<K,V>>() {
	            @Override public int compare(Map.Entry<K,V> e1, Map.Entry<K,V> e2) {
                    int res = e2.getValue().compareTo(e1.getValue());
                    return res != 0 ? res : 1; 
	            }
	        }
	    );
	    sortedEntries.addAll(map.entrySet());
	    return sortedEntries;
	}


	public List<String> movieNameExpander(String mName){
		String movieWords=Utils.getPropertyValue("MOVIE_WORDS");
		String[] mwArr = movieWords.split(" ");
		List<String> mwList = new ArrayList<String>();
		for (int i = 0; i < mwArr.length; i++) {
			String m = null;
			m = mName + " " +mwArr[i];
			mwList.add(m);
		}		
		return mwList;
	}
	
	public String removeStopWords(String text){
		String[] stopWords={
			"i","me","my","myself","we","our","ours","ourselves","you","your","yours","yourself",
			"yourselves","he","him","his","himself","she","her","hers","herself","it","its","itself",
			"they","them","their","theirs","themselves","this","that","these","those","am","is","are",
			"was","were","be","been","being","have","has","had","having","do","does","did","doing","a",
			"an","the","and","if","or","as","while","of","at","by","for","with","about","between","into",
			"through","during","above","below","to","from","in","out","on","over","then","here","there","how",
			"all","any","both","each","few","other","some","such","only","own","too","can","will"
			};
		for (int j = 0; j < stopWords.length; j++) {
			if (text.contains(stopWords[j])) {
				text=text.replaceAll("\\b"+stopWords[j]+"\\b", "");
			}	
		}
		text = text.replaceAll("[^a-zA-Z 0-9]+"," ");
		text = text.replaceAll(" +", " ");
		return text;
	}
	
	public String timeAgo(long minutes){
		String hAgo = null;
		if(minutes<60){
			hAgo = minutes+" minutes ago";
		}
		else if (minutes>=60 && minutes<1440) {
			hAgo = minutes/60+" hours ago";
		}
		else if (minutes>=1440 && minutes<2880){
			hAgo = "1 day ago";
		}
		else if(minutes>=2880){
			hAgo = minutes/1440 + " days ago";
		}
		return hAgo;
	}
}
