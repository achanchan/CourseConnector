
/**
 * Write a description of class ResultsPanel here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.awt.*;
import javax.swing.*;
import java.util.Vector; 
public class ResultsPanel extends JPanel
{
    private Vector<String> result; 
    /**
     * Constructor for objects of class ResultsPanel
     */
    public ResultsPanel(Vector<String> result, String student1, String student2)
    {
       this.result = result; 
      
       if (result.isEmpty()){
           this.add(new NoConnectionPanel(student1, student2)); 
        }
       else
       {
           this.add(new ConnectionFoundPanel(result, student1, student2)); 
       }
    }

}
