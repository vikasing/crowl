/**
 * 
 */
package org.crow.classes;

/**
 * @author vikasing
 *
 */
public class WebDocMetadata {
	private double pageSizeInKB;
	private double pageContentLength; // total number of chars
	private double numOfSentences;
	/**
	 * @return the pageSizeInKB
	 */
	public double getPageSizeInKB() {
		return pageSizeInKB;
	}
	/**
	 * @param pageSizeInKB the pageSizeInKB to set
	 */
	public void setPageSizeInKB(double pageSizeInKB) {
		this.pageSizeInKB = pageSizeInKB;
	}
	/**
	 * @return the pageContentLength
	 */
	public double getPageContentLength() {
		return pageContentLength;
	}
	/**
	 * @param pageContentLength the pageContentLength to set
	 */
	public void setPageContentLength(double pageContentLength) {
		this.pageContentLength = pageContentLength;
	}
	/**
	 * @return the numOfSentences
	 */
	public double getNumOfSentences() {
		return numOfSentences;
	}
	/**
	 * @param numOfSentences the numOfSentences to set
	 */
	public void setNumOfSentences(double numOfSentences) {
		this.numOfSentences = numOfSentences;
	}
	
}
