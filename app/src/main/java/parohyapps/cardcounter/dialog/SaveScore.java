package parohyapps.cardcounter.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import parohyapps.cardcounter.R;
import parohyapps.cardcounter.core.Score;
import parohyapps.cardcounter.core.ScoreHandler;

/**
 * Created by tomas on 3/1/2016.
 */
public class SaveScore extends DialogFragment{

    private int score;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState != null && savedInstanceState.containsKey("SCORE")){
            score = savedInstanceState.getInt("SCORE");
        }
        else if(getArguments() != null && getArguments().containsKey("SCORE")){
            score = getArguments().getInt("SCORE");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.save_score_dialog,null);
        ((TextView)view.findViewById(R.id.tw_dialog_score)).setText(String.valueOf(score));
        final EditText input = (EditText) view.findViewById(R.id.et_dialog_name);

        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);

        builder.setView(view)
            .setPositiveButton(R.string.confirm_save, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (input.getText().toString().length() > 0) {
                        Toast.makeText(getActivity(), getResources().getString(R.string.alert_confirm),Toast.LENGTH_SHORT).show();
                        ScoreHandler handler = new ScoreHandler(Environment.getExternalStorageDirectory());
                        handler.add(new Score(score, input.getText().toString()));
                        getActivity().finish();
                    }
                }
            })
            .setNegativeButton(R.string.cancel_save, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(getActivity(), getResources().getString(R.string.alert_cancel),Toast.LENGTH_SHORT).show();
                    getActivity().finish();
                    SaveScore.this.getDialog().cancel();
                }
            });



        return builder.create();
    }
}
