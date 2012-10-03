/**
 * 
 */
package org.crow.crawler;

import java.net.URL;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.crow.classes.FeedEntry;

/**
 * @author viksin
 *
 */
public interface ICrawler {
	public List<FeedEntry> crawlSingleUrl(URL url);
	public ConcurrentHashMap<URL, List<FeedEntry>> crawlUrls(List<URL> urls);
	public ConcurrentHashMap<String, List<FeedEntry>> crawlStringUrls(List<String> urls);
	//public void crawlImages(List<URL>);
	//public void docCrawler();
	//public void generalCrawler();
}
