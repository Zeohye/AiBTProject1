import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Nils Henning on 11/22/2014.
 */
public class newickParser {

    public static Node Parse(String path) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(path));
        String line,result ="";


        while ((line = br.readLine())!= null) {
            result +=line;
        }
        if(br != null)br.close();



        return null;
    }

}
