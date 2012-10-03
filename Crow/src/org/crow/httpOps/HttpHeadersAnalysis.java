/**
 * 
 */
package org.crow.httpOps;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

import org.crow.classes.HttpHeaders;

import sun.net.www.protocol.http.HttpURLConnection;
/**
 * @author viksin
 *
 */
public class HttpHeadersAnalysis {
	public HttpHeaders getHttpHeaders(URL url) {
		HttpHeaders httpHeaders = new HttpHeaders();
		URLConnection conn;
		try {
			conn = url.openConnection();
			if (conn instanceof HttpURLConnection) {
				HttpURLConnection httpConn = (HttpURLConnection) conn;
				httpHeaders.setResponseCode(httpConn.getResponseCode());
				httpHeaders.setContentType(httpConn.getContentType());
				httpHeaders.setLastModified(httpConn.getHeaderField("Last-Modified"));
				httpHeaders.setDate(httpConn.getHeaderField("Date"));
				httpHeaders.setCacheControl(httpConn.getHeaderField("Cache-Control"));
				httpHeaders.setEtag(httpConn.getHeaderField("ETag"));
				httpHeaders.setExpires(httpConn.getHeaderField("Expires"));
				httpHeaders.setServer(httpConn.getHeaderField("Server"));
				httpHeaders.setTransferEncoding(httpConn.getHeaderField("Transfer-Encoding"));
				httpHeaders.setxContentTypeOptions(httpConn.getHeaderField("X-Content-Type-Options"));
				httpHeaders.setxXssProtection(httpConn.getHeaderField("X-XSS-Protection"));
				httpHeaders.setIfModifiedSince(httpConn.getHeaderField("If-Modified-Since"));
			}
			} catch (IOException e) {
			e.printStackTrace();
		}
		return httpHeaders;
	}

	public void setHttpHeaders(URL url)
	{
		URLConnection conn;
		try {
			conn = url.openConnection();
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Crowl");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
