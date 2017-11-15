package com.ndv.fa.fragorientaapplication;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by admin on 11/15/2017.
 */

public class FragmentStudInfo extends Fragment {
    TextView mName, mYear, mAddr, mEmail;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info_stud, container, false);

        mapping(view);

        return view;
    }

    public void setInfo(Student info){
        mName.setText(info.getName());
        mYear.setText(String.valueOf(info.getBirthday()));
        mAddr.setText(info.getAddress());
        mEmail.setText(info.getEmail());
    }

    private void mapping(View view){
        mName = view.findViewById(R.id.txtvInfoName);
        mYear = view.findViewById(R.id.txtvInfoYear);
        mAddr = view.findViewById(R.id.txtvInfoAddr);
        mEmail = view.findViewById(R.id.txtvInfoMail);
    }
}
