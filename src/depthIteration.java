import java.util.HashMap;

/**
 * Created by Gamer PRO on 11/22/2014.
 */
public class depthIteration {
    private int counter = 1;
    private HashMap<String,Integer> values = new HashMap<String,Integer>();

    public void firstNumbering(Node node){
        for(int i=0; i < node.getChildren().size();i++)
            firstNumbering(node.getChildren().get(i));
        if(node.getChildren().size()==0) {
            node.setValue(counter++);
            values.put(node.getName(),node.getValue());
        }
    }

    public void numberRenameing(Node node){
        for(int i=0; i < node.getChildren().size();i++)
            numberRenameing(node.getChildren().get(i));
        if(node.getChildren().size()==0) {
            node.setValue(values.get(node.getName()));
        }
    }
}
