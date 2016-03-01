package parohyapps.cardcounter.core;

import java.io.Serializable;

/**
 * Created by tomas on 3/1/2016.
 */
public class Score implements Serializable{

    private int score;
    private String name;

    public Score(int score, String name){
        this.score = score;
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public String getName(){
        return name;
    }
}
