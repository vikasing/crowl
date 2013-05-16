package org.crow.utils;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class MemoryObjects {
	private static Map<String,Object> memObjects = new ConcurrentHashMap<String, Object>();
    /**
     * @return the memObjects
     */
    public static Map<String,Object> getMemObjects()
    {
        return memObjects;
    } 
    public static void addObjectToMemMap(String key, Object value)
    {
    	memObjects.put(key, value);
    }
    
    @SuppressWarnings("unchecked")
	public static void appendToMemMap(String key, Object value) {
    	Object oldObject = value;
    	if (memObjects.containsKey(key)) {
			oldObject = memObjects.get(key);
			if (oldObject instanceof List<?>) {
				List kList = (List) value;
				((List<?>) oldObject).addAll(kList);
			}
			else if (oldObject instanceof Set<?>) {
				Set kSet = (Set) value;
				((Set<?>) oldObject).addAll(kSet);
			}
			else if (oldObject instanceof Map<?, ?>) {
				Map kMap = (Map) value;
				((Map<?,?>) oldObject).putAll(kMap);
			}
		}
		memObjects.put(key, oldObject);
    }
}
