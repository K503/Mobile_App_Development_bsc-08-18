package com.example.classattendance;

public class MyClasses {
    private String courseName;
    private String courseCode;
    private long class_id;

    public long getClass_id() {
        return class_id;
    }

    public void setClass_id(long class_id) {
        this.class_id = class_id;
    }

    public MyClasses(String courseName, String courseCode, long class_id) {
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.class_id = class_id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }



    public MyClasses(String courseName, String courseCode) {
        this.courseName = courseName;
        this.courseCode = courseCode;
    }
}
