package parohyapps.cardcounter.core;

import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.util.ArrayList;

/**
 * Created by tomas on 3/1/2016.
 */
public class ScoreHandler {

    private final static String fileName = "score.dat";
    private final static String dirName = "cardcounter";
    private static ArrayList<Score> scoreArray;
    private File file,dir;

    public ScoreHandler(File parent){
        dir = new File(parent,dirName);
        if(!dir.exists()){
            dir.mkdir();
        }
        file = new File(dir,fileName);
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if(scoreArray == null){
            scoreArray = new ArrayList<>();
        }
        load();
    }

    public void add(Score score){
        scoreArray.add(score);
        Log.d("ADD SCORE",""+scoreArray.size());
        save();
        load();
    }

    private void save(){
        //TODO save file
        if(file != null){
            try{
                FileOutputStream outputStream = new FileOutputStream(file);
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

                for(Score tmpScore : scoreArray){
                   objectOutputStream.writeObject(tmpScore);
                }
                objectOutputStream.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void load(){
        scoreArray.clear();
        if(file != null){
            try {
                FileInputStream inputStream = new FileInputStream(file);
                ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
                //TODO read file to array
                while(true){
                    scoreArray.add((Score) objectInputStream.readObject());
                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (StreamCorruptedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        ascendScore();
    }

    private void ascendScore(){
        if(scoreArray != null){
            Log.d("LOAD FILE","ASCENDING");
            int max = scoreArray.size();
            for(int i = 0; i <= max-1; i++){
                for(int j = i; j < max-1; j++){
                    if(scoreArray.get(i).getScore() < scoreArray.get(j).getScore()){
                        Score tmp = scoreArray.get(i);
                        scoreArray.set(i,scoreArray.get(j));
                        scoreArray.set(j, tmp);
                    }
                }
            }
        }
    }

    public ArrayList<Score> getScoreList(){
        return scoreArray;
    }
}
