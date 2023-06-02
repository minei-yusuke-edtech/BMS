package com.growthers.bms;

import jakarta.validation.constraints.NotBlank;

public class SearchForm {
    @NotBlank
    private String book = null;
    private String classCode = null;
    private String author = null;
    private String publisher = null;
    private String ISBN = null;


    public String getBook() {return book;}
    public void setBook(String bk) {book = bk;}

    public String getClassCode() {return classCode;}
    public void setClassCode(String code) {classCode = code;}

    public String getAuthor() {return author;}
    public void setAuthor(String at) {author = at;}

    public String getPublisher() {return publisher;}
    public void setPublisher(String ps) {publisher = ps;}

    public String getISBN() {return ISBN;}
    public void setISBN(String IB) {ISBN = IB;}
}
