package com.example.realtime;

public class student {
    String name;
    String email;
    public student(){
    }
    public student(String name,String email){
        this.name=name;
        this.email=email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
