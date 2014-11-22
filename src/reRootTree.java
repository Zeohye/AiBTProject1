/**
 * Created by Gamer PRO on 11/22/2014.
 */
public class reRootTree {
    public static Node reRoot(Node root,String nodeName){
        boolean chooseFirst = false;
        if(nodeName =="")
            chooseFirst=true;

        Node node = root;
        if(chooseFirst) {
            boolean leaf = false;
            while (!leaf)
                if (node.getChildren().size() > 0)
                    node = node.getChildren().get(0);
                else
                    leaf = true;
        }else{
            node = findNodeInSubTree(node,nodeName);
        }

        //Make node new root.
        node = moveRoot(node);

        return node;
    }

    private static Node moveRoot(Node node) {
        Node root = new Node(node.getName(),null);
        String name = node.getName();
        Node workingNode = node;
        Node addingNode = root;
        while(workingNode.getParent() != null){
            workingNode = workingNode.getParent();
            Node parent = new Node(workingNode.getName(),addingNode);
            addingNode.addChild(parent);
            addingNode = parent;
            for(int i=0; i<workingNode.getChildren().size();i++){
                Node child = workingNode.getChildren().get(i);
                if(!existsInSubTree(child,name))
                    addingNode.addChild(child);
            }

        }
        return root;
    }

    private static Node findNodeInSubTree(Node node, String nodeName) {
        Node ret = null;
        if(node.getName().equals(nodeName))
            ret = node;
        if(node.getChildren().size()>0)
            for(int i=0; i<node.getChildren().size();i++) {
                Node tmp = findNodeInSubTree(node.getChildren().get(i), nodeName);
                if (tmp != null) ret = tmp;
            }
        return ret;
    }
    private static boolean existsInSubTree(Node node, String nodeName) {
        boolean exists = false;
        if(node.getName().equals(nodeName))
            exists = true;
        if(node.getChildren().size()>0)
            for(int i=0; i<node.getChildren().size();i++)
                if(existsInSubTree(node.getChildren().get(i), nodeName))
                    exists=true;
        return exists;
    }
}
