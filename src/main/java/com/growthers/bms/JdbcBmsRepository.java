package com.growthers.bms;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
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
//----------------------------------ここまでは大丈夫---------------------------------------------------------



    @Override
    public ArrayList<Book> rentbook(String username) {//dbから貸出中の本のデータを取り出す処理
        // String rentStatus = "貸出中";
        ArrayList<Book> books = new ArrayList<Book>();
        ArrayList<RentalList> rentbook = (ArrayList<RentalList>)
       jdbcTemplate.query("SELECT username, bookID, rentDate, returnDate, rentStatus FROM rentalList WHERE username = ? and  rentStatus = '貸出中'", new RentalListRowMapper(), username);
       
       //books.addAll();

       for(RentalList rent : rentbook){
        
            //findByBookID(rent.getBookID());
            books.add(findByBookID(rent.getBookID())); //findByBookで取ってきたデータをbooksに追加
       }                                                              
       

       return books;
    }


    @Override
    public ArrayList<Book> rentCandidate(String username) {//dbから貸出候補の本のデータを取り出す処理
        // String rentStatus = "貸出候補";
        ArrayList<Book> books = new ArrayList<Book>();
        ArrayList<RentalList> rentbook = (ArrayList<RentalList>)
        jdbcTemplate.query("SELECT username, bookID, rentDate, returnDate, rentStatus FROM rentalList WHERE username = ? and rentStatus ='貸出候補'", new RentalListRowMapper(), username);

        for(RentalList rent : rentbook){
            books.add(findByBookID(rent.getBookID()));
        }
       
        return books;
    }

    @Override//多分終わり
    public void returnBooks(String username, int[] bookidlist) {//貸出中の本を返却済みにする処理
        for(int bookid : bookidlist){
            if(checkCandidate(bookid) == true){
                jdbcTemplate.update("UPDATE rentalList SET rentStatus = '返却済', returnDate = current_date WHERE rentStatus = '貸出中' and bookid = ? and username = ?",bookid, username);  
            }
        }
        /*  ArrayList<Book> books = new ArrayList<Book>();
        jdbcTemplate.update("UPDATE rentalList SET rentStatus = '返却済' WHERE rentStatus = '貸出中' and bookid = ? and username = ?");  */
    }

    
    @Override //いったん終わり
    public void rentBooks(String username, int[] bookidlist) {//貸出候補の図書を貸出中に変更する処理
       //String rentStatus = "貸出中";
       //ArrayList<Book> books = new ArrayList<Book>();
       for (int bookid : bookidlist){
        if(checkrentBooks(bookid) == true){
       jdbcTemplate.update("UPDATE rentalList SET rentStatus = '貸出中', rentDate = current_date WHERE rentStatus = '貸出候補' and bookid = ? and username = ?", bookid, username);  
       }
   }
}

    

    @Override//いったん終わり
    public void cancelBooks(String username, int[] candidateBookidlist) {//貸出候補図書を取り消す処理
        for (int bookid : candidateBookidlist) {
            jdbcTemplate.update("DELETE FROM rentalList WHERE rentStatus = '貸出候補' and username = ? bookid = ?", username, bookid);
        }
       
    }
    //いったん終わり
    public boolean checkrentBooks(int bookid) {//本を貸出し出来るか確かめる処理 

        ArrayList<Book> checkEnabled = (ArrayList<Book>) //本が存在するか確かめる
        jdbcTemplate.query("SELECT bookid, booktitle, author, publisher, issue, version, isbn, classcode, enabled FROM book WHERE bookid = ? ", new BookRowMapper(), bookid);

        if(checkEnabled.get(0).isEnabled() == true ){//本が存在した場合の処理（貸出できるか確かめる）
            ArrayList<RentalList> checkbook = (ArrayList<RentalList>) 
            jdbcTemplate.query("SELECT  bookid, rentStatus  FROM rentalList WHERE bookid = ? and rentStatus = '貸出中'",new RentalListRowMapper(), bookid);
            return checkbook.size() == 0;
        }else{//存在しない場合はすぐに戻す
            return false;
        }
}

    public boolean checkCandidate(int bookid){

        ArrayList<Book> checkEnabled = (ArrayList<Book>) //本が存在するか確かめる
        jdbcTemplate.query("SELECT bookid, booktitle, author, publisher, issue, version, isbn, classcode, enabled FROM book WHERE bookid = ? ", new BookRowMapper(), bookid);

        if(checkEnabled.get(0).isEnabled() == true ){//本が存在した場合の処理（貸出できるか確かめる）(貸出候補の場合)
            ArrayList<RentalList> checkbook = (ArrayList<RentalList>) 
            jdbcTemplate.query("SELECT  bookid, rentStatus  FROM rentalList WHERE bookid = ? and( rentStatus = '貸出候補' or rentStatus = '返却済' )",new RentalListRowMapper(), bookid);
            return checkbook.size() == 0;
        }else{//存在しない場合はすぐに戻す
            return false;
        }
    }
}


