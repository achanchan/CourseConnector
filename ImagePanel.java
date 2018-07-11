
/**
 * ImagePanel extends JPanel and creates a panel that contains a JLabel of an image
 *
 * @author achan,alee31, clee48
 * @version 5/17/2018
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
