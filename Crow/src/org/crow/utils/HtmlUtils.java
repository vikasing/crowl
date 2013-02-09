/**
 * 
 */
package org.crow.utils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
			document = Jsoup.connect(url).userAgent("Mozilla/5.0 (Windows NT 5.1; rv:11.0) Gecko/20120330 Firefox/11.0").timeout(60000).get();
		    text = ArticleSentencesExtractor.INSTANCE.getText(document.body().toString());
		} catch (IOException e) {
			e.printStackTrace();
		}		
		return text;
	}
	public Set<String> getURLsFromHTML(String URL) throws IOException{
	    Set<String> urlSet = new HashSet<String>();
	    Document doc = Jsoup.connect(URL).userAgent("Mozilla/5.0 (Windows NT 5.1; rv:11.0) Gecko/20120330 Firefox/11.0").timeout(60000).get();
        Elements links = doc.select("a[href]");
        for (Element link : links) {
            urlSet.add(link.attr("abs:href"));
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
			Document document = Jsoup.connect(url).userAgent("Mozilla/5.0 (Windows NT 5.1; rv:11.0) Gecko/20120330 Firefox/11.0").timeout(60000).get();
			Elements urls = document.head().getAllElements();
			for (Element element : urls) {
				Element fElement = element.getElementsByAttributeValue("type", "application/rss+xml").get(0);
				if(fElement!=null) {
					fURL =  fElement.attr("href").toString();
					break;
				}
			}
		} catch (Exception e) {
			//e.printStackTrace();
		}
		return fURL;
	}
}
