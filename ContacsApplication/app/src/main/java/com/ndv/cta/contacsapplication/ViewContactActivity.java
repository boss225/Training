package com.ndv.cta.contacsapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class ViewContactActivity extends AppCompatActivity {
    TextView tvName, tvPhone, tvSex;
    EditText editName, editPhone;
    RadioButton radioBtnMale, radioBtnFemale;
    Button btnEdit;
    int id;

    private static ICallBack mICallBack;

    public void setOnCallback(ICallBack iCallBack){
        mICallBack = iCallBack;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_contact);

        mapping();
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("data");
        if (bundle != null) {
//            id = bundle.getInt("id");
            Contact contact = (Contact) bundle.getSerializable("info");

            tvName.setText(contact.getName());
            tvPhone.setText(contact.getPhone());
            tvSex.setText(contact.isSex() == true ? "Male" : "Female");
            editName.setText(contact.getName());
            editPhone.setText(contact.getPhone());
            radioBtnMale.setChecked(contact.isSex() == true ? true : false);
            radioBtnFemale.setChecked(contact.isSex() == false ? true : false);
        }

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editName.getText().toString().trim();
                String phone = editPhone.getText().toString().trim();
                Boolean gender = (radioBtnMale.isChecked() == true ? true : false);

                if (TextUtils.isEmpty(name) || TextUtils.isEmpty(phone)){
                    Toast.makeText(ViewContactActivity.this, "Name or Phone empty !", Toast.LENGTH_SHORT).show();
                } else {
                    if (mICallBack != null){
                        Contact contact = new Contact(name,phone,R.drawable.avt,gender);
                        mICallBack.callback(contact);
                        finish();
                    }
//                    Bundle bundle = new Bundle();
//                    bundle.putInt("id", id);
//                    bundle.putString("editName", name);
//                    bundle.putString("editPhone", phone);
//                    bundle.putBoolean("editGender", gender);
//
//                    Intent intent = new Intent();
//                    intent.putExtra("editData", bundle);
//                    setResult(RESULT_OK, intent);
//                    finish();
                }
            }
        });
    }

    private void mapping() {
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        tvName = findViewById(R.id.tvName);
        tvPhone = findViewById(R.id.tvPhone);
        tvSex = findViewById(R.id.tvSex);
        editName = findViewById(R.id.editName);
        editPhone = findViewById(R.id.editPhone);
        radioBtnMale = findViewById(R.id.radioBtnMale);
        radioBtnFemale = findViewById(R.id.radioBtnFemale);
        btnEdit = findViewById(R.id.btnEdit);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
                finish();
                overridePendingTransition(R.anim.anim_enter, R.anim.anim_exit);
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }
}
