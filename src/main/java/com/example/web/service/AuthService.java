package com.example.web.service;


import com.example.web.dao.UserDao;
import com.example.web.dao.model.User;

import java.sql.SQLException;

public class AuthService {
    public boolean checkLogin(String uname, String pass) throws SQLException {
        UserDao udao= new UserDao();
        User u = udao.findUsername(uname);
        if(u==null)  return false;
        return pass.equals(u.getPassword());
    }
}
