/**
 * 
 */
package org.crow.base;

/**
 * @author viksin
 * This class uses ROME library to parse the feeds.	
 */
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import org.crow.classes.FeedEntry;
import org.crow.utils.GenUtils;
import org.crow.utils.HtmlUtils;

import com.sun.syndication.feed.synd.SyndContent;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;


public class FeedParser {

	@SuppressWarnings("unchecked")
	public ArrayList<FeedEntry> parser(URL feedUrl) {
		//SyndFeedInput input = new SyndFeedInput();
		HtmlUtils htmlUtils = new HtmlUtils();
		//HttpHeadersAnalysis hha = new HttpHeadersAnalysis();
		GenUtils genUtils = new GenUtils();
		List<FeedEntry> feedList = new ArrayList<FeedEntry>();// ;Collections.synchronizedList(new ArrayList<FeedEntry>());
		//String imageThumbsStoragePath=genUtils.getPropertyValue("imagethumbslocation");
		//String imageThumbWidth=genUtils.getPropertyValue("imageWidth");
		//String imageThumbHeight=genUtils.getPropertyValue("imageHeight");
		System.out.println("Parsing URL: "+feedUrl.toString() +" at "+Calendar.getInstance().getTime());
		InputStream urlInputStream = null;
		InputStreamReader iStreamReader = null;
		try {
			//FeedFetcherCache feedInfoCache = HashMapFeedInfoCache.getInstance();
			//FeedFetcher fetcher = new HttpURLFeedFetcher(feedInfoCache);
			SyndFeedInput input = new SyndFeedInput();
			urlInputStream = feedUrl.openStream();
			iStreamReader = new InputStreamReader(urlInputStream);
			SyndFeed feed = input.build(iStreamReader);
			//URLConnection uc = feedUrl.openConnection();
			
			//uc.setConnectTimeout(120000);
			//System.out.println("before XmlReader");
			
			//SyndFeed feed = input.build(new XmlReader(uc));
			
			//System.out.println("after XmlReader");
/*			FileOps fo = new FileOps();
		    ImageThumbs it = new ImageThumbs();*/
			String feedSourceTitle = feed.getTitle();
			List<SyndEntry> feedEntries = feed.getEntries();
            for (SyndEntry se : feedEntries) {
                // HttpHeaders httpHeaders = hha.getHttpHeaders(new
                // URL(se.getLink().toString()));
                FeedEntry fe = new FeedEntry();
                StringBuffer sbuff = new StringBuffer();

                fe.setSourceLink(feedUrl.toString());
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
                //Store Image Thumb nails locally
                //TODO : 121 = A fix is needed for smoothing the thumbnail creation, it is heavy right now
                /*The method of rescaling the image is heavy and slow, 100% CPU utilization
                 * int k=0;
                for(String s:fe.getFeedImageUrls())
                {
                    fo.downloadFile(s);
                    it.createThumbnail(imageThumbsStoragePath+"img.jpg",imageThumbsStoragePath+fe.getFeedHashid()+"_"+k+".jpg", Integer.parseInt(imageThumbWidth), Integer.parseInt(imageThumbHeight));
                    k++;
                }*/
                feedList.add(fe);
            }		
		} catch (Exception ex) {
			System.out.println("ERROR: " + ex.getMessage()+" in " +feedUrl.toString());
			ex.printStackTrace();
		}
		finally {
			try {
				iStreamReader.close();
				urlInputStream.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return (ArrayList<FeedEntry>) feedList;
	}
}
