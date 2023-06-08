package com.growthers.bms;



public class Book {
    // フィールド
    private int bookID;
    private String bookTitle;
    private String author;
    private String publisher;
    private int issue;
    private String version;
    private String isbn;
    private String classCode;
    private boolean enabled;
    
    // getter setterメソッド
    public int getBookID() {
        return bookID;
    }
    public void setBookID(int bookID) {
        this.bookID = bookID;
    }
    public String getBookTitle() {
        return bookTitle;
    }
    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public String getPublisher() {
        return publisher;
    }
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
    public int getIssue() {
        return issue;
    }
    public void setIssue(int issue) {
        this.issue = issue;
    }
    public String getVersion() {
        return version;
    }
    public void setVersion(String version) {
        this.version = version;
    }
    public String getIsbn() {
        return isbn;
    }
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    public String getClassCode() {
        return classCode;
    }
    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }
    public boolean isEnabled() {
        return enabled;
    }
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
    public Book(int bookID, String bookTitle, String author, String publisher, int issue, String version,
            String isbn, String classCode, boolean enabled) {
        this.bookID = bookID;
        this.bookTitle = bookTitle;
        this.author = author;
        this.publisher = publisher;
        this.issue = issue;
        this.version = version;
        this.isbn = isbn;
        this.classCode = classCode;
        this.enabled = enabled;
    }

}
