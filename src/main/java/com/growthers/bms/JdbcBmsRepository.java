package com.growthers.bms;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

class BookRowMapper implements RowMapper<Book> {
    public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
        Book book = new Book(rs.getInt("bookID"), rs.getString("bookTitle"), rs.getString("author"),rs.getString("publisher"),rs.getInt("issue"),rs.getString("version"),rs.getString("isbn"),rs.getString("classCode"),rs.getBoolean("enabled"));
        return book;
    }
}
@Repository
public class JdbcBmsRepository implements BmsRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Override
    public ArrayList<Book> search(SearchForm form) {
      ArrayList<Book> books = (ArrayList<Book>) jdbcTemplate.query("SELECT bookID, bookTitle, author, publisher, issue, version, isbn, classCode, enabled FROM books WHERE bookTitle LIKE '%'||?||'%' and classCode LIKE '%'||?||'%' and author LIKE '%'||?||'%' and publisher LIKE '%'||?||'%' and ISBN LIKE '%'||?||'%'",
      new BookRowMapper(), form.getBookTitle(), form.getClassCode(), form.getAuthor(), form.getPublisher(), form.getISBN());
      return books;
    }

    @Override
    public Book findByBookID(int id) {
       ArrayList<Book> books = (ArrayList<Book>) jdbcTemplate.query("SELECT bookID, bookTitle, author, publisher, issue, version, isbn, classCode, enabled FROM books WHERE bookID = ?",
       new BookRowMapper(), id);
       Book book = (books.size() > 0) ? books.get(0) : null; 
       return book;
    }

    @Override
    public void regist(Book BookId, String username) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'regist'");
    }

    @Override
    public void rentbook(Book book) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'rentbook'");
    }

}
    


