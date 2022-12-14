package com.example.classattendance;

public class Teacher {

    private int id;
    private String name;
    private String email;

    public Teacher() {
    }

    public Teacher(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }


    public static class Builder{
        private int id;
        private String name;
        private String email;


        public Builder id(int id){
            this.id=id;
            return this;
        }
        public Builder name(String name){
            this.name = name;
            return this;
        }

        public Teacher Build(){
            return new Teacher(id,name,email);
        }

        public void build() {
        }
    }
}
