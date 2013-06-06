/**
 * 
 */
package org.crow.crawler;

import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.crow.classes.FeedEntry;
import org.crow.utils.HtmlUtils;
import org.crow.utils.MemoryObjects;

/**
 * @author viksin
 *
 */
public class GenericCrawler implements ICrawler {
    private HtmlUtils htmlUtils;
    public GenericCrawler(){
    	htmlUtils = new HtmlUtils();
    }
	/* (non-Javadoc)
	 * @see org.crow.crawler.ICrawler#crawlSingleUrl(java.net.URL)
	 */
	@Override
	public List<FeedEntry> crawlSingleUrl(URL url) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.crow.crawler.ICrawler#crawlUrls(java.util.List)
	 */
	@Override
	public Map<URL, List<FeedEntry>> crawlUrls(List<URL> urls) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.crow.crawler.ICrawler#crawlStringUrls(java.util.List)
	 */
	@Override
	public Map<String, List<FeedEntry>> crawlStringUrls(List<String> urls) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.crow.crawler.ICrawler#crawlAndMerge(java.util.List)
	 */
	@Override
	public List<FeedEntry> crawlAndMerge(List<String> urls) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Set<String> getLinkedURLs(String URL){
        return htmlUtils.getURLsFromHTML(URL);
	}
	public void getLinkedURLs(List<String> URLs){
		for (String URL : URLs) {
			System.out.println("getting links from "+ URL +" "+ Thread.currentThread().getName() +"current map size"+ MemoryObjects.getMemObjects().size());
	        MemoryObjects.addObjectToMemMap(URL, htmlUtils.getURLsFromHTML(URL));
		}
	}
}
