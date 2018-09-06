package com.gck.mvvmdemo.repo;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.os.Handler;
import android.os.Looper;

import com.gck.mvvmdemo.model.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentRepository {

    private static StudentRepository studentRepository;
    private List<Student> students;
    final Handler handler = new Handler(Looper.getMainLooper());

    private StudentRepository() {
        students = new ArrayList<>(10);
        for (int i = 0; i < 10; i++) {
            Student student = new Student(i, "Studetnt " + i, 90f);
            students.add(student);
        }
    }

    public static StudentRepository getInstance() {
        if (studentRepository == null) {
            studentRepository = new StudentRepository();
        }
        return studentRepository;
    }

    public List<Student> getStudentsData() {
        return students;
    }

    public LiveData<List<Student>> getLiveData() {
        final MutableLiveData<List<Student>> data = new MutableLiveData<>();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                data.setValue(students);
            }
        }, 5_000);
        return data;
    }

    public LiveData<Student> getStudentData(final int id) {
        final MutableLiveData<Student> studentMutableLiveData = new MutableLiveData<>();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Student student = students.get(id);
                studentMutableLiveData.setValue(student);
            }
        }, 5_000);
        return studentMutableLiveData;
    }
}
