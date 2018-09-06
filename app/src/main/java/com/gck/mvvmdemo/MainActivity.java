package com.gck.mvvmdemo;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.gck.mvvmdemo.databinding.MainActivityBinding;
import com.gck.mvvmdemo.model.Student;
import com.gck.mvvmdemo.repo.StudentRepository;
import com.gck.mvvmdemo.view.ItemClickCallback;
import com.gck.mvvmdemo.view.StudentAdapter;
import com.gck.mvvmdemo.viewmodel.StudentListViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ItemClickCallback {

    private static final String TAG = "MainActivity";
    private ListView listView;
    private StudentAdapter studentAdapter;

    MainActivityBinding activityBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        activityBinding = DataBindingUtil.setContentView(this, R.layout.main_activity);

        init();
    }

    private void init() {
        studentAdapter = new StudentAdapter(this, new ArrayList<Student>(0), this);
        activityBinding.listView.setAdapter(studentAdapter);

        StudentListViewModel viewModel = ViewModelProviders.of(this).get(StudentListViewModel.class);
        viewModel.getStudentData().observe(this, new Observer<List<Student>>() {
            @Override
            public void onChanged(@Nullable List<Student> students) {
                studentAdapter.addData(students);
            }
        });

    }

    private void initViews() {
        listView = findViewById(R.id.list_view);
        studentAdapter = new StudentAdapter(this, StudentRepository.getInstance().getStudentsData(), this);
        listView.setAdapter(studentAdapter);
    }

    @Override
    public void onClick(Student student) {
        Log.d(TAG, "Chandu onClick: " + student.getId());
        Intent intent = new Intent(this, StudentDetailsActivity.class);
        intent.putExtra("student", student);
        startActivity(intent);
    }
}
