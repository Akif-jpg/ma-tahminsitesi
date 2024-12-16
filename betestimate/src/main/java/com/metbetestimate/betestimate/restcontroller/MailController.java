package com.metbetestimate.betestimate.restcontroller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.metbetestimate.betestimate.models.MailModel;
import com.metbetestimate.betestimate.repositories.MailModelJdbcRepository;
import com.metbetestimate.betestimate.repositories.Repository;

@RestController
@RequestMapping(value = "/api")
public class MailController {

    private Repository<MailModel> repository;

    public MailController(MailModelJdbcRepository mailRepository) {
        this.repository = mailRepository;
    }

    @GetMapping(value = "/sec/listofallmails")
    @ResponseBody
    public List<MailModel> listofMailModels(){
        return repository.List();
    }

    @PostMapping(value = "createMail")
    public void createMail(@RequestBody MailModel model){
        repository.create(model);
    }

    @PostMapping(value = "/sec/updateMail/{id}")
    public void updateMail(@PathVariable int id){
        repository.update((MailModel) repository.get(id),id);
    }

    @PostMapping(value = "/sec/deleteMail/{id}")
    public void deleteMail(@PathVariable int id){
        repository.delete(id);
    }


    
}
