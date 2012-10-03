/**
 * 
 */
package org.crow.utils;

import org.crow.classes.Feed;

import com.google.gson.Gson;

/**
 * @author viksin
 *
 */
public class JSONProcessor {

	public Feed[] createJSONObject(String jsonText)
	{
		Gson gson = new Gson();
		Feed[] feeds = gson.fromJson(jsonText, Feed[].class);
		return feeds;
	}
}
