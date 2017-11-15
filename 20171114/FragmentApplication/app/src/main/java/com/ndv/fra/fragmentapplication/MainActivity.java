package com.ndv.fra.fragmentapplication;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button buttonA, buttonB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        buttonA = findViewById(R.id.buttonA);
//        buttonB = findViewById(R.id.buttonB);
//
//        FragmentManager fragmentManager = getFragmentManager();
//        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//
//        buttonA.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                FragmentA fragmentA = new FragmentA();
//                fragmentTransaction.add(R.id.frameLayout, fragmentA);
//                fragmentTransaction.commit();
//            }
//        });
//
//        buttonB.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                FragmentB fragmentB = new FragmentB();
//                fragmentTransaction.add(R.id.frameLayout, fragmentB);
//                fragmentTransaction.commit();
//            }
//        });
    }

    public void AddFragment(View view) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        Fragment fragment = null;

        switch (view.getId())
        {
            case R.id.buttonA:
                fragment = new FragmentA();
                break;
            case R.id.buttonB:
                fragment = new FragmentB();
                break;
        }
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.exam162:
                startActivity(new Intent(MainActivity.this, Main2Activity.class));
                return true;

            case R.id.exam164:
                startActivity(new Intent(MainActivity.this, Main3Activity.class));
                return true;

            case R.id.exam165:
                startActivity(new Intent(MainActivity.this, Main5Activity.class));
                return true;

            case R.id.examTabHost:
                startActivity(new Intent(MainActivity.this, Main4Activity.class));
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }
}
