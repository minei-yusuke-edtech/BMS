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
@RequestMapping("guest")
public class GuestController {

    @Autowired
    BmsRepository bmsRepository;
    
    @GetMapping("myPage")
    private String profile(Model model) {
        return "guest/myPage";
    }

    @GetMapping("rentalList") 
    private String rentalList(Model model, BookIdList rendingBookID, BookIdList candidateBookID) {
        // debug用
        String username = "test";

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

    @PostMapping("return")
    public String returnBook(Model model, BookIdList bookidlist) {
        return "redirect:rentalList";
    }

    @PostMapping("rent")
    public String rent(Model model) {
        return "redirect:rentalList";
    }

    @PostMapping("cancel")
    public String cancel(Model model, BookIdList candidateBookIDList) {
        return "redirect:rentalList";
    }

}
