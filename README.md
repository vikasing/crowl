crowl
=====

A fast web-crawler with some additional machine learning capabilities.

Current Release
===============
Has a limited capability of crawling RSS/ATOM feeds and storing them in MongoDB.


How To Get Started
==================
Get the code and test it using following code:

```java
public class Test {
	/**
	 * @param args
	 */
	public static void main(String[] args){
        ICrawler crawler = new FeedCrawler();
        try {
        	List<String> urlList = new ArrayList<String>();
            urlList.add("http://feeds.feedburner.com/basho/knDR");
            urlList.add("http://feeds.feedburner.com/nosql");
            urlList.add("http://www.java.net/blog/120355/feed");
            ConcurrentHashMap<String, List<FeedEntry>> feedMap = crawler.crawlStringUrls(urlList);
            Iterator<String> itr = feedMap.keySet().iterator();
            while(itr.hasNext()){
                String url = itr.next().toString();
                List<FeedEntry> feed = feedMap.get(url);
                System.out.println("Feed(RSS/ATOM) URL: "+url);
                for(FeedEntry fe: feed)
                {                
                    SyndEntry se = fe.getFeedEntry();                            
                    System.out.println("Title: "+se.getTitle()+"\n"+"Content: "+fe.getNoHtmlContent()+"\n");
                }
            }            
	    }
	    catch (Exception e) {
	        e.printStackTrace();
	    }
    }
}
```

