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
public class HTMLCrawler implements ICrawler
{

    /* (non-Javadoc)
     * @see org.crow.crawler.ICrawler#crawlSingleUrl(java.net.URL)
     */
    @Override
    public List<FeedEntry> crawlSingleUrl(URL url)
    {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see org.crow.crawler.ICrawler#crawlUrls(java.util.List)
     */
    @Override
    public ConcurrentHashMap<URL, List<FeedEntry>> crawlUrls(List<URL> urls)
    {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see org.crow.crawler.ICrawler#crawlUrls(java.util.ArrayList)
     */
    @Override
    public ConcurrentHashMap<String, List<FeedEntry>> crawlStringUrls(
            List<String> urls)
    {
        // TODO Auto-generated method stub
        return null;
    }

}
