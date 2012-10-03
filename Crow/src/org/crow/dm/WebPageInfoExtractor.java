/**
 * 
 */
package org.crow.dm;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.crow.classes.WebDocument;
import org.crow.utils.HtmlUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import de.l3s.boilerpipe.BoilerpipeProcessingException;

/**
 * @author viksin
 *
 */
public class WebPageInfoExtractor
{
    public WebDocument getWebPageInfo(URL webPageURL) throws IOException, BoilerpipeProcessingException{
        HtmlUtils htmlUtils = new HtmlUtils();
        WebDocument webDoc = new WebDocument();
        Document doc = Jsoup.connect(webPageURL.toString()).get();
        
        String title = doc.title();
        Date date = Calendar.getInstance().getTime();
        String content = htmlUtils.getCleanTextFromHTML(webPageURL.toString());
        String domain = webPageURL.getHost();
        List<String> allLinksList = htmlUtils.getURLsFromHTML(doc);
        List<String> innerLinksList = new ArrayList<String>();
        List<String> outerLinksList = new ArrayList<String>();

        for (String link : allLinksList) {
            if(link.contains(domain)){
                innerLinksList.add(link);
            }
            else{
                outerLinksList.add(link);
            }
        }
        webDoc.setTitle(title);
        webDoc.setDomain(domain);
        webDoc.setGetDate(date);
        webDoc.setPageContent(content);
        webDoc.setPageURL(webPageURL);
        webDoc.setInnerLinks(innerLinksList);
        webDoc.setOuterLinks(outerLinksList);
        return webDoc;
    }
}
