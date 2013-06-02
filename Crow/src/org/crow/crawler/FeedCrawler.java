/**
 * 
 */
package org.crow.crawler;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.impl.client.DefaultHttpClient;
import org.crow.base.FeedParser;
import org.crow.classes.FeedEntry;
import org.crow.utils.MemoryObjects;

/**
 * @author viksin
 *
 */
public class FeedCrawler implements ICrawler {
    private FeedParser feedParser;
	private Set<String> badURLSet;
    public FeedCrawler(){
    	feedParser = new FeedParser(new DefaultHttpClient());
    	badURLSet = new HashSet<String>();
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
        {   
        	if (url!=null) {
	            List <FeedEntry> feedList = crawlSingleUrl(url);
	            if (feedList.size()>0) {
	                urlFeedHashMap.put(url, feedList);
				}
	            else {
					badURLSet.add(url.toString());
				}
			}
        }
        MemoryObjects.addObjectToMemMap("badurls", badURLSet);
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
    			List<FeedEntry> feedList = crawlSingleUrl(url);
    			if (feedList.size()>0) {
            		urlFeedHashMap.put(str, feedList);
				}
    			else {
					badURLSet.add(url.toString());
				}
			}
    	}
        MemoryObjects.addObjectToMemMap("badurls", badURLSet);
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
    			List<FeedEntry> oneFeedList = crawlSingleUrl(url);
    			if (oneFeedList.size()>0) {
        			feedList.addAll(oneFeedList);
				}
    			else {
					badURLSet.add(url.toString());
				}
			}
    	}
        MemoryObjects.addObjectToMemMap("badurls", badURLSet);
    	return feedList;
    }
}
