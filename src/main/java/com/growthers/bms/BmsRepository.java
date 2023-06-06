package com.growthers.bms;

import java.util.ArrayList;

interface BmsRepository {
    ArrayList<Book> search(SearchForm form);
    Book findByBookID(int id);
    void regist(Book BookId, String username);
    void rentbook(Book book);

}
