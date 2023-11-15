package com.example.exercise1;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class HomeController {
    @GetMapping("/")
    public String index(){
        return "index";
    }
    @RequestMapping(value = "/contact", method = {RequestMethod.GET, RequestMethod.POST})
    public String contact(Model model, @RequestParam(required = false) String name, @RequestParam(required = false) String age) {
        if (name != null && age != null) {
            model.addAttribute("name", name);
            model.addAttribute("age", age);
            return "contact-success";
        }
        return "contact";
    }
    @GetMapping("/about")
    public String about(){
        return "about";
    }
}
