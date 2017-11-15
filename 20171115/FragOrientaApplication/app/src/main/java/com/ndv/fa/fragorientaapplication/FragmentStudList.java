package com.ndv.fa.fragorientaapplication;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by admin on 11/15/2017.
 */

public class FragmentStudList extends ListFragment {
    ArrayList<Student> students;
    StudentAdapter studentAdapter;

    CallBackInterface callBackInterface;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_stud, container, false);

        callBackInterface = (CallBackInterface) getActivity();

        students = StudentModel.getStudents();
        studentAdapter = new StudentAdapter(getActivity(), R.layout.view_stud, students);
        setListAdapter(studentAdapter);
        return view;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
//        Toast.makeText(getActivity(), students.get(position).getName(), Toast.LENGTH_SHORT).show();
        callBackInterface.getStudent(students.get(position));
    }
}

