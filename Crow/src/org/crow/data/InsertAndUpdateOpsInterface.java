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
public interface InsertAndUpdateOpsInterface {
	public boolean singleInsert();
	public boolean multiInsert();
	public boolean updateDB();
	public boolean insertFeeds(List<FeedEntry> feedList);
	/**
	 * @param feedList
	 * @param db
	 * @param collection
	 * @return
	 */
	boolean insertFeeds(List<FeedEntry> feedList, String db, String collection);
	/**
	 * @param feedList
	 * @param dbName
	 * @param collection
	 * @param m
	 * @return
	 */
	boolean insertFeeds(List<FeedEntry> feedList, String dbName,
			String collection, Mongo m);
}
