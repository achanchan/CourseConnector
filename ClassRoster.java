
/**
 * ClassRoster creates a hastable that holds a class name as a string and a 
 * vector of students in the class.
 *
 * Camila Lee (clee48) worked primarily on the implementation of this class 
 * 
 * @author achan, clee48, alee31
 * @version 05/17/2018
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
     * @param fileName is the name of the file that you are reading to create
     * the roster of students 
     * @return Hashtable 
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
    /**
     * the main method is used to test the ClassRoster class 
     */
    public static void main(String[] args){
        ClassRoster cs= new ClassRoster();
        System.out.println(cs.createRoster("connections"));
    }
}
