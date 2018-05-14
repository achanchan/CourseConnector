
/**
 * Write a description of class ImagePanel here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.awt.*;
import javax.swing.*;
public class ImagePanel extends JPanel
{
    
    /**
     * Constructor for objects of class ImagePanel
     */
    public ImagePanel(String imageName)
    {
        JLabel imgLabel = new JLabel(new ImageIcon(imageName));
        imgLabel.setHorizontalAlignment(JLabel.CENTER);
        this.add(imgLabel);
    }

}
