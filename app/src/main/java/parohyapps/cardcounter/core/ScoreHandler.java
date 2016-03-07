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
import java.lang.reflect.Array;
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
        //ascendScore();
    }

    private ArrayList<Score> ascendScore(ArrayList<Score> list){
        ArrayList<Score> resultList = (ArrayList<Score>) list.clone();
        if(resultList != null){
            Log.d("LOAD FILE","ASCENDING");
            int max = resultList.size();

            boolean flag = true;
            int j;
            Score tmp;
            while(flag){
                flag = false;
                for(j = 0; j < max-1; j++){
                    if(resultList.get(j).getScore() < resultList.get(j+1).getScore()){
                        tmp = resultList.get(j);
                        resultList.set(j,resultList.get(j+1));
                        resultList.set(j+1,tmp);
                        flag = true;
                    }
                }
            }
        }

        return resultList;
    }

    public ArrayList<Score> getScoreList(boolean ascend){
        if(ascend){
            ArrayList<Score> ascList = scoreArray;
            return ascendScore(ascList);
        }
        return scoreArray;
    }
}
