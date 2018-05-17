
/**
 *
 * @author achan
 * @version 05/14/2018
 */

import java.awt.*;
import javax.swing.*;

public class IntroPanel extends JPanel
{
    /**
     * Constructor for objects of class IntroPanel
     */
    public IntroPanel()
    {
        
        setLayout(new GridLayout(3,0));
        
        JLabel l1 = new JLabel("Welcome to CourseConnector!");
        l1.setHorizontalAlignment(JLabel.CENTER);
        l1.setFont(new Font("Serif", Font.BOLD, 36));
        
        JLabel l2 = new JLabel("Alicia Lee, Amy Chan, Camila Lee"); 
        l2.setHorizontalAlignment(JLabel.CENTER);
        
        JLabel l3 = new JLabel("CourseConnector can connect you with other CS students!");
        l3.setHorizontalAlignment(JLabel.CENTER);
        
       
        ImagePanel img = new ImagePanel("PeopleWeb.jpg");
       
        JPanel p1 = new JPanel(); 
        p1.setLayout(new GridLayout (2,0));
        p1.add(l1);
        p1.add(l2);
       
       
        this.add(p1);
        this.add(img);
        this.add(l3);
    }

}
