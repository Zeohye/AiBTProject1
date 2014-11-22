import java.io.IOException;

/**
 * Created by Nils Henning on 11/22/2014.
 */
public class Main {

    public static void main(String[] args) {
        String path1 = args[0];
        String path2 = args[1];
        Node tree1;
        Node tree2;
        newickParser parser = new newickParser();
        try {
            tree1 = parser.Parse("input/" + path1 + ".new");
            tree2 = parser.Parse("input/" + path2 + ".new");
        } catch (IOException e) {
            tree1 = null;
            tree2 = null;
        }

        if(tree1 == null || tree2 == null){
            System.out.println("Error parsing trees!!!");
            return;
        }
        /***Start of days algorithm***/

        //Make the trees rooted on the same node.
        tree1 = reRootTree.reRoot(tree1,"");
        tree2 = reRootTree.reRoot(tree2,tree1.getName());

        //Depth first numbering of the first tree
        depthIteration iter = new depthIteration();
        iter.firstNumbering(tree1);
        //Number the tree2 nodes the same as the nodes in tree1
        iter.numberRenameing(tree2);

        //distance between trees
        iter.intervals(tree1);
        iter.intervals(tree2);
        System.out.println(iter.getInterval().size());

    }
}
