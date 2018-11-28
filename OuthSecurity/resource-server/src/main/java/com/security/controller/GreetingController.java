package com.security.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    @RequestMapping(value = "/greetings", produces = "application/json")
    @ResponseBody
    public String greetings() {
    	
    	return "Welcome To Oauth";
    }
  
}