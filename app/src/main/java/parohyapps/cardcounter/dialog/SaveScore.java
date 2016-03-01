package parohyapps.cardcounter.dialog;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.support.v4.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import parohyapps.cardcounter.R;

/**
 * Created by tomas on 3/1/2016.
 */
public class SaveScore extends DialogFragment{

    private int score;
    private Context con;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.save_score_dialog,null);
        ((TextView)view.findViewById(R.id.tw_dialog_score)).setText(String.valueOf(score));

        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);

        builder.setView(view)
            .setPositiveButton(R.string.confirm_save, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //TODO save score & file
                    getActivity().finish();
                }
            })
            .setNegativeButton(R.string.cancel_save, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    getActivity().finish();
                    SaveScore.this.getDialog().cancel();
                }
            });



        return builder.create();
    }
}
