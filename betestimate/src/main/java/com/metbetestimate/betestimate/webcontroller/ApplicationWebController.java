package com.metbetestimate.betestimate.webcontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ApplicationWebController {
    @GetMapping("/login")
    public String login(){
        return "login";
    }
 
    @GetMapping("logout")
    public String logout(){
        return "redirect:/logout";
    }
    @GetMapping("/sentSuccessfully")
    public String sendSuccessfully(){
        return "sentSuccessfully";
    }
}
