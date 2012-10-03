/*
 *  This file is part of the Heritrix web crawler (crawler.archive.org).
 *
 *  Licensed to the Internet Archive (IA) by one or more individual 
 *  contributors. 
 *
 *  The IA licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *  
 */


package org.crow.httpOps;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**Further modified for Crow
 * viksin
 */
public class Robotstxt implements Serializable {
    static final long serialVersionUID = 7025386509301303890L;
    
    LinkedList<String> disallowedPaths = new LinkedList<String>();
    LinkedList<String> allowedPaths = new LinkedList<String>();
    boolean crawlingAllowedForCrow = false;   
    
	public Robotstxt(BufferedReader reader) throws IOException {
		String read;

		while (reader != null) {
			do {
				read = reader.readLine();
				// Skip comments & blanks
			} while ((read != null)
					&& ((read = read.trim()).startsWith("#") || read.length() == 0));
			if (read == null) {
				reader.close();
				reader = null;
			} else {
				// remove any html markup
				read = read.replaceAll("<[^>]+>", "");
				int commentIndex = read.indexOf("#");
				if (commentIndex > -1) {
					// Strip trailing comment
					read = read.substring(0, commentIndex);
				}
				read = read.trim();
				if (read.matches("(?i)^User-agent:.*")) {
					String ua = read.substring(11).trim().toLowerCase();
					if (ua.equals("*")) {
						crawlingAllowedForCrow = true;
					}
					continue;
				}
				if (crawlingAllowedForCrow) {
					if (read.matches("(?i)Disallow:.*")) {
						disallowedPaths.add(read.substring(9).trim()
								.toLowerCase());
						continue;
					}
					// crawl delay is not respected right now by Crow

					// if (read.matches("(?i)Crawl-delay:.*")) {
					// if (current == null) {
					// // buggy robots.txt
					// hasErrors = true;
					// continue;
					// }
					// // consider a crawl-delay, even though we don't
					// // yet understand it, as sufficient to end a
					// // grouping of User-Agent lines
					// hasDirectivesYet = true;
					// String val = read.substring(12).trim();
					// val = val.split("[^\\d\\.]+")[0];
					// try {
					// current.setCrawlDelay(Float.parseFloat(val));
					// } catch (NumberFormatException nfe) {
					// // ignore
					// }
					// continue;
					// }
					if (read.matches("(?i)Allow:.*")) {
						String path = read.substring(6).trim();
						allowedPaths.add(path);
						continue;
					}
				}
			}
		}
	}
    public boolean isCrawlingAllowed() {
        return crawlingAllowedForCrow;
    }
    public List<String> getAllowedPaths() {
        return allowedPaths;
    }
    public List<String> getDisallowedPaths() {
        return disallowedPaths;
    }
}
