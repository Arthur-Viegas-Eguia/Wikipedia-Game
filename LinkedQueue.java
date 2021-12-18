/**
 * Models a queue using a linked list.
 */
public class LinkedQueue<T> {
    Node firstNode = null;
    Node lastNode = null;

    private class Node {
        private T    data; // Entry in queue
        private Node next; // Link to next element
    
        private Node(T dataPortion) {
            this(dataPortion, null);
        } // end constructor

        private Node(T dataPortion, Node nextNode) {
            data = dataPortion;
            next = nextNode;
        } // end constructor
    } // end Node
    /**
     * Adds element newEntry to the tail of the queue. 
     * @param newEntry an object of type T which will be added to the queue
     */
    public void enqueue(T newEntry){
        if(firstNode == null){
            Node newNode = new Node(newEntry);
            firstNode = newNode;
        }
        else{
            Node currentNode = firstNode;
            while(currentNode.next != null){
                currentNode = currentNode.next;
            }
            Node nodeToAdd = new Node(newEntry);
            currentNode.next = nodeToAdd;
        }
    }
    /**
     * Removes and returns the first item from the queue.
     * @return the first item from the queue, which was just removed.
     */
    public T dequeue(){
        T returnValue = null;
        if(firstNode != null){
            returnValue = firstNode.data;
            firstNode = firstNode.next;
        }
        return returnValue;
    }
    /**
     * returns the first item on queue.
     * @return the first item on queue
     */
    public T peek(){
        T valueToReturn = null;
        if(firstNode != null){
            valueToReturn = firstNode.data;
        }
        return valueToReturn;
    }
    /**
     * returns true if the queue is empty; false otherwise
     * @return true if the queue is empty; false otherwise.
     */
    public boolean isEmpty(){
        return firstNode == null;
    }
    /**
     * Removes all elements from the queue.
     */
    public void clear(){
        firstNode = null;
    }
    /**
     * tests all the methods of the class.
     */
    public static void main(String[] args){
        LinkedQueue<String> test = new LinkedQueue<String>();
        

        System.out.println(test.isEmpty());
        test.enqueue("a");
        System.out.println(test.isEmpty());
        System.out.println(test.dequeue());
        System.out.println(test.dequeue());
        System.out.println(test.isEmpty());
        test.enqueue("a");
        System.out.println(test.peek());
        test.enqueue("b");
        System.out.println(test.peek());
        test.enqueue("c");
        System.out.println(test.peek());
        test.enqueue("d");
        System.out.println(test.peek());
        test.enqueue("e");
        System.out.println(test.peek());
        System.out.println(test.dequeue());
        System.out.println(test.dequeue());
        System.out.println(test.dequeue());
        System.out.println(test.dequeue());
        test.enqueue("a");
        test.enqueue("b");
        test.clear();
        System.out.println(test.isEmpty());
        test.isEmpty();

    }
}