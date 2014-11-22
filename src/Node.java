import java.util.List;

/**
 * Created by Nils Henning on 11/22/2014.
 */
public class Node<T> {
    private T data;
    Boolean visitedLeft = false;
    Boolean visitedRight= false;
    private Node<T> parent;
    private List<Node<T>> children;
}