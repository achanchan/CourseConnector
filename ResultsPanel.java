
/**
 * Write a description of class ResultsPanel here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.awt.*;
import java.awt.*;
import javax.swing.*;
public class ResultsPanel extends JPanel
{
  
    /**
     * Constructor for objects of class ResultsPanel
     */
    public ResultsPanel()
    {
       setLayout(new GridLayout (4,0));
       JLabel l1 = new JLabel("Sorry, we were unable to connect Alicia and Camila.");
       l1.setHorizontalAlignment(JLabel.CENTER);
       l1.setFont(new Font("Serif", Font.BOLD, 20));
       JLabel img = new JLabel(new ImageIcon("soSorry.jpg")); 
       JPanel p1 = new JPanel(); 
       JPanel p2 = new JPanel(); 
       JLabel again = new JLabel("Would you like to find another connection?");
       again.setFont(new Font("Serif", Font.BOLD, 20));
       again.setHorizontalAlignment(JLabel.CENTER);
       p2.add(img); 
       JButton b1 = new JButton("quit"); 
       JButton b2 = new JButton("Find another connection!"); 
       p1.add(b1); 
       p1.add(b2); 
       
       
       this.add(l1);
       this.add(p2); 
       this.add(again); 
       this.add(p1); 
    }

}
