/**
 * 
 */
package org.crow.base;

/**
 * @author viksin
 * This class uses ROME library to parse the feeds.	
 */
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.crow.classes.FeedEntry;
import org.crow.utils.GenUtils;
import org.crow.utils.HtmlUtils;

import com.sun.syndication.feed.synd.SyndContent;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;


public class FeedParser {
	private HttpClient httpClient;
	public FeedParser(HttpClient httpClient) {
		this.httpClient = httpClient;
	}
	@SuppressWarnings("unchecked")
	public ArrayList<FeedEntry> parser(URL feedUrl) {
		HtmlUtils htmlUtils = new HtmlUtils();
		//HttpHeadersAnalysis hha = new HttpHeadersAnalysis();
		GenUtils genUtils = new GenUtils();
		List<FeedEntry> feedList = new ArrayList<FeedEntry>();// ;Collections.synchronizedList(new ArrayList<FeedEntry>());
		final String fURL = feedUrl.toString();
		System.out.println("Parsing URL: "+fURL +" at "+Calendar.getInstance().getTime());
		HttpGet httpGet = new HttpGet(fURL);
		
		InputStream urlInputStream = null;
		InputStreamReader iStreamReader = null;
		try {
			HttpResponse httpResponse = httpClient.execute(httpGet);
			HttpEntity httpEntity = httpResponse.getEntity();
			if (httpEntity!=null) {
				urlInputStream = httpEntity.getContent();
				SyndFeedInput input = new SyndFeedInput();
				iStreamReader = new InputStreamReader(urlInputStream);
				SyndFeed feed = input.build(iStreamReader);
				String feedSourceTitle = feed.getTitle();
				List<SyndEntry> feedEntries = feed.getEntries();
	            for (SyndEntry se : feedEntries) {
	                // HttpHeaders httpHeaders = hha.getHttpHeaders(new
	                // URL(se.getLink().toString()));
	                FeedEntry fe = new FeedEntry();
	                StringBuffer sbuff = new StringBuffer();

	                fe.setSourceLink(fURL);
	                fe.setSourceTitle(feedSourceTitle);
	                fe.setFeedEntry(se);
	                //fe.setCompleteContent(htmlUtils.getContentFromURL(se.getLink()));
	                if (se.getContents().size() > 0) {
	                    Iterator<?> contents = se.getContents().iterator();
	                    while (contents.hasNext()) {
	                        SyndContent content = (SyndContent) contents.next();
	                        sbuff.append(content.getValue());
	                    }
	                    fe.setNoHtmlContent(htmlUtils.getCleanTextFromHTML(sbuff.toString()));
	                    fe.setFeedImageUrls(htmlUtils.getImgUrls(sbuff.toString()));
	                }
	                else if (se.getDescription() != null) {
	                    fe.setFeedImageUrls(htmlUtils.getImgUrls(se.getDescription().getValue()));
	                    fe.setNoHtmlContent(htmlUtils.getCleanTextFromHTML(se.getDescription().getValue()));
	                }
	                if (fe.getNoHtmlContent().length()<300) {
	                	fe.setNoHtmlContent(htmlUtils.getContentFromURL(se.getLink()));
					}
	                // fe.setLastModDateOnServer(httpHeaders.getLastModified());
	                fe.setFeedGetDateTime(Calendar.getInstance().getTime());
	                fe.setFeedHashid(genUtils.generateSHAHashId(se.getLink()));
	/*                if (fe.getCompleteContent()!=null && fe.getCompleteContent().length()<100) {
						fe.setCompleteContent(htmlUtils.getContentFromURL(se.getLink()));
					}*/
	                feedList.add(fe);
	            }
			}
			
		
		} catch (Exception ex) {
			System.out.println("ERROR: " + ex.getMessage()+" in " +fURL);
			ex.printStackTrace();
		}
		finally {
			try {
				iStreamReader.close();
				urlInputStream.close();
				httpGet.releaseConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return (ArrayList<FeedEntry>) feedList;
	}
}
