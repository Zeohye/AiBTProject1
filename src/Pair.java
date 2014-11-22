/**
 * Created by Gamer PRO on 11/22/2014.
 */
public class Pair {

    public int min;
    public int max;
    public int leafs;
    public Pair() {
        leafs=0;
        min = Integer.MAX_VALUE;
        max = Integer.MIN_VALUE;
    }
    public Pair combine(Pair p2){
        if(min > p2.min)
            min = p2.min;
        if(max < p2.max)
            max = p2.max;
        leafs +=p2.leafs;
        return this;
    }

}
