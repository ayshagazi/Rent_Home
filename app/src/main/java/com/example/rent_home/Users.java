package com.example.rent_home;

public class Users {

    private String Email, Name, Username,id,Address,ImageUri;

    public Users(String email, String name, String username, String id, String address,String imageUri) {
        Email = email;
        Name = name;
        Username = username;
        this.id = id;
        Address= address;
        ImageUri= imageUri;
    }


    public Users() {

    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getImageUri() {
        return ImageUri;
    }

    public void setImageUri(String imageUri) {
        ImageUri = imageUri;
    }

    /* public String getEmail() {
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
    public String getImageUri(){return ImageUri;}
    */

}
