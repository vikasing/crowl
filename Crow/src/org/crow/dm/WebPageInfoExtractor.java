/**
 * 
 */
package org.crow.dm;

import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.crow.classes.WebDocument;
import org.crow.utils.HtmlUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.vikasing.nicetext.NiceText;

/**
 * @author vikasing
 *
 */
public class WebPageInfoExtractor
{
    public WebDocument getWebPageInfo(URL webPageURL){
        HtmlUtils htmlUtils = new HtmlUtils();
        WebDocument webDoc = new WebDocument();
        NiceText niceText = new NiceText();
        try {
			Document doc = Jsoup.connect(webPageURL.toString()).timeout(30000).get();			
			String title = doc.title();
			Date date = Calendar.getInstance().getTime();
			//String content = htmlUtils.getCleanTextFromHTML(webPageURL.toString());
			//String content = niceText.getNiceText(doc).getNiceText();
			String domain = webPageURL.getHost();
			String domainName = domain.startsWith("www.") ? domain.substring(4) : domain;
			Set<String> allLinks = htmlUtils.getURLsFromHTML(doc);
			List<String> innerLinks = new ArrayList<String>();
			List<String> outerLinks = new ArrayList<String>();

			for (String link : allLinks) {
			    if(link.contains(domainName)){
			        innerLinks.add(link);
			    }
			    else{
			        outerLinks.add(link);
			    }
			}
			webDoc.setAMainPage(htmlUtils.isAFrontPage(doc));
			webDoc.setTitle(title);
			webDoc.setDomain(domain);
			webDoc.setGetDate(date);
			//webDoc.setPageContent(content);
			webDoc.setPageURL(webPageURL);
			webDoc.setInnerLinks(innerLinks);
			webDoc.setOuterLinks(outerLinks);
		} catch (Exception e) {
			e.printStackTrace();
		}
        return webDoc;
    }
}
