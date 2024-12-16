package com.metbetestimate.betestimate.webcontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ApplicationUserController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String homePage(){
        return "index";
    }
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String homePage2(){
        return "index";
    }
     @RequestMapping(value = "/AnaSayfa", method = RequestMethod.GET)
    public String homePage3(){
        return "index";
    }
    @RequestMapping(value = "/hakkimizda", method = RequestMethod.GET)
    public String aboutUs(){
        return "aboutUs";
    }

    @RequestMapping(value = "/iletisim", method = RequestMethod.GET)
    public String communication(){
        return "communication";
    }

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public String page(){
        return "page";
    }
}
