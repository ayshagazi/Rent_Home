package Model;

public class Renters {

     String Address, ContactNo, Name, State;

    public Renters(String address) {
        Address = address;
    }

    public Renters() {
    }

    public Renters(String address, String contactNo, String name, String state) {
        Address = address;
        ContactNo = contactNo;
        Name = name;
        State = state;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getContactNo() {
        return ContactNo;
    }

    public void setContactNo(String contactNo) {
        ContactNo = contactNo;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }
}
