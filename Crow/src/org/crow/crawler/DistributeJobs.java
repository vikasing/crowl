/**
 * 
 */
package org.crow.crawler;

import java.util.Arrays;
import java.util.Set;

/**
 * @author viksin
 *
 */
public class DistributeJobs {

	private Thread[] threads = null;
	private int numOfTs;
	
	public DistributeJobs(int numOfThreads) {
		this.numOfTs = numOfThreads;
		threads = new Thread[numOfThreads];
	}

	public void distributeFeedLinks(Set<String> urlSet) {
		try {
			String[] urlArray = urlSet.toArray(new String[urlSet.size()]);
			int numOfUrls = urlArray.length;
			int partialSize = numOfUrls/numOfTs;
			for (int i = 0; i < numOfTs; i++) {
				if (i==numOfTs-1) {
					threads[i] = new Thread((new InvokeCrawler(Arrays.copyOfRange(urlArray, i*partialSize, numOfUrls-1), new FeedCrawler())));
					threads[i].setName("t"+i);
					threads[i].start();
				}
				else {
					threads[i] = new Thread((new InvokeCrawler(Arrays.copyOfRange(urlArray, i*partialSize, (i+1)*partialSize),new FeedCrawler())));
					threads[i].setName("t"+i);
					threads[i].start();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void distributeGenLinks(Set<String> urlSet) {
		try {
			String[] urlArray = urlSet.toArray(new String[urlSet.size()]);
			int numOfUrls = urlArray.length;
			int partialSize = numOfUrls/numOfTs;
			for (int i = 0; i < numOfTs; i++) {
				if (i==numOfTs-1) {
					threads[i] = new Thread((new InvokeCrawler(Arrays.copyOfRange(urlArray, i*partialSize, numOfUrls-1), new GenericCrawler())));
					threads[i].setName("t"+i);
					threads[i].start();
				}
				else {
					threads[i] = new Thread((new InvokeCrawler(Arrays.copyOfRange(urlArray, i*partialSize, (i+1)*partialSize),new GenericCrawler())));
					threads[i].setName("t"+i);
					threads[i].start();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void joinThreads(){
		for (int i = 0; i < threads.length; i++) {
			try {
				System.out.println("joining thread "+threads[i].getName());
				threads[i].join();
				System.out.println("joined thread "+threads[i].getName());
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	public boolean getThreadsAliveStatus(){
		boolean areAlive = false;
		for (int i = 0; i < threads.length; i++) {
			if (threads[i]==null) {
				areAlive =false;
			}
			else if(threads[i].isAlive()){
				areAlive = true;
				System.out.println(threads[i].getName()+" is alive");
				break;
			}
		}
		return areAlive;
	}
}
