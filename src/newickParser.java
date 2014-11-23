import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
/**
 * Created by Nils Henning on 11/22/2014.
 */
public class newickParser {
    public Node Parse(String path) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(path));
        String line,data ="";

        while ((line = br.readLine())!= null) {
            data +=line;
        }
        if(br != null)br.close();
        data = data.replace("'","");
        Node root = new Node("InternalNode",null);
        createTree(data.substring(1, data.length() - 2), root);
        return root;
    }

    private Node createTree(String data, Node parent){
        if( data.charAt(0) !='('){
            //first element is not a subtree construct, meaning we can use it directly.
            int index = data.indexOf(',');
            parent.addChild(new Node(data.substring(0,data.indexOf(':')),parent));
            if(index != -1)
                parent.addChild(createTree(data.substring(index+1), parent));//recursive call on rest of string
        }else{
            //First element is a subtree construct.
            int index = getExp(data);
            String exp = data.substring(1, index);
            exp = exp.substring(0,exp.lastIndexOf(':')-1);

            Node test = new Node("InternalNode",parent);
            parent.addChild(test);
            test.addChild(createTree(exp,test));
            if(data.length()>index+1)
                parent.addChild(createTree(data.substring(index+1,data.length()-2),parent));
        }
       return null;
    }

    private int getExp(String data){
        int level = 1;
        int index = 1;
        char ch = ' ';
        while(level > 0 || ch !=',' && data.length()-1 >=index){
            ch = data.charAt(index);
            if(ch == '(') level++;
            else if(ch == ')')level--;
            index++;
        }
        return index-1;
    }
}
