package parohyapps.cardcounter;

import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import parohyapps.cardcounter.core.Score;
import parohyapps.cardcounter.core.ScoreHandler;
import parohyapps.cardcounter.gui.ScoreListAdapter;
import parohyapps.cardcounter.gui.ScorePagerAdapter;

public class ScoreActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        ViewPager scoreViewPager = (ViewPager) findViewById(R.id.score_view_pager);
        FragmentPagerAdapter adapter = new ScorePagerAdapter(getSupportFragmentManager());
        scoreViewPager.setAdapter(adapter);
    }

    public void gameMenu(View view){
        finish();
    }
}
