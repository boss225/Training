package com.ndv.fa.fragorientaapplication;

import java.util.ArrayList;

/**
 * Created by admin on 11/15/2017.
 */

public class StudentModel {
    public static String[] listName = new String[] {"Nguyen Van A","Nguyen Van B","Nguyen Van C","Nguyen Van D","Nguyen Van E","Nguyen Van F"};
    public static int[] listYear = new int[] {1999,1998,1997,1996,1995,1994};
    public static String[] listAddr = new String[] {"Ha Noi","Thanh Hoa","Vinh","Da Nang","Nha Trang","Ho Chi Minh"};
    public static String[] listEmail = new String[] {"vana@gmail.com","vanb@gmail.com","vanc@gmail.com","vand@gmail.com","vane@gmail.com","vanf@gmail.com"};

    private static ArrayList<Student> students;

    public static ArrayList<Student> getStudents(){
        if (students == null){
            StudentModel studentModel = new StudentModel();
            studentModel.initStudentList();
        }
        return students;
    }

    private void initStudentList() {
        students = new ArrayList<Student>();
        for (int i=0; i < listName.length; i++){
            Student student = new Student(listName[i],listYear[i],listAddr[i],listEmail[i]);
            students.add(student);
        }
    }
}
