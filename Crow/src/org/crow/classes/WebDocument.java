/**
 * 
 */
package org.crow.classes;

import java.net.URL;
import java.util.Date;
import java.util.List;

/**
 * @author viksin
 *
 */
public class WebDocument
{
    private URL pageURL;
    private String domain;
    private String title;
    private Date getDate;
    private String pageContent;
    private String language;
    private String author;
    private List<String> innerLinks;
    private List<String> outerLinks;
    /**
     * @param pageURL the pageURL to set
     */
    public void setPageURL(URL pageURL)
    {
        this.pageURL = pageURL;
    }
    /**
     * @return the pageURL
     */
    public URL getPageURL()
    {
        return pageURL;
    }
    /**
     * @param domain the domain to set
     */
    public void setDomain(String domain)
    {
        this.domain = domain;
    }
    /**
     * @return the domain
     */
    public String getDomain()
    {
        return domain;
    }
    /**
     * @param title the title to set
     */
    public void setTitle(String title)
    {
        this.title = title;
    }
    /**
     * @return the title
     */
    public String getTitle()
    {
        return title;
    }
    /**
     * @param getDate the getDate to set
     */
    public void setGetDate(Date getDate)
    {
        this.getDate = getDate;
    }
    /**
     * @return the getDate
     */
    public Date getGetDate()
    {
        return getDate;
    }
    /**
     * @param pageContent the pageContent to set
     */
    public void setPageContent(String pageContent)
    {
        this.pageContent = pageContent;
    }
    /**
     * @return the pageContent
     */
    public String getPageContent()
    {
        return pageContent;
    }
    /**
     * @param language the language to set
     */
    public void setLanguage(String language)
    {
        this.language = language;
    }
    /**
     * @return the language
     */
    public String getLanguage()
    {
        return language;
    }
    /**
     * @param author the author to set
     */
    public void setAuthor(String author)
    {
        this.author = author;
    }
    /**
     * @return the author
     */
    public String getAuthor()
    {
        return author;
    }
    /**
     * @param innerLinks the innerLinks to set
     */
    public void setInnerLinks(List<String> innerLinks)
    {
        this.innerLinks = innerLinks;
    }
    /**
     * @return the innerLinks
     */
    public List<String> getInnerLinks()
    {
        return innerLinks;
    }
    /**
     * @param outerLinks the outerLinks to set
     */
    public void setOuterLinks(List<String> outerLinks)
    {
        this.outerLinks = outerLinks;
    }
    /**
     * @return the outerLinks
     */
    public List<String> getOuterLinks()
    {
        return outerLinks;
    }    
}
