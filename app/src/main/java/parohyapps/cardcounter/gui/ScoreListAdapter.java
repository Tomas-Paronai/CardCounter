package parohyapps.cardcounter.gui;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import parohyapps.cardcounter.R;
import parohyapps.cardcounter.core.Score;

/**
 * Created by tomas on 3/1/2016.
 */
public class ScoreListAdapter extends ArrayAdapter<Score> {

    private ArrayList<Score> scoreList;
    private boolean bestScoreView;

    public ScoreListAdapter(Context context, int resource, ArrayList<Score> objects, boolean best) {
        super(context, resource, objects);
        if(objects != null){
            scoreList = objects;
        }
        else{
            scoreList = new ArrayList<>();
        }
        bestScoreView = best;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View itemView = convertView;

        if(itemView == null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            itemView = inflater.inflate(R.layout.score_list_item,null);
        }

        if(scoreList != null){
            Score current = scoreList.get(position);
            String outString = current.getName() + " - " + current.getScore();
            TextView scoreView = (TextView) itemView.findViewById(R.id.tw_score_view);
            scoreView.setText(outString);

            if(bestScoreView){
                if(position == 0){
                    scoreView.setTextColor(Color.rgb(255,30,0));
                }
                else if(position == 1){
                    scoreView.setTextColor(Color.rgb(255,150,0));
                }
                else if(position == 2){
                    scoreView.setTextColor((Color.rgb(255,200,0)));
                }
            }

        }

        return itemView;
    }
}
