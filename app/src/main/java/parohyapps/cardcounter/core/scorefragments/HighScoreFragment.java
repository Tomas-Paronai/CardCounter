package parohyapps.cardcounter.core.scorefragments;

import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import parohyapps.cardcounter.R;
import parohyapps.cardcounter.core.ScoreHandler;
import parohyapps.cardcounter.gui.ScoreListAdapter;

/**
 * Created by tomas on 3/3/2016.
 */
public class HighScoreFragment extends Fragment{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.highscore_fragment,null);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ScoreHandler scoreHandler = new ScoreHandler(Environment.getExternalStorageDirectory());

        ArrayAdapter adapter = new ScoreListAdapter(this.getContext(),R.layout.score_list_item,scoreHandler.getScoreList(true),true);
        ((ListView)view.findViewById(R.id.score_list)).setAdapter(adapter);
    }
}
