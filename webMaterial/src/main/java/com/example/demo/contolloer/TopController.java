package com.example.demo.contolloer;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TopController {

    @GetMapping("top")
    public String showMain(Model model) {
        return "/top.html";
    }
}
