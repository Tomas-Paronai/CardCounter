package parohyapps.cardcounter.core;

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

    }

    public void add(Score score){
        scoreArray.add(score);
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
    }

    public ArrayList<Score> getScoreList(){
        return scoreArray;
    }
}
