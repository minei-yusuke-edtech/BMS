package com.growthers.bms;

import java.sql.Date;

public class RentalList {     //貸出管理テーブル用のクラス作成
    private String username;
    private int bookID;
    private Date rentDate;
    private Date returnDate;
    private String rentStatus;
    public String getUsername() {
        return username;
    }
    public void setUserID(String username) {
        this.username = username;
    }
    public int getBookID() {
        return bookID;
    }
    
    public void setBookID(int bookID) {
        this.bookID = bookID;
    }
    public Date getRentDate() {
        return rentDate;
    }
    public void setRentDate(Date rentDate) {
        this.rentDate = rentDate;
    }
    public Date getReturnDate() {
        return returnDate;
    }
    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }
    public String getRentStatus() {
        return rentStatus;
    }
    public void setRentStatus(String rentStatus) {
        this.rentStatus = rentStatus;
    }
    public RentalList(String username, int bookID, Date rentDate, Date returnDate, String rentStatus) {
        this.username = username;
        this.bookID = bookID;
        this.rentDate = rentDate;
        this.returnDate = returnDate;
        this.rentStatus = rentStatus;   //コンストラクタ生成
    }
}
