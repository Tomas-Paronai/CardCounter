package parohyapps.cardcounter.gui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import parohyapps.cardcounter.core.scorefragments.HighScoreFragment;
import parohyapps.cardcounter.core.scorefragments.NormalScoreFragment;

/**
 * Created by tomas on 3/3/2016.
 */
public class ScorePagerAdapter extends FragmentPagerAdapter {

    public ScorePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment;
        if(position == 0){
            fragment = new HighScoreFragment();
        }
        else{
            fragment = new NormalScoreFragment();
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
