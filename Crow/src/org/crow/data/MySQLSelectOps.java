package org.crow.data;
/**
 * @author viksin
 *
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.crow.classes.FeedEntry;

public class MySQLSelectOps {
	
	public Map<String,FeedEntry> multipleSelect(String query)
	{
		Map<String,FeedEntry> dataMap= new HashMap<String, FeedEntry>();
		return dataMap;
	}
	
	public Map<String,String> getCategorizedData()
	{
		String[] categories={"General","Entertaintment","Sports","Business","Tech","National","International"};
		Map<String,String> dataMap= new HashMap<String, String>();
		
		Connection conn=null;
		Statement stmt = null;
        ResultSet rs = null;
		try {
			Class.forName(SqlConnections.MySQLDriver).newInstance();
			conn = DriverManager.getConnection(SqlConnections.RemoteMySQLHost,
					SqlConnections.Username, SqlConnections.Password);
			stmt = conn.createStatement();
			for (int i = 0; i < categories.length; i++) {
				String content="";
				rs = stmt.executeQuery("select content from dyn_news where category='"+ categories[i]+ "' order by getdate desc limit 100");
				while (rs.next()) {
					content +=rs.getString("content")+" ";
				}
				dataMap.put(categories[i], content);
			}
		} catch (Exception ex) {
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (Exception se) {
                }
            }
            if (stmt != null) {

                try {
                    stmt.close();
                } catch (Exception se) {
                }
            }
            if (conn != null) {

                try {
                    conn.close();
                } catch (Exception se) {
                }
            }
        }
		return dataMap;
	}
	protected ArrayList<String> getOneColumnData(String selQuery, String colName)
	{		
		ArrayList<String> dataList=new ArrayList<String>();
		Connection conn=null;
		Statement stmt = null;
                ResultSet rs = null;
		try {
			Class.forName(SqlConnections.MySQLDriver).newInstance();
			conn = DriverManager.getConnection(SqlConnections.RemoteMySQLHost,SqlConnections.Username, SqlConnections.Password);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(selQuery);
			while (rs.next()) 
			{
			    dataList.add(rs.getString(colName));
			}
				
		} 
		catch (Exception ex) {
			ex.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (Exception se) {
                }
            }
            if (stmt != null) {

                try {
                    stmt.close();
                } catch (Exception se) {
                }
            }
            if (conn != null) {

                try {
                    conn.close();
                } catch (Exception se) {
                }
            }
        }
		return dataList;
	}
}
