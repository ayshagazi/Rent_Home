package Model;

public class HomeInFeedModel {
    String Publisher, contactNo, date, description, homeName, image, localArea, pld, rentCost, room, time;

    public HomeInFeedModel() {
    }

    public HomeInFeedModel(String publisher, String contactNo, String date, String description, String homeName, String image, String localArea, String pld, String rentCost, String room, String time) {
        Publisher = publisher;
        this.contactNo = contactNo;
        this.date = date;
        this.description = description;
        this.homeName = homeName;
        this.image = image;
        this.localArea = localArea;
        this.pld = pld;
        this.rentCost = rentCost;
        this.room = room;
        this.time = time;
    }

    public String getPublisher() {
        return Publisher;
    }

    public void setPublisher(String publisher) {
        Publisher = publisher;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHomeName() {
        return homeName;
    }

    public void setHomeName(String homeName) {
        this.homeName = homeName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLocalArea() {
        return localArea;
    }

    public void setLocalArea(String localArea) {
        this.localArea = localArea;
    }

    public String getPld() {
        return pld;
    }

    public void setPld(String pld) {
        this.pld = pld;
    }

    public String getRentCost() {
        return rentCost;
    }

    public void setRentCost(String rentCost) {
        this.rentCost = rentCost;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
