package parohyapps.cardcounter;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import parohyapps.cardcounter.core.Game;

public class PlayActivity extends AppCompatActivity {

    private Game game;
    private int result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        game = new Game();
        generateGameState();
    }

    private void generateGameState(){
        ImageView leftView = (ImageView) findViewById(R.id.iw_left_card);
        ImageView rightView = (ImageView) findViewById(R.id.iw_right_card);

        leftView.setImageDrawable(ContextCompat.getDrawable(this,getResources().getIdentifier(game.getLeftCard(),"drawable",getPackageName())));
        rightView.setImageDrawable(ContextCompat.getDrawable(this,getResources().getIdentifier(game.getRightCard(),"drawable",getPackageName())));

        int leftNumber = game.getLeftCardNumber();
        int rightNumber = game.getRightCardNumber();

        if(game.isLeftNegative()){
            leftNumber *= -1;
        }
        if(game.isRightNegative()){
            rightNumber *= -1;
        }

        result = leftNumber + rightNumber;

        Log.d("Generated Game","Left: "+leftNumber);
        Log.d("Generated Game","Right: "+rightNumber);
        Log.d("Generated Game","Result: "+result);

        TextView leftHelper = (TextView) findViewById(R.id.tw_left_helper);
        TextView rightHelper = (TextView) findViewById(R.id.tw_right_helper);
        leftHelper.setText(String.valueOf(leftNumber));
        rightHelper.setText(String.valueOf(rightNumber));

        EditText userInput = (EditText) findViewById(R.id.et_user_input);
        userInput.setText("");
    }

    public int getResult(){
        return result;
    }

    public void confirmResult(View v){
        EditText userInput = (EditText) findViewById(R.id.et_user_input);
        TextView gameMessage = (TextView) findViewById(R.id.game_message);
        if(!userInput.getText().toString().equals("")){
            if(userInput.getText().toString().equals(String.valueOf(getResult()))){
                gameMessage.setText(getResources().getIdentifier("result_ok","string",getPackageName()));
            }
            else{
                gameMessage.setText(getResources().getIdentifier("result_wrong","string",getPackageName()));
            }
            generateGameState();
        }
    }

    public void helpMe(View v){
        TextView leftHelper = (TextView) findViewById(R.id.tw_left_helper);
        TextView rightHelper = (TextView) findViewById(R.id.tw_right_helper);
        leftHelper.setVisibility(View.VISIBLE);
        rightHelper.setVisibility(View.VISIBLE);
    }
}
