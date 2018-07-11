/**
 * StudentCourseLabel 
 * Camila Lee (clee48) worked primarily on the implementation of this class 
 * @Camila Lee,Alicia Lee,Amy Chan
 * @5/6/18
 */
public class StudentCourseLabel
{
    private String course;
    private int successorIndex;
    /**
     * Constructor for objects of class StudentCourseLabel
     * @param String courseName is the name of the course, int succ is the 
     * index of the sucessor 
     */
    public StudentCourseLabel(String courseName,int succ){
        course=courseName;
        successorIndex = succ;
    }
    /**
     * getCourse returns the name of the course
     * @return String 
     */
    public String getCourse(){
        return course;
    }
    /**
     * getSucc() returns the index of the successor
     * @return int 
     */
    public int getSucc(){
        return successorIndex;
    }
    
    public String toString(){
        return "--> " + course + " --> " + successorIndex;
    }
}