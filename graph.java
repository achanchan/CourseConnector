/**
 * class CourseConnectorGraphProject creates graph objects that represent a group of students connected by the courses they share.
 * A CourseConnecterGraph can take data from a Hashtable class roster to make a graph. It also can take two student usernames and
 * show the connected courses and students between them.
 * 
 *
 * @author alee31, clee48, achan
 * @version 5/17/18
 */
import java.util.*;
import java.io.*;
public class CourseConnectorGraph<T> //implements Graph<T>
{
    // instance variables - 
    private Vector<T> vertices;
    private Vector<LinkedList<StudentCourseLabel>> arcs;

    /**
     * Default Constructor for objects of class CourseConnectorGraphProject. Takes no params.
     */
    public CourseConnectorGraph(){
        vertices = new Vector<T>();
        arcs = new Vector<LinkedList<StudentCourseLabel>>();

    }

    /**
     * Constructor for objects of class CourseConnectorGraphProject. Duplicates an input CourseConnectorGraph g to duplicate.
     * @param CourseConnectorGraph g, the graph to duplicate
     */
    public CourseConnectorGraph(CourseConnectorGraph g) {
        this.vertices = (Vector<T>)g.vertices.clone();
        this.arcs = new Vector<LinkedList<StudentCourseLabel>>();
        for(int i=0; i<g.vertices.size(); i++){
            this.arcs.addElement((LinkedList<StudentCourseLabel>)((LinkedList<T>)g.arcs.get(i)).clone());
        } 
    }

    /**
     * Constructs a CourseConnectorGraph from an inputted Hashtable with Strings as keys and Vectors of Students as values. 
     * 
     * @param Hashtable hashtable with Strings as keys and Vectors of Students as values.
     * @return CourseConnectorGraph g populated by the hashtable
     */
    public static CourseConnectorGraph graphFromHash(Hashtable<String,Vector<Student>> hashtable){
        //creates an empty graph
        CourseConnectorGraph g = new CourseConnectorGraph();
        //an enumeration of all the courses in the hash table
        Enumeration<String> allCourses = hashtable.keys();

        //populate graph g by iterating through the hash table
        for(int i = 0; i<hashtable.size(); i++){
            //gets a course to find corresponding students in the course
            String course = allCourses.nextElement();
            Vector studentsInCourse = hashtable.get(course);
            for(int j = 0; j<studentsInCourse.size(); j++){
                //adds students to the graph as vertices one at a time
                g.addVertex(studentsInCourse.get(j));
            }
            //creates connections between all the students in a certain course
            for(int k = 0; k< studentsInCourse.size() -1; k++){
                for(int l= k; l<studentsInCourse.size()-1;l++){
                    g.addEdge(studentsInCourse.get(l), studentsInCourse.get(l+1), course);
                }
            }
        }
        return g;
    }

    /**
     * Adds a T object as a vertex to the graph.
     * 
     * @param T vertex, the object to add as a vertex to the graph
     */
    public void addVertex (T vertex){
        //if vertex already in the graph, do not add it again
        if (vertices.contains(vertex)){return;}

        //add vertex
        vertices.addElement(vertex);
        //add its corresponding empty list of connections
        arcs.addElement(new LinkedList<StudentCourseLabel>());
        //System.out.println("size of vertices: " + vertices.size());
    }

    /******************************************************************
     * Inserts an edge between two vertices of the graph.
     * If one or both vertices do not exist, ignores the addition.
     * 
     * @param T vertex1, T vertex 2 the vertices to connect
     * @param String course, the course label Students are connected by.
     ******************************************************************/
    public void addEdge (T vertex1, T vertex2, String course) {
        // getIndex will return NOT_FOUND if a vertex does not exist,
        // and the addArc() will not insert it
        this.addArc (vertex1, vertex2, course);
        addArc (vertex2, vertex1, course);
    }

    /**
     * Connects two students by their course by creating a StudentCourseLabel label that is stored as the graph arc. 
     */
    private void addArc (T student1, T student2, String course){//n1 and n2 already subtract 1
        //if n1 or n2 are invalid
        if(!vertices.contains(student1)||!vertices.contains(student2)){
            return;
        }
        int n1 = vertices.indexOf(student1);
        int n2 = vertices.indexOf(student2);

        StudentCourseLabel label = new StudentCourseLabel(course, n2);

        LinkedList<StudentCourseLabel> l = arcs.get(n1);
        //if n1 to n2 arc already exists, return
        if(l.contains(label)){return;}
        l.add(label);
    }

    /**
     * Saves graph to a TGF file
     * 
     * @param String f to name the output file
     */
    public void saveToTGF(String f){
        //System.out.println("size of vertices: " + vertices.size());
        try{
            PrintWriter printer = new PrintWriter(new File(f));
            for (int i = 0; i<vertices.size(); i++){
                printer.println((i+1) + " " + vertices.get(i));
            }
            printer.println("#");

            //go down the arcs vector
            for (int i = 0; i<arcs.size(); i++){
                LinkedList<StudentCourseLabel> ll = arcs.get(i);
                for (int j = 0; j<ll.size(); j++){
                    StudentCourseLabel label = ll.get(j);

                    int to = label.getSucc();
                    printer.println((i+1) + " " + (to+1));
                }
            }
            printer.close();
        }catch(IOException e){
            System.out.println("Cannot write in the file " + f);
        }
    }

    /**
     * Checks if T vertex is a vertex in the graph.
     * 
     * @param T vertex to check
     * @return boolean representing whether the graph contains vertex or not. 
     */
    public boolean containsVertex(T vertex){
        return vertices.contains(vertex);
    }

    /**
     * Private helper method takes a string username and returns its corresponding Student object. Returns a null object if the
     * Student is not in the graph.
     * 
     * @param String search, the username to be searched through the vertices
     * @return T corresponding T object to the search username
     */
    private T usernameToStudent(String search){
        boolean found = false;
        int i = 0;
        while(i<vertices.size()&&!found){
            Student student = (Student) vertices.get(i);
            String username = student.getUsername();
            if(search.equals(username)){
                found = true;
            }
            else{
                i++;
            } 
        }
        if(i==vertices.size()){
            return null;
        }
        return vertices.get(i);
    }
    /**
     * Starts at given username, converts username to its corresponding Student, and traverses through the graph to find the 
     * end username's corresponding Student. 
     * 
     * Uses a Vector<String> to collects the Student name, the course that connects the Student to the next Student, and 
     * the next Student.
     * For example, if alee31 is connected to clee48 through CS111,the Vector would be ["alee31", "cs111", "clee48"].
     * 
     * @param String startUsername, the username to start at
     * @param String endUsername, the username to end at
     * 
     * @return Vector<String> finalTraversal, holds the students and the course that connects them together.
     */
    public Vector<String> dfsTraversal(String startUsername, String endUsername) {
        //converts the usernames to the Student objects
        T start = this.usernameToStudent(startUsername);
        T end = this.usernameToStudent(endUsername);
        
        //accumulates the traversed elements and the corresponding course labels that connected them.
        LinkedList<T> search = new LinkedList<T>();
        LinkedList<String> labels = new LinkedList<String>();
        
        //holds the final traversal with both students and course labels.
        Vector<String> finalTraversal = new Vector<String>();
        
        //clones the graph so the original graph will not be destroyed.
        CourseConnectorGraph<T> graph = new CourseConnectorGraph<T>(this);
        
        //if the start or end Student is not in the graph, returns an empty Vector.
        if(!graph.containsVertex(start)||!graph.containsVertex(end)){
            return finalTraversal;
        }
        
        //for the stack that pushes/pops elements to traverse.
        T currentVertex;
        LinkedList<T> traversalStack = new LinkedList<T>();
        
        //adds first element to traversal
        traversalStack.push(start);
        search.add(start);
        
        //stop searching when all elements are exhausted or end Student is found. 
        while (!traversalStack.isEmpty()&&!search.contains(end))
        {
            currentVertex = traversalStack.peek();
            int index = graph.vertices.indexOf(currentVertex);
            //Try finding an element to iterate to. If there is not, no connection exists and no connection message is printed.
            try{
                LinkedList<StudentCourseLabel> connections = graph.arcs.get(index);
                if(connections.isEmpty()){
                    traversalStack.pop();
                }
                else{
                    //gets the first element of the arcs LinkedList of StudentCourseLabels 
                    StudentCourseLabel nextNode = connections.get(0);
                    //gets the String course label from the StudentCourseLabel nextNode
                    String label = nextNode.getCourse();
                    //gets the next connecting T student object 
                    T student = vertices.get(nextNode.getSucc());
                    //adds the next connecting T student to the TraversalStack
                    traversalStack.push(student);
                    if(!search.contains(student)){
                        //adds the student and course label to the search LinkedList of Students and LinkedList of labels
                        search.add(student);
                        labels.add(label);
                    }
                    connections.pop();
                }
            }
            catch (ArrayIndexOutOfBoundsException ex){
                System.out.println("There is no connection.");
                break;
            }
        }
        //puts student and course label as strings into finalTraversal Vector<String>
        for(int i = 0; i<labels.size(); i++){
            finalTraversal.add(search.get(i).toString());
            finalTraversal.add(labels.get(i));
        }
        //adds final student to finalTraversal
        finalTraversal.add((search.get(search.size()-1)).toString());

        return finalTraversal;
    }
    
    /******************************************************************
    *    Returns a string representation of the graph. 
    *    @return String representation of graph, showing vertices and edges
     ******************************************************************/
    public String toString() {
        if (vertices.size() == 0) return "Graph is empty";

        String result = "Vertices: \n";
        result = result + vertices;

        result = result + "\n\nEdges: \n";
        for (int i=0; i< vertices.size(); i++){
            result = result + "from " + vertices.get(i) + ": [";
            LinkedList<StudentCourseLabel> labelsLinkedList = arcs.get(i);
            for(int j = 0; j < labelsLinkedList.size(); j++){
                StudentCourseLabel label= labelsLinkedList.get(j);
                String course = label.getCourse();
                int nextStudentIndex = label.getSucc(); 
                T stud = vertices.get(nextStudentIndex);
                result+= " course: " + course + " next student: " + stud;
            }

            result+="]\n";
        }

        return result;
    }
     /**
     * main for testing purposes.
     */
    public static void main(String[] args){
        System.out.println("TESTING FOR SMALL HASH TABLE WITH THREE STUDENTS");
        Student s1 = new Student("alee31", "alicia");
        Student s2 = new Student("clee48", "camila");
        Student s3 = new Student("achan", "amy");

        Vector<Student> cs111 = new Vector<Student>();
        cs111.add(s1);
        cs111.add(s2);

        Vector<Student> cs230 = new Vector<Student>();
        cs230.add(s2);
        cs230.add(s3);
        
        Hashtable testTable = new Hashtable<String,Vector<Student>>();
        testTable.put("CS111", cs111);
        testTable.put("CS230", cs230);
        CourseConnectorGraph<Student> testGraph = graphFromHash(testTable);
        System.out.println(testGraph);
        
        System.out.println("testing convertor from username to object: " + testGraph.usernameToStudent("clee48"));
        System.out.println("TESTING TRAVERSAL: " + testGraph.dfsTraversal("alee31", "achan"));

        
        System.out.println("\nTESTING CREATING FROM CLASSROSTER TXT FILE");
        ClassRoster classRoster = new ClassRoster();
        Hashtable hash = classRoster.createRoster("connections");
        //System.out.println(hash);
        CourseConnectorGraph<Student> rosterGraph = graphFromHash(hash);
        System.out.println(rosterGraph);
        System.out.println("TESTING TRAVERSAL: " + rosterGraph.dfsTraversal("alee31", "achan"));
        rosterGraph.saveToTGF("gOut.tgf");
        
        
        // CourseConnectorGraph<Student> g0 = new CourseConnectorGraph<Student>();
        // g0.dfsTraversal(s1,s2);
        //g0.addVertex("Z");
        //g0.addVertex("K");
        //g0.saveToTGF("test0.tgf");
        // g0.addVertex(s1);
        // g0.addVertex(s2);
        // g0.addEdge(s1, s2, "CS111");
        
        
    }
}
