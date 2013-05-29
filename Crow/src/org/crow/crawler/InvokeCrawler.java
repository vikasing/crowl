/**
 * 
 */
package org.crow.crawler;

import java.util.Arrays;
import java.util.List;

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
		List<FeedEntry> feedList = crawler.crawlAndMerge((Arrays.asList(urlArray)));	
    	MemoryObjects.appendToMemMap("feeds", feedList);
	}
}
