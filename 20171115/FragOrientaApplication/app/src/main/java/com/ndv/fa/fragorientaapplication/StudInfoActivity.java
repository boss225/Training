package com.ndv.fa.fragorientaapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class StudInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stud_info);

        Intent intent = getIntent();
        Student student = (Student) intent.getSerializableExtra("data");

        FragmentStudInfo fragmentStudInfo = (FragmentStudInfo) getFragmentManager().findFragmentById(R.id.fragmInfo);
        fragmentStudInfo.setInfo(student);
    }
}
