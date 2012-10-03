package org.crow.data;
/**
 * @author viksin
 *
 */
import java.util.ArrayList;

public class DBUtils {
	public ArrayList<String> getAllUrls(Query query)
	{
		MySQLSelectOps sOps = new MySQLSelectOps();
		ArrayList<String> urls = new ArrayList<String>();
		urls=sOps.getOneColumnData(query.getStatement(), query.getSelectColumn());
		return urls;
	}

}
