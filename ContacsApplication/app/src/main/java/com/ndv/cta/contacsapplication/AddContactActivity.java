package com.ndv.cta.contacsapplication;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class AddContactActivity extends Activity {

    EditText addName, addPhone;
    RadioButton addRadioMale, addRadioFemale;
    Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        mapping();

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = addName.getText().toString().trim();
                String phone = addPhone.getText().toString().trim();
                Boolean gender = (addRadioMale.isChecked() == true ? true : false);

                if (TextUtils.isEmpty(name) || TextUtils.isEmpty(phone)){
                    Toast.makeText(AddContactActivity.this, "Name or Phone empty !", Toast.LENGTH_SHORT).show();
                } else {
                    Bundle bundle = new Bundle();
                    bundle.putString("addName", name);
                    bundle.putString("addPhone", phone);
                    bundle.putBoolean("addGender", gender);

                    Intent intent = new Intent();
                    intent.putExtra("addData", bundle);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });
    }

    private void mapping() {
        addName = findViewById(R.id.addName);
        addPhone = findViewById(R.id.addPhone);
        addRadioMale = findViewById(R.id.addRadioMale);
        addRadioFemale = findViewById(R.id.addRadioFemale);
        btnAdd = findViewById(R.id.btnAdd);

    }
}
