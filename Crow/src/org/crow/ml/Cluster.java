/**
 * 
 */
package org.crow.ml;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author viksin
 *
 */
public class Cluster implements Serializable{

	//private Map<String,Double> entities;
	//private Matrix featureMatrix;
	//private Map<String,Double> featureMap;

	/**
	 * @param featureMap the featureMap to set
	 
	public void setFeatureMap(Map<String,Double> featureMap) {
		this.featureMap = featureMap;
	}*/
	/**
	 * @return the featureMap
	 
	public Map<String,Double> getFeatureMap() {
		return featureMap;
	}*/
	/**
	 * @param entities the entities to set
	 
	public void setEntities(Map<String,Double> entities) {
		this.entities = entities;
	}*/
	/**
	 * @return the entities
	 
	public Map<String,Double> getEntities() {
		return entities;
	}*/
	/*****************************
	 * Above methods are for
	 * future, when factor
	 * feature importance (double)
	 * will be considered
	 *****************************/
	
	private ArrayList<String> feedHashIds;
	
	private ArrayList<String> featuresList;
	
	/**
	 * @param featuresList the featuresList to set
	*/ 
	public void setFeaturesList(ArrayList<String> featuresList) {
		this.featuresList = featuresList;
	}
	
	/**
	 * @return the featuresList
	 */
	public ArrayList<String> getFeaturesList() {
		return featuresList;
	}
	

	public void addFeatureToCluster(String feature)
	{
		if(!getFeaturesList().contains(feature))
		getFeaturesList().add(feature);
	}
	public boolean hasFeature(String feature)
	{
		if(getFeaturesList().contains(feature))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	public void createFeatureList()
	{		
		setFeaturesList(new ArrayList<String>());
	}
	public void createHashIdList()
        {
	    if(getFeedHashIds()==null)
            {
                setFeedHashIds(new ArrayList<String>());
            }
                
        }
	public void addHashIdToCluster(String hashId)
        {
	    createHashIdList();
            if(!getFeedHashIds().contains(hashId))
            getFeedHashIds().add(hashId);
        }

    /**
     * @param feedHashIds the feedHashIds to set
     */
    public void setFeedHashIds(ArrayList<String> feedHashIds)
    {
        this.feedHashIds = feedHashIds;
    }

    /**
     * @return the feedHashIds
     */
    public ArrayList<String> getFeedHashIds()
    {
        return feedHashIds;
    }
        
}
