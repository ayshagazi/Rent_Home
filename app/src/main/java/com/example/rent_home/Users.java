package com.example.rent_home;

public class Users {

    private String Email, Name, Username,id,Address,image,ContactNo,Number;



    public Users(String email, String name, String username, String id, String address, String image, String contactNo, String number) {
        Email = email;
        Name = name;
        Username = username;
        this.id = id;
        this.Address= address;
        this.image= image;
        this.ContactNo=contactNo;
        Number=number;
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
        this.Address = address;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String imageUri) {
        image = imageUri;
    }

    public String getContactNo() {
        return ContactNo;
    }

    public void setContactNo(String contactNo) {
        ContactNo = contactNo;
    }

    public String getNumber() {
        return Number;
    }

    public void setNumber(String number) {
        Number = number;
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
