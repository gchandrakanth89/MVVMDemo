package com.gck.mvvmdemo;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.gck.mvvmdemo.databinding.ActivityStudentDetailBinding;
import com.gck.mvvmdemo.model.Student;
import com.gck.mvvmdemo.viewmodel.StudentDetailedViewModel;

public class StudentDetailsActivity extends AppCompatActivity {

    private Student student;
    private ActivityStudentDetailBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_student_detail);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_student_detail);
        student = (Student) getIntent().getExtras().get("student");

        StudentDetailedViewModel.Factory factory = new StudentDetailedViewModel.Factory(getApplication(), student.getId());

        final StudentDetailedViewModel detailedViewModel = ViewModelProviders.of(this, factory).get(StudentDetailedViewModel.class);

        binding.setStudentModel(detailedViewModel);
        binding.setIsLoading(true);

        detailedViewModel.getStudentData().observe(this, new Observer<Student>() {
            @Override
            public void onChanged(@Nullable Student student) {
                binding.setIsLoading(false);
                detailedViewModel.setStudentData(student);
            }
        });



    }

    private void init() {
        TextView textView = findViewById(R.id.tv_id);
        textView.setText(student.getId() + "");

        textView = findViewById(R.id.tv_name);
        textView.setText(student.getName());

        textView = findViewById(R.id.tv_percent);
        textView.setText(student.getPercent() + "");
    }
}
