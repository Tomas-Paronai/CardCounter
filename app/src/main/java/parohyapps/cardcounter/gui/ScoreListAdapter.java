package parohyapps.cardcounter.gui;

import android.content.Context;
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

    public ScoreListAdapter(Context context, int resource, ArrayList<Score> objects) {
        super(context, resource, objects);
        if(objects != null){
            scoreList = objects;
        }
        else{
            scoreList = new ArrayList<>();
        }
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
            ((TextView)itemView.findViewById(R.id.tw_score_view)).setText(outString);
        }

        return itemView;
    }
}
