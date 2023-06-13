package com.growthers.bms;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@Controller
@RequestMapping("book")
public class BookController {

    @Autowired
    private BmsRepository bmsRepository;
    
    @GetMapping("find") 
    private String find(Model model, SearchForm form) {
        model.addAttribute("books", new ArrayList<Book>());
        model.addAttribute("searchForm", form);
        return "book/find";
    }

    @PostMapping("search")
    private String search(Model model, @Validated SearchForm form, BindingResult result) {
        if (result.hasErrors()) {
            model.addAttribute("books", new ArrayList<Book>());
        }
        else {
            
            ArrayList<Book> books = bmsRepository.search(form);
            for(Book book : books) {
                int bookid = book.getBookID();  
                String status = bmsRepository.bookstatus(bookid);
                book.setStatus(status);
            }
            model.addAttribute("books",books);
        }
        model.addAttribute("searchForm", form);
        return "book/find";
    }
    

    @PostMapping("entry")
    private String entry(Model model, CandidateForm form, BookIdList rendingBookID, BookIdList candidateBookID, BindingResult result) {
        int bookID = form.getBookID();
        // debug用
        String username = "test";
        
        bmsRepository.regist(bookID, username);

        // 貸出中の本の一覧を取ってくる
        ArrayList<Book> rendingBooks = bmsRepository.rentbook(username);
        model.addAttribute("rendingBooks", rendingBooks);
        // 貸出中図書のチェックボックスの値を受け取るclassを渡す
        model.addAttribute("rendingCheckbox", rendingBookID);

        // 貸出候補図書の一覧を取ってくる
        ArrayList<Book> rentCandidateBooks =  bmsRepository.rentCandidate(username);
        model.addAttribute("rentCandidateBooks", rentCandidateBooks);
        // 貸出候補図書のチェックボックス
        model.addAttribute("candidateCheckbox", candidateBookID);
        
        return "guest/rentalList";
    }
    @GetMapping("{bookID}/view")
    private String view(@PathVariable("bookID") int bookID, Model model, CandidateForm form) {
        Book book = bmsRepository.findByBookID(bookID);
        model.addAttribute("Book", book);
        model.addAttribute("CandidateForm", form);
        return "book/view";
    }

}
