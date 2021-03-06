package parohyapps.cardcounter;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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

        EditText input = (EditText) findViewById(R.id.et_user_input);
        input.setInputType(InputType.TYPE_CLASS_NUMBER);
        ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(input.getWindowToken(), 0);

        String scoreString = getResources().getString(R.string.show_score);
        ((TextView)findViewById(R.id.tw_score_text)).setText(String.format(scoreString, game.getScore()));
    }

    private void generateGameState(){
        ImageView leftView = (ImageView) findViewById(R.id.iw_left_card);
        ImageView rightView = (ImageView) findViewById(R.id.iw_right_card);

        leftView.setImageDrawable(ContextCompat.getDrawable(this,getResources().getIdentifier(game.getLeftCard(),"drawable",getPackageName())));
        rightView.setImageDrawable(ContextCompat.getDrawable(this,getResources().getIdentifier(game.getRightCard(),"drawable",getPackageName())));

        int leftNumber = game.getLeftCardNumber();
        int rightNumber = game.getRightCardNumber();

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
        final TextView gameMessage = (TextView) findViewById(R.id.game_message);

        if(!userInput.getText().toString().equals("")){
            if(userInput.getText().toString().equals(String.valueOf(getResult()))){
                gameMessage.setText(R.string.result_ok);
                game.addScore(Math.abs(result));

                String scoreString = getResources().getString(R.string.show_score);
                ((TextView)findViewById(R.id.tw_score_text)).setText(String.format(scoreString,game.getScore()));
            }
            else{
                gameMessage.setText(R.string.result_wrong);
                game.takeLife();
                Toast.makeText(this,String.format(getResources().getString(R.string.lives_left),game.getLives()),Toast.LENGTH_SHORT).show();
            }
            Log.d("GAME MESSAGE","Showing Message");
            gameMessage.setVisibility(View.VISIBLE);
            gameMessage.postDelayed(new Runnable(){
                public void run(){
                    gameMessage.setVisibility(View.INVISIBLE);
                    Log.d("GAME MESSAGE","Hiding Message");
                }
            },3000);

            if(game.isGameOver()){
                dialogOn();
            }
            else{
                generateGameState();
            }
        }
    }

    private void dialogOn() {
        DialogFragment saveScoreDialog = new SaveScore();
        saveScoreDialog.setRetainInstance(true);

        Bundle scoreBundle = new Bundle();
        scoreBundle.putInt("SCORE", game.getScore());
        saveScoreDialog.setArguments(scoreBundle);

        saveScoreDialog.show(getSupportFragmentManager(),"TAG");
    }

    public void helpMe(View v){
        TextView leftHelper = (TextView) findViewById(R.id.tw_left_helper);
        TextView rightHelper = (TextView) findViewById(R.id.tw_right_helper);
        leftHelper.setVisibility(View.VISIBLE);
        rightHelper.setVisibility(View.VISIBLE);
    }
}
