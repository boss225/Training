package com.ndv.fra.fragmentapplication;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

/**
 * Created by admin on 11/15/2017.
 */

public class FragmentDialog extends DialogFragment {

    private BackInterface backInterface;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        backInterface = (BackInterface) getActivity();

        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
        dialog.setTitle("Confirm");
        dialog.setMessage("Yes or No ?");
        dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
//                Toast.makeText(getActivity(), "Yes", Toast.LENGTH_SHORT).show();
                backInterface.BackValue(true, "dialog");
            }
        });
        dialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                backInterface.BackValue(false,"dialog");
            }
        });

        return dialog.create();
    }
}
