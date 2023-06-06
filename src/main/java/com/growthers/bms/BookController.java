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

    private static final Book BookID = null;
    @Autowired
    private BmsRepository bmsRepository;
    
    @GetMapping("find") 
    private String find(Model model, SearchForm form) {
        model.addAttribute("books", new ArrayList<Book>());
        model.addAttribute("searchForm", form);
        return "book/find";
    }

    @GetMapping("rentalList") 
    private String rentalList(Model model) {
        return "book/rentalList";
    }

    @PostMapping("search")
    private String search(Model model, @Validated SearchForm form, BindingResult result) {
        if (result.hasErrors()) {
            model.addAttribute("books", new ArrayList<Book>());
        }
        else {
            ArrayList<Book> books = bmsRepository.search(form);
            model.addAttribute("books",books);
        }
        model.addAttribute("searchForm", form);
        return "book/find";
    }
    @PostMapping("entry")
    private String entry(Model model, CandidateForm form, BindingResult result) {
        int bookID = form.getBookID();
        String username = "test";
        bmsRepository.regist(BookID, username);
        return "book/entry";
    }
    @GetMapping("{bookID}/view")
    private String view(@PathVariable("bookID") int bookID, Model model, CandidateForm form) {
        Book book = bmsRepository.findByBookID(bookID);
        model.addAttribute("Book", book);
        model.addAttribute("CandidateForm", form);
        return "book/view";
    }


}
