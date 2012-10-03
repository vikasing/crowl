package org.crow.utils;
/**
 * @author viksin
 *
 */
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class FileOps {
	
	public void downloadFile(String fileUrl) {
		try {
			BufferedInputStream in = new BufferedInputStream(new URL(fileUrl).openStream());
	                GenUtils genUtils = new GenUtils();
	                FileOutputStream fos = new FileOutputStream(genUtils.getPropertyValue("imagethumbslocation")+"/img.jpg");
			BufferedOutputStream bout = new BufferedOutputStream(fos, 1024);
			byte[] data = new byte[1024];
			int x = 0;
			while ((x = in.read(data, 0, 1024)) >= 0) {
				bout.write(data, 0, x);
			}
			bout.close();
			in.close();
		} catch (MalformedURLException e) {
			System.err.println(e.toString());
		} catch (IOException e) {
			System.err.println(e.toString());
		}
	}
	public String readFile(String fileName,String dir){	
	    File file = new File(dir+fileName);
	    int ch;
	    StringBuffer strBuff = new StringBuffer("");
	    FileInputStream fin = null;
	     
		try {
			 fin = new FileInputStream(file);
			 while( (ch = fin.read()) != -1)
				 strBuff.append((char)ch);
			 fin.close();
			 System.out.println("Reading from cache"+strBuff.toString());
		} catch (Exception e) {
			System.out.println(e);
		}
		return strBuff.toString();
	}
	public void deleteFile(String fileName,String dir) {
		//String dir = Constants.TempImageDir;
	    File f = new File(dir+fileName);
	    if (!f.exists())
	      throw new IllegalArgumentException(
	          "Delete: no such file or directory: " + fileName);

	    if (!f.canWrite())
	      throw new IllegalArgumentException("Delete: write protected: "
	          + fileName);
	    if (f.isDirectory()) {
	      String[] files = f.list();
	      if (files.length > 0)
	        throw new IllegalArgumentException(
	            "Delete: directory not empty: " + fileName);
	    }
	    boolean success = f.delete();

	    if (!success)
	      throw new IllegalArgumentException("Delete: deletion failed");
	}


}
