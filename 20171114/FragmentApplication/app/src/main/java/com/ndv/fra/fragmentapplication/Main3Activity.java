package com.ndv.fra.fragmentapplication;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Main3Activity extends AppCompatActivity {
    TextView textViewMain3;
    Button buttonMain3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        buttonMain3 = findViewById(R.id.buttonMain3);
        final FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        buttonMain3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentC fragmentC = new FragmentC();
                Bundle bundle = new Bundle();
                bundle.putString("txt","Text Data");
                fragmentC.setArguments(bundle);

                fragmentTransaction.add(R.id.myLinearLayout, fragmentC);
                fragmentTransaction.commit();
            }
        });
    }
}
