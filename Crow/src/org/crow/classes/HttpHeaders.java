/**
 * 
 */
package org.crow.classes;


/**
 * @author viksin
 *
 */
public class HttpHeaders {
	private int responseCode;
	private String contentType;
	private String httpVersion;
	private String etag;
	private String date;
	private String transferEncoding;
	private String expires;
	private String xXssProtection;
	private String lastModified;
	private String charset;
	private String server;
	private String cacheControl;
	private String xContentTypeOptions;
	private String ifModifiedSince;
	/**
	 * @param responseCode the responseCode to set
	 */
	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}
	/**
	 * @return the responseCode
	 */
	public int getResponseCode() {
		return responseCode;
	}
	/**
	 * @param contentType the contentType to set
	 */
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	/**
	 * @return the contentType
	 */
	public String getContentType() {
		return contentType;
	}
	/**
	 * @param httpVersion the httpVersion to set
	 */
	public void setHttpVersion(String httpVersion) {
		this.httpVersion = httpVersion;
	}
	/**
	 * @return the httpVersion
	 */
	public String getHttpVersion() {
		return httpVersion;
	}
	/**
	 * @param etag the etag to set
	 */
	public void setEtag(String etag) {
		this.etag = etag;
	}
	/**
	 * @return the etag
	 */
	public String getEtag() {
		return etag;
	}
	/**
	 * @param date the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}
	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}
	/**
	 * @param transferEncoding the transferEncoding to set
	 */
	public void setTransferEncoding(String transferEncoding) {
		this.transferEncoding = transferEncoding;
	}
	/**
	 * @return the transferEncoding
	 */
	public String getTransferEncoding() {
		return transferEncoding;
	}
	/**
	 * @param expires the expires to set
	 */
	public void setExpires(String expires) {
		this.expires = expires;
	}
	/**
	 * @return the expires
	 */
	public String getExpires() {
		return expires;
	}
	/**
	 * @param xXssProtection the xXssProtection to set
	 */
	public void setxXssProtection(String xXssProtection) {
		this.xXssProtection = xXssProtection;
	}
	/**
	 * @return the xXssProtection
	 */
	public String getxXssProtection() {
		return xXssProtection;
	}
	/**
	 * @param string the lastModified to set
	 */
	public void setLastModified(String lastModified) {
		this.lastModified = lastModified;
	}
	/**
	 * @return the lastModified
	 */
	public String getLastModified() {
		return lastModified;
	}
	/**
	 * @param charset the charset to set
	 */
	public void setCharset(String charset) {
		this.charset = charset;
	}
	/**
	 * @return the charset
	 */
	public String getCharset() {
		return charset;
	}
	/**
	 * @param server the server to set
	 */
	public void setServer(String server) {
		this.server = server;
	}
	/**
	 * @return the server
	 */
	public String getServer() {
		return server;
	}
	/**
	 * @param cacheControl the cacheControl to set
	 */
	public void setCacheControl(String cacheControl) {
		this.cacheControl = cacheControl;
	}
	/**
	 * @return the cacheControl
	 */
	public String getCacheControl() {
		return cacheControl;
	}
	/**
	 * @param xContentTypeOptions the xContentTypeOptions to set
	 */
	public void setxContentTypeOptions(String xContentTypeOptions) {
		this.xContentTypeOptions = xContentTypeOptions;
	}
	/**
	 * @return the xContentTypeOptions
	 */
	public String getxContentTypeOptions() {
		return xContentTypeOptions;
	}
	/**
	 * @return the ifModifiedSince
	 */
	public String getIfModifiedSince() {
		return ifModifiedSince;
	}
	/**
	 * @param ifModifiedSince the ifModifiedSince to set
	 */
	public void setIfModifiedSince(String ifModifiedSince) {
		this.ifModifiedSince = ifModifiedSince;
	}
}


