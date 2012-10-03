package org.crow.classes;

import java.util.Date;
import java.util.List;

import com.sun.syndication.feed.synd.SyndEntry;

/**
 * @author viksin
 *
 */
public class FeedEntry{
	

	private String feedHashid;
	private String lastModDateOnServer;
	private List<String> feedImageUrls;
	private List<String> encodedImages;
	private SyndEntry feedEntry;
	private String noHtmlContent;
	private Date feedGetDateTime;
	private String sourceTitle;
	private String sourceLink;
	private String completeContent;
	/**
	 * @param feedHashid the feedHashid to set
	 */
	public void setFeedHashid(String feedHashid) {
		this.feedHashid = feedHashid;
	}
	/**
	 * @return the feedHashid
	 */
	public String getFeedHashid() {
		return feedHashid;
	}
	/**
	 * @param lastModDateOnServer the lastModDateOnServer to set
	 */
	public void setLastModDateOnServer(String lastModDateOnServer) {
		this.lastModDateOnServer = lastModDateOnServer;
	}
	/**
	 * @return the lastModDateOnServer
	 */
	public String getLastModDateOnServer() {
		return lastModDateOnServer;
	}
	
	
	/**
	 * @param feedEntry the feedEntry to set
	 */
	public void setFeedEntry(SyndEntry feedEntry) {
		this.feedEntry = feedEntry;
	}
	/**
	 * @return the feedEntry
	 */
	public SyndEntry getFeedEntry() {
		return feedEntry;
	}
	/**
	 * @param noHtmlContent the noHtmlContent to set
	 */
	public void setNoHtmlContent(String noHtmlContent) {
		this.noHtmlContent = noHtmlContent;
	}
	/**
	 * @return the noHtmlContent
	 */
	public String getNoHtmlContent() {
		return noHtmlContent;
	}
	/**
	 * @param feedGetDate the feedGetDate to set
	 */
	public void setFeedGetDateTime(Date date) {
		this.feedGetDateTime = date;
	}
	/**
	 * @return the feedGetDate
	 */
	public Date getFeedGetDateTime() {
		return feedGetDateTime;
	}
	/**
	 * @param sourceTitle the sourceTitle to set
	 */
	public void setSourceTitle(String sourceTitle) {
		this.sourceTitle = sourceTitle;
	}
	/**
	 * @return the sourceTitle
	 */
	public String getSourceTitle() {
		return sourceTitle;
	}
	/**
	 * @param sourceLink the sourceLink to set
	 */
	public void setSourceLink(String sourceLink) {
		this.sourceLink = sourceLink;
	}
	/**
	 * @return the sourceLink
	 */
	public String getSourceLink() {
		return sourceLink;
	}
    /**
     * @param feedImageUrl the feedImageUrl to set
     */
    public void setFeedImageUrls(List<String> feedImageUrls)
    {
        this.feedImageUrls = feedImageUrls;
    }
    /**
     * @return the feedImageUrl
     */
    public List<String> getFeedImageUrls()
    {
        return feedImageUrls;
    }
    /**
     * @param completeContent the completeContent to set
     */
    public void setCompleteContent(String completeContent)
    {
        this.completeContent = completeContent;
    }
    /**
     * @return the completeContent
     */
    public String getCompleteContent()
    {
        return completeContent;
    }
	/**
	 * @return the encodedImages
	 */
	public List<String> getEncodedImages() {
		return encodedImages;
	}
	/**
	 * @param encodedImages the encodedImages to set
	 */
	public void setEncodedImages(List<String> encodedImages) {
		this.encodedImages = encodedImages;
	}

}
