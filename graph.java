
/**
 * Write a description of class CourseConnectorGraphProject here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.util.*;
import java.io.*;
public class CourseConnectorGraph<T> //implements Graph<T>
{
    // instance variables - 
    private Vector<T> vertices;
    private Vector<LinkedList<StudentCourseLabel>> arcs;

    /**
     * Constructor for objects of class CourseConnectorGraphProject
     */
    public CourseConnectorGraph(){
        vertices = new Vector<T>();
        arcs = new Vector<LinkedList<StudentCourseLabel>>();
        
    }
    //clone method
    public CourseConnectorGraph(CourseConnectorGraph g) {
    this.vertices = (Vector<T>)g.vertices.clone();
    this.arcs = new Vector<LinkedList<StudentCourseLabel>>();
    for(int i=0; i<g.vertices.size(); i++){
      this.arcs.addElement((LinkedList<StudentCourseLabel>)((LinkedList<T>)g.arcs.get(i)).clone());
    } 
  }
    
     public static CourseConnectorGraph graphFromHash(Hashtable<String,Vector<Student>> hashtable){
       CourseConnectorGraph g = new CourseConnectorGraph();
       Enumeration<String> allCourses = hashtable.keys();
       
       
       for(int i = 0; i<hashtable.size(); i++){
           String course = allCourses.nextElement();
           Vector studentsInCourse = hashtable.get(course);

           for(int j = 0; j<studentsInCourse.size(); j++){
               
               g.addVertex(studentsInCourse.get(j));
            }
           for(int k = 0; k< studentsInCourse.size() -1; k++){
               for(int l= k; l<studentsInCourse.size()-1;l++){
               g.addEdge(studentsInCourse.get(l), studentsInCourse.get(l+1), course);
            }
            }
        }
       
       return g;
    }
    public boolean isEmpty(){
        return vertices.isEmpty(); // or this.getNumVertices() == 0
    }
    
    public int getNumVertices(){
        return vertices.size();
    }
    
    public int getNumArcs(){
        int n = 0;
        for (int i = 0; i<arcs.size(); i++){
            n += arcs.get(i).size();
        }
        return n;
    }
    
    public void addVertex (T vertex){
        //if vertex already in the graph, do not add it again
        if (vertices.contains(vertex)){return;}
        
        //add vertex
        vertices.addElement(vertex);
        //add its corresponding empty list of connections
        arcs.addElement(new LinkedList<StudentCourseLabel>());
        //System.out.println("size of vertices: " + vertices.size());
    }
    //make new courseStudent connection to add to linkedList
    /******************************************************************
    * Inserts an edge between two vertices of the graph.
    * If one or both vertices do not exist, ignores the addition.
    ******************************************************************/
  public void addEdge (T vertex1, T vertex2, String course) {
    // getIndex will return NOT_FOUND if a vertex does not exist,
    // and the addArc() will not insert it
    this.addArc (vertex1, vertex2, course);
    addArc (vertex2, vertex1, course);
  }
  
    /**
     * adds course connection between two students
     * insert both ways
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
        //if n1 to n2 arc already exists
        if(l.contains(label)){return;}
        
        l.add(label);
    }
    /**
     * adds arc based on object indices
     */
    public void addArc (int v1, int v2, String course){
        T student1 = vertices.get(v1);
        T student2 = vertices.get(v2);
        addArc(student1, student2, course);
        addArc(student2, student1, course);
    }
    
    public void removeVertex (T v){
        if(!vertices.contains(v)){return;}
        int index = vertices.indexOf(v);
        vertices.remove(index);
        arcs.remove(index);
        //remove from all linked list wherever it appears
        for(int i = 0; i<arcs.size(); i++){
            LinkedList<StudentCourseLabel> ll = arcs.get(i);
            ll.remove(v);
        }
    }
    
    
    // public static CourseConnectorGraph<String> CourseConnectorGraphFromFile(String f){
        // CourseConnectorGraph<String> g = new CourseConnectorGraph<String>();
        // try{
            // Scanner scan = new Scanner(new File(f));
            // while(scan.hasNext()){
                // //int n = scan.nextInt();
                // String s = scan.next();
                // String[] line = s.split(";");
                // String[] courseListString = line[2].split(",");
                
                // LinkedList<String> courses = new LinkedList<String>();
                // for (int i = 0; i< courseListString.length; i++){
                    // courses.add(courseListString[0]);
                // }
                // Student currentStudent = new Student(line[0], line[1], courses);
                // g.addVertex(currentStudent);
            // }
            // while(scan.hasNext()){
                // int n1 = scan.nextInt();
                // int n2 = scan.nextInt();
                // g.addArc(n1-1, n2-1);
            // }
            // scan.close();
        // }catch(IOException e){
            // System.out.println("Cannot read from " + f);
        // }
        // return g;
    // }
    
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
    
    // public LinkedList<StudentCourseLabel> getSuccessors(T vertex){
        // int index = vertices.indexOf(vertex);
        // return arcs.get(index);
    // }
    
    // public LinkedList<StudentCourseLabel> getPredecessors(T vertex){
        // LinkedList<StudentCourseLabel> temp = new LinkedList<StudentCourseLabel>();
        // for(int i = 0; i<arcs.size(); i++){
            // LinkedList<StudentCourseLabel> ll = arcs.get(i);
            // if(ll.contains(vertex)){
                // temp.add(vertices.get(i));
            // }
        // }
        // return temp;
    // }
    public boolean containsVertex( T vertex){
        return vertices.contains(vertex);
    }
    private T usernameToStudent(String username){
        boolean found = false;
        int i = 0;
        while(!found){
            if(username.equals(vertices.get(i))){
                found = true;
            }
            else{
                i++;
            }
        }
        return (vertices.get(i));
    }
    
    public LinkedList<T> dfsTraversal(String startUsername, String endUsername) {
        T start = this.usernameToStudent(startUsername);
        T end = this.usernameToStudent(endUsername);
        LinkedList<T> search = new LinkedList<T>();
        LinkedList<String> labels = new LinkedList<String>();
        CourseConnectorGraph<T> graph = new CourseConnectorGraph<T>(this);
        if(!graph.containsVertex(start)){
            return search;
        }
        T currentVertex;
        LinkedList<T> traversalStack = new LinkedList<T>();
        
         traversalStack.push(start);
         search.add(start);
        //return when we find end
         while (!traversalStack.isEmpty()||!search.contains(end))
         {
            currentVertex = traversalStack.peek();
            int index = graph.vertices.indexOf(currentVertex);
            LinkedList<StudentCourseLabel> connections = graph.arcs.get(index);
            if(connections.isEmpty()){
                traversalStack.pop();
            }
            else{
                StudentCourseLabel nextNode = connections.get(0);
                String label = nextNode.getCourse();
                T student = vertices.get(nextNode.getSucc());
                
                traversalStack.push(student);
                
                if(!search.contains(student)){
                    search.add(student);
                    labels.add(label);
                }
                connections.pop();
            }
             
        }
        for(int i = 0; i<labels.size(); i++){
            System.out.print("student: " + search.get(i)+ "takes "+labels.get(i)+"with ");
        }
        System.out.print(search.get(search.size()-1));
        return search;
     }
    // public String traversalToString(){
        
    // }
    /******************************************************************
//    Returns a string representation of the graph. 
//    ******************************************************************/
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
    public static void main(String[] args){
        // CourseConnectorGraph<Student> g0 = new CourseConnectorGraph<Student>();
        // Student s1 = new Student("alee31", "alicia");
        // Student s2 = new Student("clee48", "camila");
        // g0.addVertex(s1);
        // g0.addVertex(s2);
        // g0.addEdge(s1, s2, "CS111");
        
        ClassRoster classRoster = new ClassRoster();
        Hashtable hash = classRoster.createRoster("connections");
        System.out.println(hash);
        CourseConnectorGraph<Student> rosterGraph = graphFromHash(hash);
        //System.out.println(rosterGraph);
        //rosterGraph.dfsTraversal("bchang31", "ckang29");
        
        //test isEmpty
        //System.out.println("Testing isEmpty. Expecting: true. Got: " + g0.isEmpty());
        //test getNumArcs
        // System.out.println("Testing getNumArcs. Expecting: 1. Got: " + g0.getNumArcs());
        // //test getNumVertices
        // System.out.println("Testing getNumVertices. Expecting: 2. Got: " + g0.getNumVertices());
        // System.out.println(g0);
        
        // g0.dfsTraversal(s1,s2);
        //g0.addVertex("Z");
        //g0.addVertex("K");
        //g0.saveToTGF("test0.tgf");
        
        // CourseConnectorGraph<String> g = CourseConnectorGraphFromFile("g5.tgf");
        // //System.out.println("The number of vetices: (3)" + g.getNumVertices());
        // //System.out.println("The number of arcs: (3)" + g.getNumArcs());
        rosterGraph.saveToTGF("gOut.tgf");
        
        // System.out.println("Predecessors of 3: [1,2,4] " + g.getPredecessors("3"));
        // System.out.println("Successors of 2: [3] " + g.getSuccessors("2"));
        // System.out.println("Predecessors of 5: [] " + g.getPredecessors("5"));
        // System.out.println("Successors of 5: [] " + g.getSuccessors("5"));
    }
}
