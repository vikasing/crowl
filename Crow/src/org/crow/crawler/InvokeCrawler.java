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
	public InvokeCrawler(String[] urlArray) {
		this.urlArray = urlArray;
		System.out.println(urlArray.length);
	}
	@Override
	public void run() {
		ICrawler crawler = new FeedCrawler();
		Map<String, List<FeedEntry>> feedMap = crawler.crawlStringUrls((Arrays.asList(urlArray)));	
/*    	@SuppressWarnings("unchecked")
		Set<String> idsSet = (Set<String>) MemoryObjects.getMemObjects().get("feedids");
    	System.out.println("size of feed map before "+feedMap.size());
    	for (String id : idsSet) {
			feedMap.remove(id);
		}
    	System.out.println("size of feed map after "+feedMap.size());*/
    	MemoryObjects.appendToMemMap("feeds", feedMap);
	}
}
