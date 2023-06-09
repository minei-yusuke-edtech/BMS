package com.growthers.bms;

import java.util.ArrayList;

interface BmsRepository {
    ArrayList<Book> search(SearchForm form);
    Book findByBookID(int id);
    void regist(int bookID, String username);
    ArrayList<Book> rentbook(String username);            //貸出中図書をDBから持ってくる用のメソッド
    ArrayList<Book> rentCandidate(String username);       //貸出候補図書をDBから持ってくる用のメソッド

    // 本を返却する処理(貸出管理テーブルの貸出中の列を返却済に変更する)
    void returnBooks(String username, int[] bookidlist);

    // 本を借りる処理(貸出候補の図書を貸出中に変更する)
    void rentBooks(String username, int[] bookidlist);

    // 貸出候補図書をキャンセルする処理(貸出候補の行をdeleteする)
    void cancelBooks(String username, int[] candidateBookidlist);

    String bookstatus(int bookid);
}
