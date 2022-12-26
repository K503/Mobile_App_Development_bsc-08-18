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
}

//public class MyClasses {
//    private String courseName;
//    private String courseCode;
//
//    public String getCourseName() {
//        return courseName;
//    }
//
//    public String getCourseCode() {
//        return courseCode;
//    }
//
//    public long getClass_id() {
//        return class_id;
//    }
//
//    private long class_id;
//
//    public MyClasses(String courseName, String courseCode, long class_id){
//        this.courseName = courseName;
//        this.courseCode = courseCode;
//        this.class_id = class_id;
//    }



//    public static class Builder{
//        private String courseName;
//        private String courseCode;
//        private long class_id;
//
//
//        public Builder(String courseName, String courseCode, long class_id) {
//            this.courseName = courseName;
//            this.courseCode = courseCode;
//            this.class_id = class_id;
//        }
//
//
//        public Builder courseName(String courseName){
//            this.courseName = courseName;
//            return this;
//        }
//
//        public Builder courseCode(String courseCode){
//            this.courseCode = courseCode;
//            return this;
//        }
//
//        public Builder class_id(long class_id){
//            this.class_id = class_id;
//            return this;
//        }
//
//        public MyClasses Build(){
//            return new MyClasses(courseName,courseCode,class_id);
//        }
//        public void build(){}
//    }
//
//}