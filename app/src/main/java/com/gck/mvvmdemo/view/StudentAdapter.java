package com.gck.mvvmdemo.view;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.gck.mvvmdemo.R;
import com.gck.mvvmdemo.databinding.ListItemsBinding;
import com.gck.mvvmdemo.model.Student;

import java.util.List;

public class StudentAdapter extends BaseAdapter {
    private Context context;
    private List<Student> students;
    private ItemClickCallback callback;

    public StudentAdapter(Context context, List<Student> students, ItemClickCallback callback) {
        this.context = context;
        this.students = students;
        this.callback = callback;
    }

    @Override
    public int getCount() {
        return students.size();
    }

    @Override
    public Object getItem(int i) {
        return students.get(i);
    }

    @Override
    public long getItemId(int i) {
        return students.get(i).getId();
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ListItemsBinding binding;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.list_items, null);
            binding = DataBindingUtil.bind(view);
            view.setTag(binding);
        } else {
            binding = (ListItemsBinding) view.getTag();
        }

        binding.setStudent(students.get(position));
        binding.setCallback(callback);
        return binding.getRoot();
    }

    public void addData(List<Student> student) {
        this.students.addAll(student);
        notifyDataSetChanged();
    }

}
