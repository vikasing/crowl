/**
 * 
 */
package org.crow.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.HttpParams;
import org.apache.http.params.SyncBasicHttpParams;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import de.l3s.boilerpipe.BoilerpipeProcessingException;
import de.l3s.boilerpipe.extractors.ArticleSentencesExtractor;

/**
 * @author viksin
 *
 */
public class HtmlUtils {
	private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.2; WOW64; rv:21.0) Gecko/20100101 Firefox/21.0";
	private static final Integer TIMEOUT = 5*60*1000;
	String DATE_REGEX = "^(0[1-9]|[12][0-9]|3[01])[- /.](0[1-9]|1[012])[- /.](19|20)\\d\\d$";  
	private static final String HTML = "text/html";
	private static final String PLAIN_TEXT = "text/plain";
	private HttpClient httpClient;
	//private static final String DATE_REGEX = "^(?:(?:(?:0?[13578]|1[02])(\/|-|\.)31)\1|(?:(?:0?[1,3-9]|1[0-2])(\/|-|\.)(?:29|30)\2))(?:(?:1[6-9]|[2-9]\d)?\d{2})$|^(?:0?2(\/|-|\.)29\3(?:(?:(?:1[6-9]|[2-9]\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:(?:0?[1-9])|(?:1[0-2]))(\/|-|\.)(?:0?[1-9]|1\d|2[0-8])\4(?:(?:1[6-9]|[2-9]\d)?\d{2})$";
	public HtmlUtils() {
		initHttpClient();
	}
	
	private void initHttpClient(){
		HttpParams httpParams = new SyncBasicHttpParams();
		httpParams.setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 60000);
		httpParams.setParameter(CoreConnectionPNames.SO_TIMEOUT, 60000);
		//ClientConnectionManager clientConnectionManager = new ThreadSafeClientConnManager();
		httpClient = new DefaultHttpClient(httpParams);
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
	
	public List<String> findImageUrl(String feedContent, boolean convertTo64BitEncoding)
	{
	    List<String> imageUrls = getImgUrls(feedContent); 
	    List<String> encodeImages = new ArrayList<String>();
	    ImageTools iTools = null;
	    for (String url : imageUrls) {
	    	URL iUrl;
			try {
				iUrl = new URL(url);
				iTools = new ImageTools();
				String base64Image = iTools.getDimAndGenerate64BaseCode(150, 180, iTools.determineImageType(url),iUrl.openStream());
				if (base64Image!=null) {
					encodeImages.add(base64Image);
				}
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}	    	
		}
		return encodeImages;
	}

	/**
	 * @param feedContent
	 * @return
	 */
	public List<String> getImgUrls(String feedContent) {
		List<String> imageUrls= new ArrayList<String>();
		String imageUrl=null;
		String imageTagRegex="<img.*?>";
		String srcRegex="src=\".*?\"";
		Pattern pTag = Pattern.compile(imageTagRegex);
		Matcher matcher = pTag.matcher(feedContent); 
		while (matcher.find()) 
		{
			Pattern pSrc = Pattern.compile(srcRegex);
			Matcher m = pSrc.matcher(matcher.group()); 
			while (m.find()) 
			{			
				imageUrl = m.group();
				imageUrl = imageUrl.substring(4, imageUrl.length());
				imageUrl=imageUrl.replaceAll("\"", "");
				imageUrls.add(imageUrl);
			}
         }
		return imageUrls;
	}
	
	public String getCleanTextFromHTML(String html) throws MalformedURLException, BoilerpipeProcessingException
	{
	    // NOTE: Use ArticleExtractor unless DefaultExtractor gives better results for you
	     
        return ArticleSentencesExtractor.INSTANCE.getText(html);
	}
	public String getContentFromURL(String url) throws MalformedURLException, BoilerpipeProcessingException
	{
		Document document;
        String text = null;
		try {
			document = Jsoup.connect(url).userAgent(USER_AGENT).timeout(TIMEOUT).get();
		    text = ArticleSentencesExtractor.INSTANCE.getText(document.body().toString());
		} catch (IOException e) {
			System.out.println("Exception for URL: "+ url);
			e.printStackTrace();
		}		
		return text;
	}
	public Set<String> getURLsFromHTML(String URL){
		Set<String> urlSet = new HashSet<String>();
		HttpGet httpGet = new HttpGet(URL);
		String currentDomain = httpGet.getURI().getHost();
		InputStream iStream = null;
		BufferedReader in = null;
		InputStreamReader isr = null;
		try {
			HttpResponse httpResponse = httpClient.execute(httpGet);
			HttpEntity httpEntity = httpResponse.getEntity();
			ContentType contentType = ContentType.getOrDefault(httpEntity);
			String mimeType = contentType.getMimeType();

			if (mimeType.equalsIgnoreCase(HTML) || mimeType.equalsIgnoreCase(PLAIN_TEXT)) {
				iStream = httpEntity.getContent();
				isr = new InputStreamReader(iStream);
				in = new BufferedReader(isr);
				StringBuffer htmlBuffer = new StringBuffer();
				String xString = null;
				while ((xString=in.readLine())!=null) {
					htmlBuffer.append(xString);
				}
				Document doc = Jsoup.parse(htmlBuffer.toString());
				Elements links = doc.select("a[href]");
				for (Element link : links) {
					String href = link.attr("abs:href");
					if (!href.isEmpty()) {
						urlSet.add(href);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();		
		}
		finally{
			try {
				if (in!=null) {
					in.close();
				}
				if (isr!=null) {
					isr.close();
				}
				if (iStream!=null) {					
					iStream.close();					
				}
			} catch (IOException e) {
					e.printStackTrace();
			}
			httpGet.releaseConnection();
		}
		return urlSet; 
	}
	public Set<String> getURLsFromHTML(Document doc) throws IOException{
        Set<String> urlSet = new HashSet<String>();
        Elements links = doc.select("a[href]");
        for (Element link : links) {
        	urlSet.add(link.attr("abs:href"));
        }
        return urlSet; 
    }
	
	public String getFeedURL(String url) {
		String fURL = null;
		try {
			//System.out.println("main url "+url);
			Document document = Jsoup.connect(url).userAgent(USER_AGENT).timeout(TIMEOUT).get();
			Elements urls = document.head().getAllElements();
			for (Element element : urls) {
				Element fElement = element.getElementsByAttributeValue("type", "application/rss+xml").get(0);
				if(fElement!=null) {
					fURL =  fElement.attr("href").toString();
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fURL;
	}
	public Integer getHTTPStatusCode(String urlStr) {
		int respCode = 0;
		try {
			URL url = new URL(urlStr);
			HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
			httpURLConnection.setRequestMethod("GET");
			httpURLConnection.connect();
			respCode = httpURLConnection.getResponseCode();
			httpURLConnection.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return respCode;
	}
}
