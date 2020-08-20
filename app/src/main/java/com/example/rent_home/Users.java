package com.example.rent_home;

public class Users {

    private String Email, Name, Username,id;

    public Users(String email, String name, String username, String id) {
        Email = email;
        Name = name;
        Username = username;
        this.id = id;
    }


    public Users() {

    }

    public String getEmail() {
        return Email;
    }

    public String getName() {
        return Name;
    }

    public String getUsername() {
        return Username;
    }

    public String getId() {
        return id;
    }
}
