/**
 * 
 */
package org.crow.crawler;

import java.util.ArrayList;

/**
 * @author viksin
 *
 */
public class Crawler {

	private ArrayList<String> urls;

	/**
	 * @param urls the urls to set
	 */
	public void setUrls(ArrayList<String> urls) {
		this.urls = urls;
	}

	/**
	 * @return the urls
	 */
	public ArrayList<String> getUrls() {
		return urls;
	}
}
