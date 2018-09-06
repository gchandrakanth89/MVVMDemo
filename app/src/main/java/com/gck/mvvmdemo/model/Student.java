package com.gck.mvvmdemo.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Student implements Parcelable{
    private int id;
    private String name;
    private float percent;

    public Student(int id, String name, float percent) {
        this.id = id;
        this.name = name;
        this.percent = percent;
    }

    public Student(Parcel parcel) {
        this.id = parcel.readInt();
        this.name = parcel.readString();
        this.percent = parcel.readFloat();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public float getPercent() {
        return percent;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeFloat(percent);
    }

    public static final Parcelable.Creator<Student> CREATOR = new Parcelable.Creator<Student>(){

        @Override
        public Student createFromParcel(Parcel parcel) {
            return new Student(parcel);
        }

        @Override
        public Student[] newArray(int i) {
            return new Student[i];
        }
    };
}
