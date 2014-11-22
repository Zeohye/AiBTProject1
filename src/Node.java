import java.util.ArrayList;

/**
 * Created by Nils Henning on 11/22/2014.
 */
public class Node {
    private String name;
    private int value;
    Boolean visitedLeft = false;
    Boolean visitedRight= false;
    private Node parent;
    private ArrayList<Node> children;
    public Node(String name,Node parent){
        this.name = name;
        this.parent = parent;
        children = new ArrayList<Node>();
    }

    public Node getParent(){return parent;}
    public ArrayList<Node> getChildren(){return children;}
    public String getName(){return name;}
    public void addChild(Node child){if(child!=null)children.add(child);}
    public void setValue(int value){this.value=value;}
    public int getValue(){return value;}

}