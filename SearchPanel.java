
import java.awt.*;
import java.awt.*;
import javax.swing.*;
public class SearchPanel extends JPanel
{
    /**
     * Constructor for objects of class FakeSearchPanel
     */
    public SearchPanel()
    {
       setLayout(new GridLayout (3,0));
       
       JLabel l1 = new JLabel("Please Enter The Username of Student 1:");
       JLabel l2 = new JLabel("Please Enter The Username of Student 2:");
       JButton b1 = new JButton("quit");
       JPanel p3 = new JPanel(); 
       JPanel p4 = new JPanel(); 
       TextField t1= new TextField(20);
       TextField t2 = new TextField(20); 
       p3.add(l1); 
       p3.add(t1); 
       p4.add(l2); 
       p4.add(t2); 
       JPanel p1 = new JPanel(); 
      
       JButton b2 = new JButton("connect!"); 
       p1.add(b1); 
       p1.add(b2); 
       
       this.add(p3);
       this.add(p4);
       this.add(p1); 
    
       
    }
}
