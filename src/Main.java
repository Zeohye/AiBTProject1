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


        //distance between trees
    }
}
