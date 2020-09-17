package Model;

public class HomeInFeedModel {
    String Publisher, contactNo, date, description, homeName, image, localArea,pId, rentCost, room, time, Punlisher;

    public HomeInFeedModel() {
    }

    public HomeInFeedModel(String publisher, String contactNo, String date, String description, String homeName, String image, String localArea, String pId, String rentCost, String room, String time , String Punlisher) {
        Publisher = publisher;
        this.contactNo = contactNo;
        this.date = date;
        this.description = description;
        this.homeName = homeName;
        this.image = image;
        this.localArea = localArea;
        this.pId = pId;
        this.rentCost = rentCost;
        this.room = room;
        this.time = time;
        this.Punlisher=Punlisher;
    }

    public String getPunlisher() {
        return Punlisher;
    }

    public void setPunlisher(String punlisher) {
        Punlisher = punlisher;
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

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
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
