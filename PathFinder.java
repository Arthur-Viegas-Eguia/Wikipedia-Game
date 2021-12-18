import java.util.*;
import java.io.*;
/**
 * The class finds the shortest path between two nodes in a graph.
 * It takes all possible nodes and the edges connecting them via 
 * two external files. The position it starts the path from and arrives
 * at are taken via runtime input.
 */
public class PathFinder {
  UnweightedGraph wikiGraph = new MysteryUnweightedGraphImplementation();
  Map<String, Integer> articleVertex = new HashMap<String, Integer>();
  Map<Integer, String> vertexArticle = new HashMap<Integer, String>();
  /**
  * Starts the class.
  * @param nodeFile name of the file with the node names
  * @param edgeFile name of the file with the edge names
  */
  public PathFinder(String nodeFile, String edgeFile){
    readNodes(nodeFile);
    readEdges(edgeFile);

  }
/**
 * Reads the nodes of the graph from an external file. 
 * @param nodeFile the file containing the graph vertexe's names
 */
  private void readNodes(String nodeFile) {
    File inputFile = new File(nodeFile);
    Scanner scanner = null;
    try {
        scanner = new Scanner(inputFile);
    } catch (FileNotFoundException e) {
        System.err.println(e);
        System.exit(1);
    }

    while(scanner.hasNextLine()) {
      String line = scanner.nextLine();
      if(line.length() > 0 && !line.substring(0,1).equals("#")) {
        Integer nodeNum = wikiGraph.addVertex();
        articleVertex.put(line, nodeNum);
        vertexArticle.put(nodeNum, line);
      }
    }
  }
/**
 * Reads the edges connecting two nodes from an external file. 
 * @param nodeFile the file containing the links between the nodes
 */
  private void readEdges(String edgeFile) {
    File inputFile = new File(edgeFile);
    Scanner scanner = null;
    try {
        scanner = new Scanner(inputFile);
    } catch (FileNotFoundException e) {
        System.err.println(e);
        System.exit(1);
    }
    while(scanner.hasNextLine()) {
      String line = scanner.nextLine();
      if(line.length() > 0 && !line.substring(0,1).equals("#")) {
        String[] splitline = line.split("\\s+");
        String begin = splitline[0];
        String end = splitline[1];
        int beginNum = articleVertex.get(begin);
        int endNum = articleVertex.get(end);
        wikiGraph.addEdge(beginNum, endNum);
      }
    }
  }


  /**
  * Performs breadth first search to find all possible shortest paths from 
  * start to all nodes it can reach within the graph
  * @param start the first node. The breadth first search will start from this node, and find a path to all nodes within reach.
  * @return A map, containing the shortest path between the original node (start) and all other nodes it can reach
  */
  public Map<Integer, Integer> breadthFirstSearch(String start) {
    HashMap<Integer, Integer> breadthFirst = new HashMap<Integer, Integer>();
    LinkedQueue<Integer> notVisited = new LinkedQueue<Integer>();
    int currentValue;
    int startNum = articleVertex.get(start);
    breadthFirst.put(startNum, null);
    notVisited.enqueue(startNum);
    while(!notVisited.isEmpty()){
      currentValue = notVisited.dequeue();
      for(int i: wikiGraph.getNeighbors(currentValue)){
        if((breadthFirst.get(i) == null) && ((i != startNum))){
          breadthFirst.put(i, currentValue);
          notVisited.enqueue(i);
        } 
      }
    }
    return breadthFirst;
  }
  
  /**
  * Returns a shortest path from node1 to node2.
  * @param node1 name of the starting article node
  * @param node2 name of the ending article node
  * @return list of the names of nodes on the shortest path
  */
  public List<String> getShortestPath(String node1, String node2){
    Map<Integer, Integer> resultsOfSearch = breadthFirstSearch(node1);
    List<String> finalPath = new LinkedList<String>();
    Integer currentValue = null;
    if(resultsOfSearch.containsKey(articleVertex.get(node2))){
      currentValue = articleVertex.get(node2); 
    }
    while(currentValue != null){
      finalPath.add(vertexArticle.get(currentValue));
      currentValue = resultsOfSearch.get(currentValue);
    }
    Collections.reverse(finalPath);
    return finalPath;
  }

  /**
   * Returns a string in the format "numberOfEdges,  numberOfNodes"
   * @return a string in the format "numberOfEdges,  numberOfNodes"
   */
  public String toString() {
    return wikiGraph.numEdges()+",  "+wikiGraph.numVerts();
  }
/**
 * Starts the program which finds the shortest path between two wikipedia
 * articles by clicking the links present in the wikipedia articles. Receives
 * and processes all user input.
 */
  public static void main(String[] args) {
    String articles;
    String links;
    Scanner userInput = new Scanner(System.in);
    String startArticle;
    String articleToReach;
    PathFinder shortestPath;
    List<String> path;
    System.out.println("Hello! What is the path to a file containing the name of all the wikipedia articles you want to consider?");
    articles = userInput.nextLine();
    System.out.println("Now, What is the path to a file containing all the links in these articles and where they lead to?");
    links = userInput.nextLine();
    shortestPath = new PathFinder(articles, links);
    System.out.println("In which article you want to start in?");
    startArticle = userInput.nextLine();
    System.out.println("Which article do you want to arrive at?");
    articleToReach = userInput.nextLine();
    path = shortestPath.getShortestPath(startArticle, articleToReach);
    if(path.size() == 0){
      System.out.println("There is no possible path starting at article "+startArticle+" Going to article "+articleToReach);
    } else{
      System.out.print("The shortest path is ");
      for(int i = 0; i < path.size(); i++){
        System.out.print(path.get(i));
        if(i != path.size() - 1){
          System.out.print(" -> ");
        } else{
          System.out.println();
        }
      }
    }
  }
  
}
                  