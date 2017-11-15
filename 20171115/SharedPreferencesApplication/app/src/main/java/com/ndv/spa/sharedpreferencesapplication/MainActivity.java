package com.ndv.spa.sharedpreferencesapplication;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText mEtName, mEtEmail;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mapping();

        sharedPreferences = getApplicationContext().getSharedPreferences("myData", 0);
        if (sharedPreferences.contains("name")){
            mEtName.setText(sharedPreferences.getString("name", null));
        }
        if (sharedPreferences.contains("email")){
            mEtEmail.setText(sharedPreferences.getString("email", null));
        }

    }

    private void mapping() {
        mEtName = findViewById(R.id.etName);
        mEtEmail = findViewById(R.id.etEmail);
    }

    public void ClickEvent(View view) {
        switch (view.getId()){
            case R.id.btnClear:
                mEtEmail.setText("");
                mEtName.setText("");
                break;

            case R.id.btnSave:
                save();
                break;

            case R.id.btnRetrieve:
                retrieve();
                break;
        }
    }

    private void retrieve() {
        sharedPreferences = getApplicationContext().getSharedPreferences("myData", 0);
        if (sharedPreferences.contains("name")){
            mEtName.setText(sharedPreferences.getString("name", null));
        }
        if (sharedPreferences.contains("email")){
            mEtEmail.setText(sharedPreferences.getString("email", null));
        }
    }

    private void save() {
        String name = mEtName.getText().toString().trim();
        String mail = mEtEmail.getText().toString().trim();

        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(mail)){
            Toast.makeText(this, "Name or Email is required !", Toast.LENGTH_SHORT).show();
        }else {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("name", name);
            editor.putString("email", mail);
            editor.commit();
        }
    }


}
