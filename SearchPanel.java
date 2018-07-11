/**
 * SearchPanel allows the user to input two usernames that will be used to find if there is a connection between two students. 
 * @author achan, alee31, clee48
 * @version 05/14/2018
 */
import java.awt.*;
import javax.swing.*;
import java.awt.event.*; 
import java.util.Vector; 

public class SearchPanel extends JPanel
{   
    private String s1, s2; 
    private JLabel l1, l2, enter,connect; 
    private TextField t1,t2; 
    private JButton quitButton, connectButton; 
    private JPanel p1, p2, quitConnectPanel; 
    private CourseConnectorGraph<Student> csStudentGraph;
    private Vector<String> result; 
    private JPanel connectionResults;
   

    /**
     * Constructor for objects of class SearchPanel
     * @param CourseConnectorGraph<Student> graph is the graph of students 
     * Vector<String> result is to hold the vector that results from the dfs traversal
     * String student1 holds the username for student1
     * String student2 holds the username for student2
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
       
       connectionResults = new JPanel(); 
       connectionResults.setLayout(new GridLayout (1,1));
       connect = new JLabel(); 
       connect.setHorizontalAlignment(JLabel.CENTER);
       connectionResults.add(connect); 
      
       // create listeners 
       ConnectListener connect = new ConnectListener(); 
       QuitListener quit = new QuitListener(); 
       
       // adding listeners
       connectButton.addActionListener(connect); 
       quitButton.addActionListener(quit); 
       
       // adding panels to the current panel 
       this.add(p1);
       this.add(p2);
       this.add(connectionResults); 
       this.add(quitConnectPanel); 
    }
    /**
     * ConnectListener implements ActionListener, when an the connect button is pressed, We will search the student graph to see 
     * if there is a connection between the two students 
     * 
     */
    private class ConnectListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            s1 = t1.getText().toString(); 
            s2 = t2.getText().toString(); 
            result = csStudentGraph.dfsTraversal(s1, s2);
            if (result.isEmpty()){
           connect.setText("Sorry we could not find a connection between " + s1 + " and " + s2);
           
        }
            else{
                String connectionWeb = "";
                int numStudents = 0;
                for (int i = 0; i < result.size()-1; i++){
                    if (i <= 8){
                        connectionWeb += result.get(i) + "-->";
                    }
                    else {
                    numStudents++;
                }
                    if ((i > 8) && (i == result.size()-2)){
                        connectionWeb += numStudents + " students" + "-->";
                       
                }
            }
                connectionWeb += result.get(result.size()-1);
                connect.setText("We found a connection! " + connectionWeb);
              
            }
        }
          
    }   
    /**
     * QuitListener implements ActionListener, when the quit button is pressed, we exit the application 
     */
      private class QuitListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
        System.exit(0);
    }
    }
}


    


