package parohyapps.cardcounter;

import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import parohyapps.cardcounter.core.Game;
import parohyapps.cardcounter.dialog.SaveScore;

public class PlayActivity extends AppCompatActivity {

    private Game game;
    private int result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        game = new Game();
        generateGameState();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
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
        leftHelper.setVisibility(View.INVISIBLE);
        rightHelper.setVisibility(View.INVISIBLE);

        EditText userInput = (EditText) findViewById(R.id.et_user_input);
        userInput.setText("");
    }

    public int getResult(){
        return result;
    }

    public void respondKeyboard(View v){
        //TODO sound
        //TODO reset input
        EditText userInput = (EditText) findViewById(R.id.et_user_input);
        String origString = userInput.getText().toString();
        switch(v.getId()){
            case R.id.kb_confirm:
                confirmResult();
                origString = "";
                break;
            case R.id.kb_delete:
                if(origString.length() > 0){
                    origString = origString.substring(0,origString.length()-1);
                }
                break;
            case R.id.kb_negative:
                if(origString.length() == 0){
                    origString += "-";
                }
                break;
            case R.id.kb_num0:
                origString += "0";
                break;
            case R.id.kb_num1:
                if(origString.length() > 0 && !origString.substring(0,1).equals("0")){
                    origString += "1";
                }
                else{
                    origString = "1";
                }
                break;
            case R.id.kb_num2:
                if(origString.length() > 0 && !origString.substring(0,1).equals("0")){
                    origString += "2";
                }
                else{
                    origString = "2";
                }
                break;
            case R.id.kb_num3:
                if(origString.length() > 0 && !origString.substring(0,1).equals("0")){
                    origString += "3";
                }
                else{
                    origString = "3";
                }
                break;
            case R.id.kb_num4:
                if(origString.length() > 0 && !origString.substring(0,1).equals("0")){
                    origString += "4";
                }
                else{
                    origString = "4";
                }
                break;
            case R.id.kb_num5:
                if(origString.length() > 0 && !origString.substring(0,1).equals("0")){
                    origString += "5";
                }
                else{
                    origString = "5";
                }
                break;
            case R.id.kb_num6:
                if(origString.length() > 0 && !origString.substring(0,1).equals("0")){
                    origString += "6";
                }
                else{
                    origString = "6";
                }
                break;
            case R.id.kb_num7:
                if(origString.length() > 0 && !origString.substring(0,1).equals("0")){
                    origString += "7";
                }
                else{
                    origString = "7";
                }
                break;
            case R.id.kb_num8:
                if(origString.length() > 0 && !origString.substring(0,1).equals("0")){
                    origString += "8";
                }
                else{
                    origString = "8";
                }
                break;
            case R.id.kb_num9:
                if(origString.length() > 0 && !origString.substring(0,1).equals("0")){
                    origString += "9";
                }
                else{
                    origString = "9";
                }
                break;
        }
        userInput.setText(origString);
    }

    public void confirmResult(){
        EditText userInput = (EditText) findViewById(R.id.et_user_input);
        TextView gameMessage = (TextView) findViewById(R.id.game_message);

        if(!userInput.getText().toString().equals("")){
            if(userInput.getText().toString().equals(String.valueOf(getResult()))){
                gameMessage.setText(getResources().getIdentifier("result_ok","string",getPackageName()));
                game.addScore(Math.abs(result));
            }
            else{
                gameMessage.setText(getResources().getIdentifier("result_wrong","string",getPackageName()));
                game.takeLife();
            }

            if(game.isGameOver()){
                getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
                DialogFragment saveScoreDialog = new SaveScore();
                saveScoreDialog.setRetainInstance(true);

                Bundle scoreBundle = new Bundle();
                scoreBundle.putInt("SCORE",game.getScore());
                saveScoreDialog.setArguments(scoreBundle);

                saveScoreDialog.show(getSupportFragmentManager(),"TAG");
            }
            else{
                generateGameState();
            }
        }
    }

    public void helpMe(View v){
        TextView leftHelper = (TextView) findViewById(R.id.tw_left_helper);
        TextView rightHelper = (TextView) findViewById(R.id.tw_right_helper);
        leftHelper.setVisibility(View.VISIBLE);
        rightHelper.setVisibility(View.VISIBLE);
    }
}
