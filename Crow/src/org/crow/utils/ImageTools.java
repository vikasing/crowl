/**
 * 
 */
package org.crow.utils;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import net.sf.jmimemagic.Magic;

import org.imgscalr.Scalr;

import com.sun.corba.se.spi.orbutil.fsm.Input;

import sun.misc.BASE64Encoder;
import sun.misc.OSEnvironment;
/**
 * @author viksin
 *
 */
public class ImageTools implements ImageObserver {
	public static String image;
/*	private InputStream imageStream;
	public ImageTools(InputStream imageStream) {
		this.imageStream = imageStream;
	}*/

/*	public static void main(String[] args) {
		ImageTools imageTools = new ImageTools();
		imageTools.convertImage2Base64("");
	}*/
	
	public String determineImageType(String imageUrl) {
		String type = null;
		if (imageUrl.contains(".jpg")) {
			type= "jpg";
		}
		else if (imageUrl.contains(".gif")) {
			type="gif";
		}
		else if(imageUrl.contains(".png")){
			type = "png";
		}
		return type;
	}
	/**
	 * @param 
	 * data:image/jpeg;base64,
	 */
	public String getDimAndGenerate64BaseCode(int heightL, int widthL, String type,InputStream imageStream) {
		String imageString = null;
		try {
			BufferedImage _image = ImageIO.read(imageStream);
			if (_image != null && type!=null) {
				ByteArrayOutputStream os = null;
				try {
					int h = _image.getHeight();
					int w = _image.getWidth();
					if (h>=heightL && w>=widthL) {
						os = new ByteArrayOutputStream();
				        BufferedImage bImage = Scalr.resize(_image, Scalr.Method.QUALITY, Scalr.Mode.FIT_EXACT,150, 120, Scalr.OP_ANTIALIAS);
						ImageIO.write(bImage, type, os);
						byte[] data= os.toByteArray();
						imageString = new BASE64Encoder().encode(data);
					}
				}
				catch (Exception ex2) {
					ex2.printStackTrace();
				}
				finally {
					if (os!=null) {
						os.close();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (imageString!=null) {
			imageString = "data:image/"+type+";base64,"+imageString;
		}
		return imageString; 
	}
	public Dimension getImageDimensions(InputStream imageStream) {
		ImageInputStream in =null;
		Dimension dimension = null;
		try {
			in = ImageIO.createImageInputStream(imageStream);
	        final Iterator<ImageReader> readers = ImageIO.getImageReaders(in);
	        if (readers.hasNext()) {
                ImageReader reader = readers.next();
                try {
                    reader.setInput(in);
                    dimension = new Dimension(reader.getWidth(0), reader.getHeight(0));
                } finally {
                	reader.dispose();
                }
	        }
		} catch (IOException e) {
			e.printStackTrace();
		} 
		finally {
	        if (in != null)
				try {
					in.close();
				} catch (Exception e) {
					e.printStackTrace();
			}
		}
		return dimension;
	}
    public void rescaleImage(String srcFile,String destFile,int destWidth, int destHeight) throws IOException
    {
        BufferedImage srcImage = ImageIO.read(new File(srcFile));
        BufferedImage destImage = new BufferedImage(destWidth, destHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = destImage.createGraphics();
        AffineTransform aft = AffineTransform.getScaleInstance((double)destWidth/srcImage.getWidth(),(double)destHeight/srcImage.getHeight());
        g2d.drawImage(srcImage, aft,this);
        ImageIO.write(destImage, "JPG",new File(destFile));
    }
	/* (non-Javadoc)
	 * @see java.awt.image.ImageObserver#imageUpdate(java.awt.Image, int, int, int, int, int)
	 */
	@Override
	public boolean imageUpdate(Image img, int infoflags, int x, int y,
			int width, int height) {
		// TODO Auto-generated method stub
		return false;
	}
}
