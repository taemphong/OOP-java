package beverageproducts;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;


public class ProjectUtil {
    public static BufferedImage getBufferedImageFromIcon(ImageIcon icon) {
        if(icon==null)return null;
        
        BufferedImage picture = null;
        try {
            picture = new BufferedImage(
                    icon.getIconWidth(),
                    icon.getIconHeight(),
                    BufferedImage.TYPE_INT_RGB);
            Graphics g = picture.createGraphics();
            icon.paintIcon(null, g, 0, 0);
            g.dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error " + e.getMessage());
        }

        return picture;

    }
    
    public static ImageIcon getIconFromBufferedImage(BufferedImage img,int width, int height) {
        if(img==null)return null;
        
        ImageIcon icon = null;
        try {
        Image dimg = img.getScaledInstance(width, height,Image.SCALE_SMOOTH);
        icon = new ImageIcon(dimg);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error " + e.getMessage());
        }
        return icon;
    }

    public static byte[] getByteArrayFromBufferedImage(BufferedImage picture) {
        if (picture == null)return null;
        
        byte[] bytes = null;
        try {
            
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ImageIO.write(picture, "jpg", baos);
                bytes = baos.toByteArray();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error " + e.getMessage());
        }
        return bytes;
    }
    
    public static boolean verifyDouble(String s){
        try{
            Double.parseDouble(s);
            return true;
        }catch(Exception e){
            return false;
        }
    }
    
    public static boolean verifyInteger(String s){
        try{
            Integer.parseInt(s);
            return true;
        }catch(Exception e){
            return false;
        }
    }
    
}

