package com.gck.mvvmdemo.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import com.gck.mvvmdemo.model.Student;
import com.gck.mvvmdemo.repo.StudentRepository;

public class StudentDetailedViewModel extends AndroidViewModel {

    private final int id;
    private final LiveData<Student> studentData;
    public ObservableField<Student> student = new ObservableField<>();

    public StudentDetailedViewModel(@NonNull Application application, int id) {
        super(application);
        this.id = id;
        studentData = StudentRepository.getInstance().getStudentData(id);
    }

    public LiveData<Student> getStudentData() {
        return studentData;
    }

    public void setStudentData(Student student) {
        this.student.set(student);
    }


    public static class Factory extends ViewModelProvider.NewInstanceFactory{
        private final Application application;
        private final int id;

        public Factory(Application application, int id) {
            this.application = application;
            this.id = id;
        }

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            return (T) new StudentDetailedViewModel(application, id);
        }
    }

}
