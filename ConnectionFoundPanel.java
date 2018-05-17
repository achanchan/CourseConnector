
/**
 * Write a description of class ConnectionFoundPanel here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.awt.*;
import javax.swing.*;
import java.util.Vector; 
public class ConnectionFoundPanel extends JPanel
{
    /**
     * Constructor for objects of class ConnectionFoundPanel
     */
    public ConnectionFoundPanel(Vector<String> result,String student1, String student2)
    {
       setLayout(new GridLayout (4,0));
       JLabel found = new JLabel("We found a connection!");
       found.setHorizontalAlignment(JLabel.CENTER);
       found.setFont(new Font("Serif", Font.BOLD, 20));
       
       
       JPanel p1 = new JPanel(); 
       JPanel p2 = new JPanel(); 
       JLabel again = new JLabel("Would you like to find another connection?");
       again.setFont(new Font("Serif", Font.BOLD, 20));
       again.setHorizontalAlignment(JLabel.CENTER);
       
       JButton b1 = new JButton("quit"); 
       JButton b2 = new JButton("Find another connection!"); 
       p1.add(b1); 
       p1.add(b2); 
       
       
       this.add(found);
       this.add(p2); 
       this.add(again); 
       this.add(p1);
    }
}
