package com.gck.mvvmdemo.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.gck.mvvmdemo.model.Student;
import com.gck.mvvmdemo.repo.StudentRepository;

import java.util.List;

public class StudentListViewModel extends AndroidViewModel {

    private final LiveData<List<Student>> data;

    public StudentListViewModel(@NonNull Application application) {
        super(application);
        data = StudentRepository.getInstance().getLiveData();
    }

    public LiveData<List<Student>> getStudentData(){
        return data;
    }

}
