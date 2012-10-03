/**
 * 
 */
package org.crow.httpOps;

/**
 * @author viksin
 * This class tries to determine the type of a url i.e. if it is feed url or some other type.
 */
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

import org.crow.classes.FeedUrl;
import org.crow.classes.UrlBase;
import org.crow.data.DBUtils;
import org.crow.enums.UrlType;

import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

//import sun.net.www.http.HttpClient;

public class UrlDetective {
	Thread searchThread;
	public static final String DISALLOW = "Disallow:";
	private UrlBase urlBase = new UrlBase();
	private FeedUrl feedUrl = new FeedUrl();

	public UrlType determineUrlType(String url) {
		String contentType = "";
		ArrayList<String> urls = new ArrayList<String>();
		HttpHeadersAnalysis httpHeaderAnalysis = new HttpHeadersAnalysis();
		DBUtils gUrls = new DBUtils();
		//urls = gUrls.getAllUrls();
		urlBase.setUrlAddress(url);
		UrlType utype=null;
		// next check if the url is syntactically correct
		// if it is a relative url try to get the domain for the same and then
		// analyze
		// for(String url:urls)
		// {
		try {
			URL passedUrl = new URL(url);
			boolean alreadyConnected = false;
			boolean isCrawlingAllowed = analyzeRobotsDotText(passedUrl);
			urlBase.setHeaders(httpHeaderAnalysis.getHttpHeaders(passedUrl));
			contentType = urlBase.getHeaders().getContentType();
			if (contentType.contains("html")) {
				utype = UrlType.HTML;
			} 
			else if (contentType.contains("xml")) {
				try {
					SyndFeedInput input = new SyndFeedInput();
					SyndFeed feed = input.build(new XmlReader(passedUrl));
					String feedtype = feed.getFeedType();
					if (feedtype.contains("rss")) {
						utype = UrlType.RSS;
					}
					if (feedtype.contains("atom")) {
						utype = UrlType.ATOM;
					}
				} catch (Exception e) {
					utype = UrlType.NO_SYND_XML;
					e.printStackTrace();
				}
			} 
			else {
				// TODO for images, plain text, different doc types etc.
			}
			if (isCrawlingAllowed && utype!=null) {
				if(utype.equals(UrlType.HTML))
				{
					// TODO send to HrefGrabber 
				}
				else if(utype.equals(UrlType.RSS)|| utype.equals(UrlType.ATOM))
				{
					// TODO send to FeedURLStore
				}
				else {
					// TODO store the url in JunkLinks table
				}
			} else {
				// TODO put the url in future vault.
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
        return utype;

		// }
	}

	private boolean analyzeRobotsDotText(URL url) {
		String strHost = url.getHost();
		String strRobot = "http://" + strHost + "/robots.txt";
		boolean isCrawlingAllowed = false;
		URL urlRobot;
		try {
			urlRobot = new URL(strRobot);
			InputStream urlRobotStream = urlRobot.openStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(urlRobotStream));
			Robotstxt robotstxt = new Robotstxt(br);
			isCrawlingAllowed = robotstxt.isCrawlingAllowed();
		} catch (Exception e) {
		}
		return isCrawlingAllowed;
	}
}
