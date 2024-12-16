package com.metbetestimate.betestimate.webcontroller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Controller
@RequestMapping(value = "/sec")
public class ApplicationAdminController{

    private static final Logger log = LoggerFactory.getLogger(ApplicationAdminController.class);

    @GetMapping("admin")
    public String homePage(@CookieValue(name = "JSESSIONID") String session,Model model){
        log.info(session);
        model.addAttribute("sessionid", session);
        return "/admin/index";
    }

    @GetMapping("notifications")
    public String notifications(){
        return "/admin/notifications";
    }
}