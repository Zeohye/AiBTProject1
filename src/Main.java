import java.io.IOException;

/**
 * Created by Nils Henning on 11/22/2014.
 */
public class Main {

    public static void main(String[] args) {
        if(args.length != 2 || (args[0].equals("Experiment") && (!args[1].equals("1")&&!args[1].equals("2")&&!args[1].equals("3")))){
            System.out.println("Usage either: {tree1name tree2name} or {Experiment #} for experiment 1,2 or 3");
            return;
        }
        if(args[0].equals("Experiment")){

            String path="";
            if(args.length > 1 && args[1].equals("1"))
                path ="input/unpermuted/";
            if(args.length > 1 && args[1].equals("2"))
                path="input/permuted/";

            //Experiments 1 and 2
            if(!args[1].equals("3")) {
                String[] fileNames = {"kalign", "mafft", "muscle", "clustalw2"};

                int[][] values = new int[8][8];


                newickParser parser = new newickParser();
                Node tree1;
                Node tree2;
                try {
                    for (int i = 0; i < fileNames.length * 2; i++) {
                        if (i < fileNames.length)
                            tree1 = parser.Parse(path + "QuickTree/" + fileNames[i] + ".new");
                        else
                            tree1 = parser.Parse(path + "RapidNJ/" + fileNames[i % fileNames.length] + ".new");

                        tree1 = reRootTree.reRoot(tree1, "");
                        depthIteration iter = new depthIteration();
                        iter.firstNumbering(tree1);
                        for (int j = 0; j < fileNames.length * 2; j++) {
                            if (j < fileNames.length)
                                tree2 = parser.Parse(path + "QuickTree/" + fileNames[j] + ".new");
                            else
                                tree2 = parser.Parse(path + "RapidNJ/" + fileNames[j % fileNames.length] + ".new");

                            tree2 = reRootTree.reRoot(tree2, tree1.getName());

                            iter.numberRenameing(tree2);
                            iter.clearIntervals();
                            iter.intervals(tree1);
                            iter.intervals(tree2);
                            values[i][j] = iter.getInterval().size();
                        }
                    }
                } catch (IOException e) {
                    tree1 = null;
                    tree2 = null;
                }


                String print = "\t\t";
                for (int i = 0; i < fileNames.length; i++)
                    print += fileNames[i] + " QT " + "\t";
                for (int i = 0; i < fileNames.length; i++)
                    print += fileNames[i] + " RNJ" + "\t";
                System.out.println(print);
                print = "";
                for (int i = 0; i < values.length; i++) {
                    print += fileNames[i % fileNames.length];
                    if (i < fileNames.length)
                        print += " QT ";
                    else
                        print += " RNJ";

                    for (int j = 0; j < values.length; j++) {
                        print += "\t" + values[i][j];
                    }
                    System.out.println(print);
                    print = "";
                }
            }else{
                //Experiment 3
                String[] fileNames = {"kalign", "mafft", "muscle", "clustalw2"};

                int[] values = new int[8];


                newickParser parser = new newickParser();
                Node tree1;
                Node tree2;
                try {
                    for (int i = 0; i < fileNames.length * 2; i++) {
                        if (i < fileNames.length) {
                            tree1 = parser.Parse("input/unpermuted/QuickTree/" + fileNames[i] + ".new");
                            tree2 = parser.Parse("input/permuted/QuickTree/" + fileNames[i] + ".new");
                        }else {
                            tree1 = parser.Parse("input/unpermuted/RapidNJ/" + fileNames[i % fileNames.length] + ".new");
                            tree2 = parser.Parse("input/permuted/RapidNJ/" + fileNames[i % fileNames.length] + ".new");
                        }
                        tree1 = reRootTree.reRoot(tree1, "");
                        tree2 = reRootTree.reRoot(tree2, tree1.getName());

                        depthIteration iter = new depthIteration();
                        iter.firstNumbering(tree1);
                        iter.numberRenameing(tree2);
                        iter.intervals(tree1);
                        iter.intervals(tree2);
                        values[i] = iter.getInterval().size();
                    }
                } catch (IOException e) {
                    tree1 = null;
                    tree2 = null;
                }


                for(int i=0; i<values.length;i++){
                    if (i < fileNames.length)
                        System.out.println(fileNames[i]+" QT value\t" + values[i]);
                    else
                        System.out.println(fileNames[i%fileNames.length]+" RNJ value\t" + values[i]);
                }
            }


        }else {
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

            if (tree1 == null || tree2 == null) {
                System.out.println("Error parsing trees, most likely a wrong fileName");
                return;
            }
            /***Start of days algorithm***/

            //Make the trees rooted on the same node.
            tree1 = reRootTree.reRoot(tree1, "");
            tree2 = reRootTree.reRoot(tree2, tree1.getName());

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
}
