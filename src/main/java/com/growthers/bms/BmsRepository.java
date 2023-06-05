package com.growthers.bms;

import java.util.ArrayList;

interface BmsRepository {
    ArrayList<Book> search(SearchForm form);
}
