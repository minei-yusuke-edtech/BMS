package com.growthers.bms;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

class BookRowMapper implements RowMapper<Book> {          //sqlでbookから値を取得するためのクラス(インターフェース)
    public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
        Book book = new Book(rs.getInt("bookID"), rs.getString("bookTitle"), rs.getString("author"),rs.getString("publisher"),rs.getInt("issue"),rs.getString("version"),rs.getString("isbn"),rs.getString("classCode"),rs.getBoolean("enabled"));
        return book;
    }
}
class RentalListRowMapper implements RowMapper<RentalList> {  //sqlでrentalListから値を取得するためのクラス(インターフェース)
    public RentalList mapRow(ResultSet rs, int rowNum) throws SQLException {
        RentalList rentalinfo = new RentalList(rs.getString("username"), rs.getInt("bookID"), rs.getDate("rentDate"),rs.getDate("returnDate"),rs.getString("rentStatus"));
        return rentalinfo;
    }
}
@Repository 
public class JdbcBmsRepository implements BmsRepository {  //BmsRepositoryの実装クラスJdbcBmsRepositoryクラスを作成
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Override
    public ArrayList<Book> search(SearchForm form) {      //検索条件の問い合わせ
      ArrayList<Book> books = (ArrayList<Book>) jdbcTemplate.query   ("SELECT bookID, bookTitle, author, publisher, issue,    version, isbn, classCode, enabled FROM books WHERE bookTitle LIKE '%'||?||'%' and classCode LIKE '%'||?||'%' and author LIKE '%'||?||'%' and publisher LIKE '%'||?||'%' and ISBN LIKE '%'||?||'%'",
      new BookRowMapper(), form.getBookTitle(), form.getClassCode(), form.getAuthor(), form.getPublisher(), form.getISBN());
      return books;
    }

    @Override
    public Book findByBookID(int id) {    //図書IDをもとに検索結果の表示SELECT
       ArrayList<Book> books = (ArrayList<Book>) jdbcTemplate.query("SELECT bookID, bookTitle, author, publisher, issue, version, isbn, classCode, enabled FROM books WHERE bookID = ?",
       new BookRowMapper(), id);
       Book book = (books.size() > 0) ? books.get(0) : null; 
       return book;
    }

    @Override
    public void regist(int bookID, String username) {     //uesrnameとbookIDを使って貸出候補図書に登録する処理
    String rentStatus = "貸出候補";
     jdbcTemplate.update("INSERT INTO rentalList(username, bookID, rentStatus) VALUES(?, ?, ?)",username, bookID, rentStatus);
    }




    @Override
    public ArrayList<Book> rentbook(String username) {
        String rentStatus = "貸出中";
    //     ArrayList<RentalList> rentalLists = (ArrayList<RentalList>)
    //    jdbcTemplate.query("SELECT username, bookID, rentDate, returnDate, rentStatus FROM rentalList WHERE username = ", new RentalListRowMapper(), username, rentStatus);

       ArrayList<Book> books = new ArrayList<Book>();
       books.add(new Book(0, "えんぴつ", "佐々木のりこ", "うみかぜ文庫", 0, rentStatus, username, rentStatus, false));
       
       return books;
    }

    @Override
    public ArrayList<Book> rentCandidate(String username) {
        String rentStatus = "貸出候補";
        // ArrayList<RentalList> rentalLists = (ArrayList<RentalList>)
        // jdbcTemplate.query("SELECT username, bookID, rentDate, returnDate, rentStatus FROM rentalList WHERE rentStatus = ?", new RentalListRowMapper(), username);

        ArrayList<Book> books = new ArrayList<Book>();
        books.add(new Book(0, "ゾロリ", "原ゆたか", "青空文庫", 0, rentStatus, username, rentStatus, false));
        books.add(new Book(0, "ちびまる子ちゃん", "さくらももこ", "青空文庫", 0, rentStatus, username, rentStatus, false));
       
        return books;
    }

    @Override
    public void returnBooks(String username, int[] bookidlist) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'returnBooks'");
    }

    @Override
    public void rentBooks(String username, int[] bookidlist) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'rentBooks'");
    }

    @Override
    public void cancelBooks(String username, int[] candidateBookidlist) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'cancelBooks'");
    }
    

}
    


