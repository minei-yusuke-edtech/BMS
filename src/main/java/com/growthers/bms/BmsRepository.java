package com.growthers.bms;

import java.util.ArrayList;

interface BmsRepository {
    ArrayList<Book> search(SearchForm form);
    Book findByBookID(int id);
    void regist(int bookID, String username);
    void rentbook(String username);            //貸出中図書をDBから持ってくる用のメソッド
    void rentCandidate(String username);       //貸出候補図書をDBから持ってくる用のメソッド
}
