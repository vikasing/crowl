package org.crow.classes;

import com.sun.syndication.feed.synd.SyndFeed;

public class FeedUrl extends UrlBase{
	private String feedUrl; 
	private String syndType;
	private String lastContentChangeOn; // last datetime when feed content was changed
	private String lastVisit;
	private String nextVisit;
	private String changeRate;
	private SyndFeed feedInfo;
	/**
	 * @param syndType the syndType to set
	 */
	public void setSyndType(String syndType) {
		this.syndType = syndType;
	}
	/**
	 * @return the feedUrl
	 */
	public String getFeedUrl() {
		return feedUrl;
	}
	/**
	 * @param feedUrl the feedUrl to set
	 */
	public void setFeedUrl(String feedUrl) {
		this.feedUrl = feedUrl;
	}
	/**
	 * @return the syndType
	 */
	public String getSyndType() {
		return syndType;
	}
	/**
	 * @param lastContentChangeOn the lastContentChangeOn to set
	 */
	public void setLastContentChangeOn(String lastContentChangeOn) {
		this.lastContentChangeOn = lastContentChangeOn;
	}
	/**
	 * @return the lastContentChangeOn
	 */
	public String getLastContentChangeOn() {
		return lastContentChangeOn;
	}
	/**
	 * @param lastVisit the lastVisit to set
	 */
	public void setLastVisit(String lastVisit) {
		this.lastVisit = lastVisit;
	}
	/**
	 * @return the lastVisit
	 */
	public String getLastVisit() {
		return lastVisit;
	}
	/**
	 * @param nextVisit the nextVisit to set
	 */
	public void setNextVisit(String nextVisit) {
		this.nextVisit = nextVisit;
	}
	/**
	 * @return the nextVisit
	 */
	public String getNextVisit() {
		return nextVisit;
	}
	/**
	 * @param changeRate the changeRate to set
	 */
	public void setChangeRate(String changeRate) {
		this.changeRate = changeRate;
	}
	/**
	 * @return the changeRate
	 */
	public String getChangeRate() {
		return changeRate;
	}
	/**
	 * @param feedInfo the feedInfo to set
	 */
	public void setFeedInfo(SyndFeed feedInfo) {
		this.feedInfo = feedInfo;
	}
	/**
	 * @return the feedInfo
	 */
	public SyndFeed getFeedInfo() {
		return feedInfo;
	}
	

}
