
/**
 * Write a description of class NoConnectionPanel here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.awt.*;
import javax.swing.*;
public class NoConnectionPanel extends JPanel 
{   private JLabel unable, again;
    private JButton quit, restart; 
    private ImagePanel img;
    private JPanel quitRestartPanel;
    /**
     * Constructor for objects of class NoConnectionPanel
     */
    public NoConnectionPanel(String student1, String student2) 
    {
       setLayout(new GridLayout (4,0));
       JLabel l1 = new JLabel("Sorry, we were unable to connect " + student1 + " and " + student2);
       l1.setHorizontalAlignment(JLabel.CENTER);
       l1.setFont(new Font("Serif", Font.BOLD, 20));
       
       img = new ImagePanel("soSorry.jpg"); 
       
       
       again = new JLabel("Would you like to find another connection?");
       again.setFont(new Font("Serif", Font.BOLD, 20));
       again.setHorizontalAlignment(JLabel.CENTER);
       
       // create buttons 
       quit = new JButton("quit"); 
       restart = new JButton("Find another connection!"); 
       quitRestartPanel = new JPanel();
       quitRestartPanel.add(quit); 
       quitRestartPanel.add(restart); 
       
       
       this.add(l1);
       this.add(img); 
       this.add(again); 
       this.add(quitRestartPanel);
    }
}
