package com.ndv.cta.contacsapplication;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by admin on 11/7/2017.
 */

public class ContactAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<Contact> contactList;

    public ContactAdapter(Context context, int layout, List<Contact> contactList) {
        this.context = context;
        this.layout = layout;
        this.contactList = contactList;
    }

    @Override
    public int getCount() {
        return contactList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    private class ViewHolder {
        ImageView imgAvatar;
        TextView txtName, txtPhone;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;

        if (view == null){
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(layout, null);
            viewHolder = new ViewHolder();

            viewHolder.txtName = view.findViewById(R.id.txtName);
            viewHolder.txtPhone = view.findViewById(R.id.txtPhone);
            viewHolder.imgAvatar = view.findViewById(R.id.imgAvatar);

            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }

        Contact contact = contactList.get(i);
        viewHolder.txtName.setText(contact.getName());
        viewHolder.txtPhone.setText(String.valueOf(contact.getPhone()));
        viewHolder.imgAvatar.setImageResource(contact.getAvatar());

        return view;
    }
}
