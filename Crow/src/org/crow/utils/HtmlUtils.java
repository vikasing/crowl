/**
 * 
 */
package org.crow.utils;

import java.awt.Dimension;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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
	    String text = ArticleSentencesExtractor.INSTANCE.getText(html);
        return text;
	}
	public String getContentFromURL(String url) throws MalformedURLException, BoilerpipeProcessingException
	{
		Document document;
        String text = null;

		try {
			document = Jsoup.connect(url).timeout(60000).get();
		    text = ArticleSentencesExtractor.INSTANCE.getText(document.body().toString());
		    //System.out.println(text);

		} catch (IOException e) {
			e.printStackTrace();
		}		
		return text;
	}
	public List<String> getURLsFromHTML(String URL) throws IOException{
	    List<String> urlList = new ArrayList<String>();
	    Document doc = Jsoup.connect(URL).get();
	        Elements links = doc.select("a[href]");
	        for (Element link : links) {
	            urlList.add(link.attr("abs:href"));
	        }
            return urlList; 
	  }
	public List<String> getURLsFromHTML(Document doc) throws IOException{
            List<String> urlList = new ArrayList<String>();
                Elements links = doc.select("a[href]");
                for (Element link : links) {
                    urlList.add(link.attr("abs:href"));
                }
            return urlList; 
          }
}
