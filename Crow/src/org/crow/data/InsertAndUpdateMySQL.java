/**
 * 
 */
package org.crow.data;

import java.util.List;

import org.crow.classes.FeedEntry;

import com.mongodb.Mongo;

/**
 * @author viksin
 *
 */
public class InsertAndUpdateMySQL  implements InsertAndUpdateOpsInterface {

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

	/* (non-Javadoc)
	 * @see org.crow.data.InsertAndUpdateOpsInterface#insertFeeds(java.util.List, java.lang.String, java.lang.String, com.mongodb.Mongo)
	 */
	@Override
	public boolean insertFeeds(List<FeedEntry> feedList, String dbName,
			String collection, Mongo m) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
