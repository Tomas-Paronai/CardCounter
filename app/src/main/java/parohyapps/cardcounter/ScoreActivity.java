package parohyapps.cardcounter;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import parohyapps.cardcounter.core.Score;
import parohyapps.cardcounter.core.ScoreHandler;
import parohyapps.cardcounter.gui.ScoreListAdapter;

public class ScoreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        ScoreHandler handler = new ScoreHandler(Environment.getExternalStorageDirectory());
        for(Score tmp : handler.getScoreList()){
            Log.d("LIST",tmp.getName()+" - "+tmp.getScore());
        }

        ArrayAdapter adapter = new ScoreListAdapter(this,R.layout.score_list_item,handler.getScoreList());
        ((ListView)findViewById(R.id.score_list)).setAdapter(adapter);
    }
}
