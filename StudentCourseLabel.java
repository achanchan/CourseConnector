/**
 * Write a description of class Course here.
 *
 * @Camila Lee,Alicia Lee,Amy Chan
 * @5/6/18
 */
public class StudentCourseLabel
{
    private String course;
    private int successorIndex;
    /**
     * Constructor for objects of class Course
     */
    public StudentCourseLabel(String courseName,int succ){
        course=courseName;
        successorIndex = succ;
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public String getCourse(){
        return course;
    }
    
    
    public int getSucc(){
        return successorIndex;
    }
    public String toString(){
        return "--> " + course + " --> " + successorIndex;
    }
}