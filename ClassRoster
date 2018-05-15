
/**
 * Write a description of class ClassRoster here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.util.*;
import java.io.*;
public class ClassRoster
{
    private Hashtable <String,Vector<Student>> courseRoster;
    
    /**
     * Constructor for objects of class ClassRoster
     */
    public ClassRoster()
    {
        courseRoster= new Hashtable <String,Vector<Student>>();
    }
    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public Hashtable createRoster(String fileName)
    {
        courseRoster= new Hashtable<String,Vector<Student>>();
         try {   
             Scanner scan = new Scanner(new File(fileName+".txt"));
             while (scan.hasNext()) {
                 String[] line= scan.nextLine().split(";");//["wwood12","Wendy","CS115,CS242,CS249"]
                 String[] courses=line[2].split(",");//["CS115","CS242","CS249"]
                 Student current= new Student(line[0],line[1]);
                 for (String course:courses){
                     if(courseRoster.containsKey(course)){
                        courseRoster.get(course).addElement(current);
                     }
                     else{
                         Vector<Student> studentNames= new Vector<Student>();
                         studentNames.add(current);
                         courseRoster.put(course,studentNames);
                     }
                 }
             }
             scan.close(); 
             }
        catch (IOException e) {
            System.out.println("Error: There is a problem with finding the file name! Check to see if it exists! ");
            }   
        return courseRoster;
    }
    public static void main(String[] args){
        ClassRoster cs= new ClassRoster();
        System.out.println(cs.createRoster("connections"));
    }
}
