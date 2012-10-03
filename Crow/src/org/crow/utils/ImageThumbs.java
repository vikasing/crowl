package org.crow.utils;

/**
 * @author viksin
 *
 */
import java.awt.Container;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class ImageThumbs implements ImageObserver
{

    public void createThumbnail(String imgFilePath, String thumbPath,int thumbWidth, int thumbHeight)
    {
        try {
        Image image = Toolkit.getDefaultToolkit().getImage(imgFilePath);
        MediaTracker mediaTracker = new MediaTracker(new Container());
        mediaTracker.addImage(image, 0);
        mediaTracker.waitForID(0);        
        double thumbRatio = (double) thumbWidth / (double) thumbHeight;
        int imageWidth = image.getWidth(null);
        int imageHeight = image.getHeight(null);
        double imageRatio = (double) imageWidth / (double) imageHeight;
        if (thumbRatio < imageRatio) {
            thumbHeight = (int) (thumbWidth / imageRatio);
        }
        else {
            thumbWidth = (int) (thumbHeight * imageRatio);
        }
        Image scaleImg = image.getScaledInstance(thumbWidth, thumbHeight,Image.SCALE_SMOOTH);
        new javax.swing.ImageIcon(scaleImg);
        BufferedImage thumbImage = new BufferedImage(scaleImg.getWidth(null),scaleImg.getHeight(null), BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = thumbImage.createGraphics();
        graphics2D.setRenderingHint(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_SPEED);
        graphics2D.drawImage(scaleImg, 0, 0, null);
        graphics2D.dispose();
        
        BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(thumbPath));
        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
        JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(thumbImage);
        int quality = 100;
        param.setQuality(quality / 100.0f, false);
        encoder.setJPEGEncodeParam(param);
        encoder.encode(thumbImage);
        out.close();
        }
        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public void rescaleImage(String srcFile,String destFile,int destWidth, int destHeight) throws IOException
    {
        BufferedImage srcImage = ImageIO.read(new File(srcFile));
        BufferedImage destImage = new BufferedImage(destWidth, destHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = destImage.createGraphics();
        AffineTransform aft = AffineTransform.getScaleInstance((double)destWidth/srcImage.getWidth(),(double)destHeight/srcImage.getHeight());
        ImageIO.write(destImage, "JPG",new File(destFile));
    }

    /* (non-Javadoc)
     * @see java.awt.image.ImageObserver#imageUpdate(java.awt.Image, int, int, int, int, int)
     */
    @Override
    public boolean imageUpdate(Image img, int infoflags, int x, int y,
            int width, int height)
    {
        // TODO Auto-generated method stub
        return false;
    }
}
