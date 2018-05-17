
/**
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import javax.swing.*;
import java.util.Hashtable;
import java.util.Vector; 
public class CourseConnectorGUI  
{  
    public static void main(String[] args)
    {
        ClassRoster csRoster = new ClassRoster(); 
        Hashtable csHash = csRoster.createRoster("connections"); 
        CourseConnectorGraph<Student> csStudentConnector = new CourseConnectorGraph<Student>();  
        csStudentConnector = csStudentConnector.graphFromHash(csHash); 
        Vector<String> connection = new Vector<String>(); 
        String student1 = "";
        String student2 = "";
        JFrame frame = new JFrame("Layout Manager Demo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JTabbedPane tp = new JTabbedPane();
        
        
        tp.addTab("Welcome", new IntroPanel());
        tp.addTab("Find a connection!", new SearchPanel(csStudentConnector,connection,student1, student2));
        tp.addTab("Results", new ResultsPanel(connection, student1, student2));
        frame.getContentPane().add(tp);
        
        frame.pack();
        frame.setVisible(true);
    }
}
