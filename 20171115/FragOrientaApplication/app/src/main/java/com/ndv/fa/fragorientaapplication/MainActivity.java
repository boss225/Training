package com.ndv.fa.fragorientaapplication;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements CallBackInterface{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void getStudent(Student student) {
        FragmentStudInfo fragmentStudInfo = (FragmentStudInfo) getFragmentManager().findFragmentById(R.id.fragmentInfo);
        Configuration configuration = getResources().getConfiguration();

//        if (fragmentStudInfo != null && fragmentStudInfo.isInLayout()){
        if (fragmentStudInfo != null && configuration.orientation == Configuration.ORIENTATION_LANDSCAPE){
            fragmentStudInfo.setInfo(student);
        } else {
            Intent intent = new Intent(MainActivity.this, StudInfoActivity.class);
            intent.putExtra("data", student);
            startActivity(intent);
        }
    }
}
