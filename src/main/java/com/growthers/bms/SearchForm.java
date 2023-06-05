package com.growthers.bms;

import jakarta.validation.constraints.NotBlank;

public class SearchForm {
    @NotBlank
    private String bookTitle = null;
    private String classCode = null;
    private String author = null;
    private String publisher = null;
    private String ISBN = null;


    public String getBookTitle() {return bookTitle;}
    public void setBookTitle(String bk) {bookTitle = bk;}

    public String getClassCode() {return classCode;}
    public void setClassCode(String code) {classCode = code;}

    public String getAuthor() {return author;}
    public void setAuthor(String at) {author = at;}

    public String getPublisher() {return publisher;}
    public void setPublisher(String ps) {publisher = ps;}

    public String getISBN() {return ISBN;}
    public void setISBN(String IB) {ISBN = IB;}
}
