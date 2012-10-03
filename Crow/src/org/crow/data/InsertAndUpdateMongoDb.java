/**
 * 
 */
package org.crow.data;

import java.util.Iterator;
import java.util.List;

import org.crow.classes.FeedEntry;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.WriteResult;
import com.sun.syndication.feed.module.DCModule;
import com.sun.syndication.feed.synd.SyndCategory;
import com.sun.syndication.feed.synd.SyndContent;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndLink;

/**
 * @author viksin
 *
 */
public class InsertAndUpdateMongoDb implements InsertAndUpdateOpsInterface {

	@Override
	public boolean multiInsert() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean singleInsert() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateDB() {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see org.crow.data.InsertAndUpdateOpsInterface#insertFeeds(com.sun.syndication.feed.synd.SyndFeed)
	 */
	@Override
	public boolean insertFeeds(List<FeedEntry> feedList,String dbName,String collection, Mongo m) {
		try {
			//TODO handle the multiple unnecessary connections
			//Mongo m = new Mongo(Constants.MongoDBServer,Constants.MongoDBServerPort);
			DB db = m.getDB(dbName);
			DBCollection coll = db.getCollection(collection);
			

			for (FeedEntry fe : feedList) {
				SyndEntry entry = fe.getFeedEntry();
				DCModule entrydc = (DCModule) entry.getModule(DCModule.URI);
				DBObject feed = new BasicDBObject();
				
				feed.put("source", fe.getSourceTitle());
				feed.put("sourcelink", fe.getSourceLink());
				feed.put("getdate", fe.getFeedGetDateTime());
				feed.put("hashid", fe.getFeedHashid());
				//coll.ensureIndex(feed,"hashid", true);
				DBObject feedData = new BasicDBObject();
				feedData.put("title", entry.getTitle());
				if (entry.getLink() != null) {
					feedData.put("link", entry.getLink());
				} 
				else if (entry.getLinks().size() > 0) {
					Iterator<?> links = entry.getLinks().iterator();
					while (links.hasNext()) {
						SyndLink link = (SyndLink) links.next();
						if (link.getRel().equals("alternate"))
							feedData.put("link", link.toString());
					}
				}

				if (entry.getContents().size() > 0) {
					int i = 1;
					Iterator<?> contents = entry.getContents().iterator();
					while (contents.hasNext()) {
						SyndContent content = (SyndContent) contents.next();
						feedData.put("description" + i, content.getValue());
						i++;
					}
				} else if (entry.getDescription() != null) {
					feedData.put("description1", entry.getDescription().getValue());
				}

				if (entry.getAuthor() != null) {
					feedData.put("author", entry.getAuthor());
				} else if (entrydc.getCreator() != null) {
					feedData.put("author", entrydc.getCreator());
				}
				if (entry.getPublishedDate() != null) {
					feedData.put("publishdate", entry.getPublishedDate().toString());
				} else if (entrydc.getDate() != null) {
					feedData.put("publishdate", entrydc.getDate().toString());
				}
				String categories = "";
				if (entry.getCategories().size() > 0) {
					Iterator<?> cats = entry.getCategories().iterator();
					while (cats.hasNext()) {
						SyndCategory cat = (SyndCategory) cats.next();
						categories += cat.getName() + ",";
					}
				}
				feedData.put("categories", categories);
				if(entry.getUpdatedDate()!=null){
				feedData.put("updatedate", entry.getUpdatedDate().toString());
				}
				feedData.put("nohtmlcontent", fe.getNoHtmlContent());
				feedData.put("completecontent", fe.getCompleteContent());
				//TODO uncomment the below line after the fix of TODO 121 in FeedParser class
				//feedData.put("imagecount", fe.getFeedImageUrls().size());
				feed.put("feeddata", feedData);
				WriteResult insertResult= coll.insert(feed);
				//DBCursor cur = coll.find();
				
				System.out.println(insertResult.toString());
			}
			//DBCursor cur = coll.find();
			//while (cur.hasNext()) {
			//	System.out.println(cur.next());
			//}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see org.crow.data.InsertAndUpdateOpsInterface#insertFeeds(java.util.List)
	 */
	@Override
	public boolean insertFeeds(List<FeedEntry> feedList) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see org.crow.data.InsertAndUpdateOpsInterface#insertFeeds(java.util.List, java.lang.String, java.lang.String)
	 */
	@Override
	public boolean insertFeeds(List<FeedEntry> feedList, String db,
			String collection) {
		// TODO Auto-generated method stub
		return false;
	}
	

}
