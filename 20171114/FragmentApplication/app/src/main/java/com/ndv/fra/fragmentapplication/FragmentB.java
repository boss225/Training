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

public class FragmentB extends Fragment {
    EditText editTextB;
    Button buttonClickB;

    public FragmentB(){}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_b, container, false);
        mappingB(view);

        buttonClickB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView textViewA = getActivity().findViewById(R.id.textViewA);
                textViewA.setText(editTextB.getText().toString());
            }
        });

        return view;
    }

    private void mappingB(View view) {
        editTextB = view.findViewById(R.id.editTextB);
        buttonClickB = view.findViewById(R.id.buttonClickB);
    }
}
