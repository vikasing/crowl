/**
 * 
 */
package org.crow.ml;

import java.io.Serializable;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author viksin
 *
 */
public class Clusters implements Serializable{
	/**
     * 
     */
    private static final long serialVersionUID = 125241081769068516L;
    private ConcurrentHashMap <String,Cluster> clusterMap = new ConcurrentHashMap<String,Cluster>();	
	
	public void addCluster(Cluster cluster)
	{
		int mapSize = clusterMap.size();
		clusterMap.put("C"+mapSize+1, cluster);
	}
	
	public ConcurrentHashMap <String,Cluster> getClusterMap()
	{		
		return clusterMap;
	}
	public int size()
	{
		return clusterMap.size();
	}
}
