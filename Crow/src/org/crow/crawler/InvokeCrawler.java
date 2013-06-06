/**
 * 
 */
package org.crow.crawler;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.crow.classes.FeedEntry;
import org.crow.utils.MemoryObjects;


/**
 * @author viksin
 *
 */
public class InvokeCrawler implements Runnable{
	
	private String[] urlArray;
	private ICrawler crawler;
	public InvokeCrawler(String[] urlArray, ICrawler crawler) {
		this.urlArray = urlArray;
		this.crawler = crawler;
	}
	
	@Override
	public void run() {
		if (crawler instanceof FeedCrawler) {
			Map<String, List<FeedEntry>> feedMap = crawler.crawlStringUrls((Arrays.asList(urlArray)));	
	    	MemoryObjects.appendToMemMap("feeds", feedMap);
		}
		else if (crawler instanceof GenericCrawler) {
			((GenericCrawler) crawler).getLinkedURLs((Arrays.asList(urlArray)));
		}
/*    	@SuppressWarnings("unchecked")
		Set<String> idsSet = (Set<String>) MemoryObjects.getMemObjects().get("feedids");
    	System.out.println("size of feed map before "+feedMap.size());
    	for (String id : idsSet) {
			feedMap.remove(id);
		}
    	System.out.println("size of feed map after "+feedMap.size());*/
	}
}
