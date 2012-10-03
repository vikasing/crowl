/**
 * 
 */
package org.crow.dm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author viksin
 *
 */
public class DMUtils {

	public Map<String,Integer> findCapWords(String content){		
		Map<String,Integer> capWordsMap = new HashMap<String,Integer>() ;
		findCapitalWords(content, capWordsMap);
		return capWordsMap;
	}
	
	public Map<String,Integer> findCapWords(String[] sentences){		
		
		Map<String,Integer> capWordsMap = new HashMap<String,Integer>() ;
		RemoveStopWords rsw = new RemoveStopWords();
		for(String content:sentences)
		{
		        //System.out.println("sent1 "+content);
		        content = rsw.cleanSpecialChars(content);
		        //System.out.println("sent2 "+content);
			findCapitalWords(content, capWordsMap);
			//System.out.println("sent "+content);
		}
		return capWordsMap;
	}
	/**
	 * @param content
	 * @param capWordsMap
	 */
	private void findCapitalWords(String content, Map<String, Integer> capWordsMap) {			
		StringBuffer capWord=new StringBuffer();
		int pointer=0;
		ArrayList<String> wlist = new ArrayList<String>();
		String[] wordsArray = content.split("[\\s]+");
		for(int i=0; i<wordsArray.length;i++)
		{
			if(wordsArray[i].length()>1 && Character.isUpperCase(wordsArray[i].charAt(0)))
			{
				if(i-pointer!=1)
				{
					capWord= new StringBuffer();
				}
				try
				{
					capWord.append(wordsArray[i]+" ");
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				pointer=i;				
			}
			String cWord=capWord.toString().replace(".", "");
			if(!cWord.isEmpty() &&( i-pointer==1 || wordsArray.length-1-pointer==0))
			{
				/*
				 * Code for comparing nouns in a single string
				 * if(wlist.size()<1)
				{
					wlist.add(cWord);
				}
				else
				{
					for(String s:wlist)
					{
						//if (s.length()>=cWord.length())
						//{
							if(cWord.indexOf(s)>-1)
							{
								
							}
							
						//}
						//else
						//{
							else if(s.indexOf(cWord)>-1)
							{
								
							}
							else{ wlist.add(cWord); }
						//}
					}
				}*/				
					if(!capWordsMap.containsKey(cWord)){
						capWordsMap.put(cWord, new Integer(1));
					}
					else{
						capWordsMap.put(cWord, capWordsMap.get(cWord)+1);
					}				 
				//System.out.println(capWord);
			}			
		}
	}
}
