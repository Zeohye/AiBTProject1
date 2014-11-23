import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by Gamer PRO on 11/22/2014.
 */
public class depthIteration {
    private int counter = 1;
    private HashMap<String,Integer> values = new HashMap<String,Integer>();
    private HashSet<String> intervals = new HashSet<String>();

    public void firstNumbering(Node node){
        for(int i=0; i < node.getChildren().size();i++)
            firstNumbering(node.getChildren().get(i));
        if(node.getChildren().size()==0) {
            node.setValue(counter);
            values.put(node.getName(),counter);
            counter++;
        }
    }

    public void numberRenameing(Node node){
        for(int i=0; i < node.getChildren().size();i++)
            numberRenameing(node.getChildren().get(i));
        if(node.getChildren().size()==0) {
            node.setValue(values.get(node.getName()));
        }
    }

    public HashSet<String> intervals(Node node){
        _intervals(node, new Pair());
        return intervals;
    }
    private Pair _intervals(Node node,Pair pair){
        for(int i=0; i < node.getChildren().size();i++)
            pair.combine(_intervals(node.getChildren().get(i), new Pair()));
        if(node.getName().equals("InternalNode")){
            //if(pair.max-pair.min+1==pair.leafs)
            String name = "Max:"+pair.max+"Min:"+pair.min + "Leafs:"+pair.leafs;
            if(intervals.contains(name))
                intervals.remove(name);
            else
                intervals.add(name);
        }else{
            if(node.getValue()>pair.max)
                pair.max = node.getValue();
            if(node.getValue()<pair.min)
                pair.min = node.getValue();
            pair.leafs++;
        }
        return pair;
    }
    public void clearIntervals(){intervals.clear();}
    public HashSet<String> getInterval(){return intervals;}
}
