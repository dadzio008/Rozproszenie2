package com.example.rozproszenie2.controller;

import com.example.rozproszenie2.interfaces.GenService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

@RestController
public class Controller {

    private GenService service;

    public Controller(@Qualifier("genServiceImpl") GenService service) {
        this.service = service;
    }

    @PostMapping("/findId")
    public ModelAndView findId(@RequestParam String id){
        String url = service.getUrl(Long.valueOf(id));
        return new ModelAndView("redirect:" + url);
    }

    @DeleteMapping("/byCreationDate")
    public HttpStatus deleteByLastUsageDate(@RequestParam Date date){
        return service.deleteUrlByCreationDate(date);
    }
}
