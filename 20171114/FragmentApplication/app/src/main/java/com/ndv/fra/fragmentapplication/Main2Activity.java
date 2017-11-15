package com.ndv.fra.fragmentapplication;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    TextView txtMain;
    Button btnMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        txtMain = findViewById(R.id.textViewMain);
        btnMain = findViewById(R.id.buttonMain);

        btnMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentA fragmentA = (FragmentA) getSupportFragmentManager().findFragmentById(R.id.fragmentA);
                fragmentA.setString(txtMain.getText().toString());
            }
        });
    }
}
