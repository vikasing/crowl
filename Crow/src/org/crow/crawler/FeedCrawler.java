/**
 * 
 */
package org.crow.crawler;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.impl.client.DefaultHttpClient;
import org.crow.base.FeedParser;
import org.crow.classes.FeedEntry;

/**
 * @author viksin
 *
 */
public class FeedCrawler implements ICrawler {
    private FeedParser feedParser;
	
    public FeedCrawler(){
    	feedParser = new FeedParser(new DefaultHttpClient());
    }

    @Override
    public List<FeedEntry> crawlSingleUrl(URL url)
    {
        return feedParser.parser(url);        
    }


    @Override
    public Map<URL, List<FeedEntry>> crawlUrls(List<URL> urls)
    {
        Map<URL, List<FeedEntry>> urlFeedHashMap = new HashMap<URL, List<FeedEntry>>();
        for(URL url : urls)
        {    if (url!=null) {
            List <FeedEntry> feedList = crawlSingleUrl(url);
            urlFeedHashMap.put(url, feedList);
			}
        }
        return urlFeedHashMap;
    }

    @Override
    public Map<String, List<FeedEntry>> crawlStringUrls(List<String> urls)
    {
    	Map<String, List<FeedEntry>> urlFeedHashMap = new HashMap<String, List<FeedEntry>>();
    	for(String str : urls){   
    		URL url = null;
    		try {
    			url = new URL(str);
    		}
    		catch (MalformedURLException e) {
    			e.printStackTrace();
    		}
    		if (url!=null) {
        		urlFeedHashMap.put(str, crawlSingleUrl(url));
			}
    	}
    	return urlFeedHashMap;
    }
    @Override
    public List<FeedEntry> crawlAndMerge(List<String> urls)
    {
    	List<FeedEntry> feedList = new ArrayList<FeedEntry>();
    	for(String str : urls){   
    		URL url = null;
    		try {
    			url = new URL(str);
    		}
    		catch (MalformedURLException e) {
    			e.printStackTrace();
    		}
    		if (url!=null) {
    			feedList.addAll(crawlSingleUrl(url));
			}
    	}
    	return feedList;
    }
}
