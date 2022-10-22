package com.example.android_coursemanagement;

public class Courses {
    String course_name, course_what, course_why, course_creator, course_id;

    public Courses(String course_id, String course_name, String course_what, String course_why, String course_creator) {
        this.course_name = course_name;
        this.course_what = course_what;
        this.course_why = course_why;
        this.course_creator = course_creator;
        this.course_id = course_id;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public String getCourse_what() {
        return course_what;
    }

    public void setCourse_what(String course_what) {
        this.course_what = course_what;
    }

    public String getCourse_why() {
        return course_why;
    }

    public void setCourse_why(String course_why) {
        this.course_why = course_why;
    }

    public String getCourse_creator() {
        return course_creator;
    }

    public void setCourse_creator(String course_creator) {
        this.course_creator = course_creator;
    }

    public String getCourse_id() {
        return course_id;
    }

    public void setCourse_id(String course_id) {
        this.course_id = course_id;
    }
}
