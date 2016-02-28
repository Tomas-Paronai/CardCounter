package parohyapps.cardcounter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MenuActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void startGame(View v){
        Intent newGameIntent = new Intent(MenuActivity.this, PlayActivity.class);
        startActivity(newGameIntent);
    }

    public void viewScore(View v){
        Intent viewScoreIntent = new Intent(MenuActivity.this, ScoreActivity.class);
        startActivity(viewScoreIntent);
    }

    public void exit(View v){
        finish();
    }
}
