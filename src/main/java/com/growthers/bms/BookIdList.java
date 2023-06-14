package com.growthers.bms;

import jakarta.validation.constraints.NotEmpty;

public class BookIdList {        //チェックボックスを管理するクラスの作成
    @NotEmpty
    private int[] selectedBooks;

    public int[] getSelectedBooks() {return selectedBooks;}
    public void setSelectedBooks(int[] sb) {selectedBooks = sb;}
}
