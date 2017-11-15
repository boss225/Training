package com.ndv.fra.fragmentapplication;

import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Main5Activity extends AppCompatActivity implements BackInterface {

    Button btn4, btn8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        btn4 = findViewById(R.id.button4);
        btn8 = findViewById(R.id.button8);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportFragmentManager().popBackStack();
            }
        });
        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                getSupportFragmentManager().popBackStack("fragA", 0);
                FragmentDialog fragmentDialog = new FragmentDialog();
                fragmentDialog.show(getFragmentManager(), "dialog");
            }
        });
    }


    public void AddContentFragment(View view) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        Fragment fragment = null;

        switch (view.getId()){
            case R.id.button:
                fragment = new FragmentA();
                fragmentTransaction.add(R.id.fragmentContent, fragment, "fragA");
                fragmentTransaction.addToBackStack("fragA");
                break;
            case R.id.button2:
                fragment = new FragmentB();
                fragmentTransaction.add(R.id.fragmentContent, fragment, "fragB");
                fragmentTransaction.addToBackStack("fragB");
                break;
            case R.id.button3:
                fragment = new FragmentC();
                fragmentTransaction.add(R.id.fragmentContent, fragment, "fragC");
                fragmentTransaction.addToBackStack("fragC");
                break;
        }
        fragmentTransaction.commit();
    }


    public void RemoveContentFragment(View view) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        Fragment fragment = null;

        switch (view.getId()){
            case R.id.button5:
                fragment = getSupportFragmentManager().findFragmentByTag("fragA");
                break;
            case R.id.button6:
                fragment = getSupportFragmentManager().findFragmentByTag("fragB");
                break;
            case R.id.button7:
                fragment = getSupportFragmentManager().findFragmentByTag("fragC");
                break;
        }
        if (fragment != null) {
            fragmentTransaction.remove(fragment);
            fragmentTransaction.commit();
        } else {
            Toast.makeText(this, "Null", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0){
            getSupportFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void BackValue(boolean i, String key) {
        if (i){
            switch (key){
                case "dialog":
                    getSupportFragmentManager().popBackStack("fragA", 0);
                    break;
                case "spre":
                    SharedPreferences sharedPreferences = getApplication().getSharedPreferences("MyText",0);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
//                    editor.putBoolean("key_name", true);
//                    editor.putInt("key_name", 22592);
//                    editor.putFloat("key_name", 96);
//                    editor.putLong("key_name", 92);
                    editor.putString("key_name", "string value");


                    editor.commit();
                    Toast.makeText(this,sharedPreferences.getString("key_name", null), Toast.LENGTH_SHORT).show();
                    break;
            }
        }else {
            Toast.makeText(this, "No", Toast.LENGTH_SHORT).show();
        }
    }
}
