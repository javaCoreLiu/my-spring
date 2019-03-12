package com.bootstrap.controller;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

import com.bootstrap.annotation.Autowired;
import com.bootstrap.annotation.Controller;
import com.bootstrap.annotation.RequestMapping;
import com.bootstrap.annotation.RequestParam;
import com.bootstrap.service.UserService;

@Controller("userController")
@RequestMapping("/user")
public class UserController {
    
    @Autowired("userService")
    private UserService userService;
    
    @RequestMapping("/getUserName")
    public void getUserName(@RequestParam("userName") String userName) {
        PrintWriter pw;
        try {
            pw = new PrintWriter(userName);
            pw.println();
            pw.flush();
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        
    }
    
}
