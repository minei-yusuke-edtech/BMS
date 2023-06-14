package com.growthers.bms;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RootController {
    @GetMapping("/") 
    private String index() {
        return "redirect:guest/myPage";
    }
}
