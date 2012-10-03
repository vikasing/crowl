/**
 * 
 */
package org.crow.crawler;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.crow.base.FeedParser;
import org.crow.classes.FeedEntry;

/**
 * @author viksin
 *
 */
public class FeedCrawler implements ICrawler {
	
    /* (non-Javadoc)
     * @see org.crow.crawler.ICrawler#crawlSingleUrl(java.net.URL)
     */
    @Override
    public List<FeedEntry> crawlSingleUrl(URL url)
    {
        FeedParser feedParser = new FeedParser();
        List<FeedEntry> feedList = feedParser.parser(url);
        return feedList;
        
    }

    /* (non-Javadoc)
     * @see org.crow.crawler.ICrawler#crawlUrls(java.util.List)
     */
    @Override
    public ConcurrentHashMap<URL, List<FeedEntry>> crawlUrls(List<URL> urls)
    {
        ConcurrentHashMap<URL, List<FeedEntry>> urlFeedHashMap = new ConcurrentHashMap<URL, List<FeedEntry>>();
        for(URL url : urls)
        {    
            List <FeedEntry> feedList = crawlSingleUrl(url);
            urlFeedHashMap.put(url, feedList);
        }
        return urlFeedHashMap;
    }

    /* (non-Javadoc)
     * @see org.crow.crawler.ICrawler#crawlUrls(java.util.ArrayList)
     */
    @Override
    public ConcurrentHashMap<String, List<FeedEntry>> crawlStringUrls(List<String> urls)
    {
        ConcurrentHashMap<String, List<FeedEntry>> urlFeedHashMap = new ConcurrentHashMap<String, List<FeedEntry>>();
        for(String str : urls)
        {   URL url = null;
        try {
            url = new URL(str);
        }
        catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
            List <FeedEntry> feedList = crawlSingleUrl(url);
            urlFeedHashMap.put(str, feedList);
        }
        return urlFeedHashMap;
    }

}
