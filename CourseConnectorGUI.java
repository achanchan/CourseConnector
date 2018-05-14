
/**
 * Write a description of class FakeGUI here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import javax.swing.*;
public class CourseConnectorGUI
{
    public static void main(String[] args)
    {
        JFrame frame = new JFrame("Layout Manager Demo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JTabbedPane tp = new JTabbedPane();
        
        tp.addTab("Welcome", new IntroPanel());
        tp.addTab("Find a connection!", new SearchPanel());
        tp.addTab("Results", new ResultsPanel());
        frame.getContentPane().add(tp);
        
        frame.pack();
        frame.setVisible(true);
    }
}
