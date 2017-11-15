package com.ndv.cta.contacsapplication;

import android.Manifest;
import android.app.Dialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity {

    ListView listviewContact;
    ArrayList<Contact> contacts;
    ContactAdapter contactAdapter;
    int CODE_ADD = 2292,
            CODE_EDIT = 2922;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setVariable();
        contactAdapter = new ContactAdapter(this, R.layout.item_list_contact, contacts);
        listviewContact.setAdapter(contactAdapter);

        listviewContact.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(final AdapterView<?> adapterView, View view, final int i, long l) {
                Bundle bundle = new Bundle();
//                bundle.putInt("id", i);
                bundle.putSerializable("info", contacts.get(i));

                ViewContactActivity viewContactActivity = new ViewContactActivity();
                viewContactActivity.setOnCallback(new ICallBack() {
                    @Override
                    public void callback(Contact contact) {
                        contacts.set(i,contact);
                        contactAdapter.notifyDataSetChanged();
                    }
                });

                Intent intent = new Intent(MainActivity.this, viewContactActivity.getClass());
                intent.putExtra("data", bundle);
                //startActivityForResult(intent, CODE_EDIT);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_enter, R.anim.anim_exit);

            }
        });

        listviewContact.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                showDialogConfirm(i);
                return false;
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == CODE_ADD && resultCode == RESULT_OK && data != null) {
            Bundle bundle = data.getBundleExtra("addData");
            String addName = bundle.getString("addName");
            String addPhone = bundle.getString("addPhone");
            Boolean addGender = bundle.getBoolean("addGender", true);

            contacts.add(new Contact(addName, addPhone, R.drawable.avt, addGender));
            sortContacts();
            contactAdapter.notifyDataSetChanged();
        }

//        if (requestCode == CODE_EDIT && resultCode == RESULT_OK && data != null) {
//            Bundle bundle = data.getBundleExtra("editData");
//            int id = bundle.getInt("id");
//            String editName = bundle.getString("editName");
//            String editPhone = bundle.getString("editPhone");
//            Boolean editGender = bundle.getBoolean("editGender", true);
//
//            contacts.get(id).setName(editName);
//            contacts.get(id).setPhone(editPhone);
//            contacts.get(id).setSex(editGender);
//            contactAdapter.notifyDataSetChanged();
//        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    private void setVariable() {
        listviewContact = findViewById(R.id.listviewContact);
        contacts = new ArrayList<>();

        contacts.add(new Contact("Vinh.nd", "01644846865", R.drawable.avt, true));
        contacts.add(new Contact("Thang.lb", "0123456789", R.drawable.avt, true));
        contacts.add(new Contact("Xuan.th", "0907887802", R.drawable.avt, true));
        contacts.add(new Contact("Ngoc.tq", "0123456789", R.drawable.avt, true));
        contacts.add(new Contact("Anh.ln", "01634067196", R.drawable.avt, false));
        contacts.add(new Contact("Hoang.ph", "0123456789", R.drawable.avt, true));
        contacts.add(new Contact("Hoang.mn", "01287178414", R.drawable.avt, true));
        contacts.add(new Contact("Nhat.nb", "0123456789", R.drawable.avt, true));
        contacts.add(new Contact("Tam.tv", "0123456789", R.drawable.avt, true));
        contacts.add(new Contact("Thuong.lv", "0935579194", R.drawable.avt, true));
        contacts.add(new Contact("Nguyen.pv", "0985172011", R.drawable.avt, true));
        contacts.add(new Contact("Thao.ntt", "0123456789", R.drawable.avt, false));
        contacts.add(new Contact("Phat.vt", "01683725866", R.drawable.avt, true));
        contacts.add(new Contact("Hai.tn", "0123456789", R.drawable.avt, true));

        sortContacts();
    }

    private void sortContacts() {
        Collections.sort(contacts, new Comparator<Contact>() {
            @Override
            public int compare(Contact contact, Contact t1) {
                return contact.getName().compareTo(t1.getName());
            }
        });
    }

    public void showDialogConfirm(int i) {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_call_sms);
        Button btnCall = dialog.findViewById(R.id.btnCall);
        Button btnSms = dialog.findViewById(R.id.btnSms);
        final String num = contacts.get(i).getPhone();
        dialog.show();

        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intentCall(num);
                dialog.cancel();
            }
        });
        btnSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intentSms(num);
                dialog.cancel();
            }
        });
    }

    private void intentSms(String phone) {
        Intent intentSms = new Intent();
        intentSms.setAction(Intent.ACTION_VIEW);
        intentSms.setData(Uri.parse("sms:"+phone));
        startActivity(intentSms);
    }

    private void intentCall(String phone) {
        Intent intentCall = new Intent();
        intentCall.setAction(Intent.ACTION_CALL);
        intentCall.setData(Uri.parse("tel:"+phone));
        startActivity(intentCall);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_contact, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.menuAddContact:
                Intent intent = new Intent(MainActivity.this, AddContactActivity.class);
                startActivityForResult(intent, CODE_ADD);
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }


}
