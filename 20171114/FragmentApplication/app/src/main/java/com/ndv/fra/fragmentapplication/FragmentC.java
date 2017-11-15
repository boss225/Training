package com.ndv.fra.fragmentapplication;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by admin on 11/10/2017.
 */

public class FragmentC extends Fragment {
    TextView textViewC;
    Button mButton;
    BackInterface backInterface;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_c, container, false);

        textViewC = view.findViewById(R.id.textViewC);
        mButton = view.findViewById(R.id.btnEdit);

        Bundle bundle = getArguments();
        if (bundle != null){
            textViewC.setText(bundle.getString("txt"));
        }

        backInterface = (BackInterface) getActivity();
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backInterface.BackValue(true,"spre");
            }
        });

        return view;
    }
}
