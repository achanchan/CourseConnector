
/**
 * The Student class represents a student with a username and name.
 *
 * @Camila Lee,Alicia Lee,Amy Chan
 * @5/6/18
 */
import java.util.*;
public class Student
{
    private String username,name;
    
    /**
     * Constructor for objects of class Student
     * 
     * @param A String studentUsername that represents the username of the student and String studentName which represents the 
     * name of the student.
     */
    public Student(String studentUsername,String studentName){ 
        username=studentUsername;
        name=studentName;
    }

    /**
     * The method getName is a "getter" method that returns a String that represents the name of the student.
     *
     * @return    A String that represents the name of the student.
     */
    public String getName(){
        return name;
    }
    
    /**
     * The method getUserName is a "getter" method that returns a String that represents the username of the student.
     *
     * @return    A String that represents the username of the student.
     */
    public String getUsername(){
        return username;
    }
    
    /**
     * The method toString returns a String that represents the username of the student.
     *
     * @return    A String that represents the username of the student.
     */
    public String toString(){
        return username;
    }
    
    /**
     * The main method to test the Student class.
     */
    public static void main(String [] args){
        Student one= new Student("clee48","Camila");
        System.out.println("Expected:/t clee48 \t Actual:\t "+one);
        System.out.println("Expected:/t Camila \t Actual:\t "+one.getName());
        System.out.println("Expected:/t clee48 \t Actual:\t "+one.getUsername());
        
        Student two= new Student("alee31","Alicia");
        System.out.println("Expected:/t alee31 \t Actual:\t "+two);
        System.out.println("Expected:/t Alicia \t Actual:\t "+two.getName());
        System.out.println("Expected:/t alee31 \t Actual:\t "+two.getUsername());
    }
}