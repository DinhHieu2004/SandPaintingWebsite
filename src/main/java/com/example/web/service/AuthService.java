package com.example.web.service;


import com.example.web.dao.UserDao;
import com.example.web.dao.model.User;

public class AuthService {
    public boolean checkLogin(String uname, String pass){
        UserDao udao= new UserDao();
        User u = udao.findUsername(uname);
        if(u==null)  return false;
        return pass.equals(u.getPassword());
    }
}
