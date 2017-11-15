package com.ndv.fra.fragmentapplication;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by admin on 11/10/2017.
 */

public class FragmentA extends Fragment {
    EditText editTextA;
    Button buttonClickA;
    TextView textViewA;

    public FragmentA(){}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_a, container, false);
        mappingA(view);

        buttonClickA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView textViewMain = getActivity().findViewById(R.id.textViewMain);
                textViewMain.setText(editTextA.getText().toString());
            }
        });

        return view;
    }

    private void mappingA(View view) {
        editTextA = view.findViewById(R.id.editTextA);
        buttonClickA = view.findViewById(R.id.buttonClickA);
        textViewA = view.findViewById(R.id.textViewA);
    }

    public void setString(String text) {
        textViewA.setText(text);
    }
}
