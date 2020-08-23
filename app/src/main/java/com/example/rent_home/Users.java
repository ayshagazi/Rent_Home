package com.example.rent_home;

public class Users {

    private String Email, Name, Username,id,Address;

    public Users(String email, String name, String username, String id, String address) {
        Email = email;
        Name = name;
        Username = username;
        this.id = id;
        Address= address;
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

    public String getAddress(){
        return Address;
    }
}
