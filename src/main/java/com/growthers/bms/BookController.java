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
    return "book/entry";
}

}
