package com.growthers.bms;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;


@Controller
@RequestMapping("guest")
public class GuestController {

    @Autowired
    BmsRepository bmsRepository;
    
    @GetMapping("myPage")
    private String profile(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("username", auth.getName());

        return "guest/myPage";
    }

    @GetMapping("rentalList") 
    private String rentalList(Model model, BookIdList rendingBookID, BookIdList candidateBookID, @ModelAttribute("message") String message) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

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
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        bmsRepository.returnBooks(username, bookidlist.getSelectedBooks());
        return "redirect:rentalList";
    }

    @PostMapping("rent")
    public String rent(RedirectAttributes redirectAttributes, BookIdList candidateBookIDList) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        int RENT_LIMIT = 5;
        int status = bmsRepository.rentBooks(username, candidateBookIDList.getSelectedBooks(), RENT_LIMIT);
        switch (status) {
            case 1:
                redirectAttributes.addFlashAttribute("message", String.format("貸出は%d冊までです。", RENT_LIMIT));
                break;
            case 2:
                redirectAttributes.addFlashAttribute("message", "同じ日に同じ本を借りることはできません。");
                break;
            case 3:
                redirectAttributes.addFlashAttribute("message", "貸出中、若しくは無効な本です。");
                break;
        }
        return "redirect:rentalList";
    }

    @PostMapping("cancel")
    public String cancel(Model model, BookIdList candidateBookIDList) {
        // debug用
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        bmsRepository.cancelBooks(username, candidateBookIDList.getSelectedBooks());
        return "redirect:rentalList";
    }

}
