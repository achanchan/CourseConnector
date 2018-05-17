/**
 * @author achan 
 * @version 05/14/2018
 */
import java.awt.*;
import javax.swing.*;
import javax.swing.event.*; 
import java.util.Vector; 

public class SearchPanel extends JPanel
{   
    private String s1, s2; 
    private JLabel l1, l2, enter;
    private TextField t1,t2; 
    private JButton quitButton, connectButton; 
    private JPanel p1, p2, quitConnectPanel; 
    private CourseConnectorGraph<Student> csStudentGraph;
    private Vector<String> result; 
    /**
     * Constructor for objects of class SearchPanel
     */
    public SearchPanel(CourseConnectorGraph<Student> graph, Vector<String> result, String student1, String student2)
    {
       setLayout(new GridLayout (4,0));
       
       csStudentGraph = graph; 
       this.result = result; 
       s1 = student1; 
       s2 = student2;
       // create labels that prompt the username 
       l1 = new JLabel("Please Enter The Username of Student 1:");
       l2 = new JLabel("Please Enter The Username of Student 2:");
       enter = new JLabel ("Please press enter after you enter each username");
       // create textfields that takes in user input 
       t1= new TextField(20);
       t2 = new TextField(20);
       
       // create buttons 
       quitButton = new JButton("quit");
       connectButton = new JButton("connect!"); 
       
       // create JPanel for first label and first textfield 
       p1 = new JPanel(); 
       p1.add(l1); 
       p1.add(t1); 
       
       // create JPanel for second label and second textfield 
       p2 = new JPanel(); 
       p2.add(l2); 
       p2.add(t2); 
       
       // create JPanel for buttons
       quitConnectPanel = new JPanel();
       quitConnectPanel.add(quitButton); 
       quitConnectPanel.add(connectButton);
       
       
       // create listeners 
       ConnectListener connect = new ConnectListener(); 
       QuitListener quit = new QuitListener(); 
       
       // adding listeners
       connectButton.addActionListener(connect); 
       quitButton.addActionListener(quit); 
       
       // adding panels to the current panel 
       this.add(p1);
       this.add(p2);
       this.add(enter); 
       this.add(quitConnectPanel); 
    }
    private class ConnectListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            s1 = t1.getText().toString(); 
            s2 = t2.getText().toString(); 
            result = csStudentGraph.dfsTraversal(student1, student2);
    }
    }   
      private class QuitListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
        System.exit(0);
    }
    }
}
    


