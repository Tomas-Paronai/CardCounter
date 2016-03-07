package parohyapps.cardcounter.core;

import java.util.Random;

/**
 * Created by tomas on 2/29/2016.
 */
public class Game {

    public static int SPADES = 0;
    public static int DIAMONDS = 1;
    public static int HEARTS = 2;
    public static int CLUBS = 3;

    private boolean left;
    private int leftCard;
    private int rightCard;

    private int score;
    private int lives;

    public Game()
    {
        lives = 3;
        left = true;
    }
    public String getLeftCard(){
        leftCard = randInt(1,13);
        String result = "c"+getCardTypeString(leftCard)+"_of_"+getTypeString(randInt(0,3));
        return result;
    }

    public String getRightCard(){
        rightCard = randInt(1,13);
        String result = "c"+getCardTypeString(rightCard)+"_of_"+getTypeString(randInt(0,3));
        return result;
    }

    public int getLeftCardNumber(){
        return leftCard;
    }

    public int getRightCardNumber(){
        return rightCard;
    }

    public void addScore(int score){
        this.score += score;
    }

    public int getScore(){
        return score;
    }

    public void takeLife(){
        lives--;
    }

    public int getLives(){
        return lives;
    }

    public boolean isGameOver(){
        if(lives <= 0){
            return true;
        }
        return false;
    }

    private String getCardTypeString(int number){
        if(number > 1 && number < 11){
            return String.valueOf(number);
        }
        else{
            if(number == 11){
                return "jack";
            }
            else if(number == 12){
                return "queen";
            }
            else if(number == 13){
                return "king";
            }
            else{
                return "ace";
            }
        }
    }

    private String getTypeString(int type){
        setNegatives(type);
        if(type == SPADES){
            return "spades";
        }
        else if(type == DIAMONDS){

            return "diamonds";
        }
        else if(type == HEARTS){
            return "hearts";
        }
        else{
            return "clubs";
        }
    }

    private void setNegatives(int type){
        if(type == DIAMONDS || type == HEARTS){
            if(left){
                leftCard *= -1;
            }
            else{
                rightCard *= -1;
            }
        }

        if(left){
            left = false;
        }
        else{
            left = true;
        }
    }

    public int randInt(int min, int max) {
        Random rand = new Random();
        return rand.nextInt((max - min) + 1) + min;
    }
}
