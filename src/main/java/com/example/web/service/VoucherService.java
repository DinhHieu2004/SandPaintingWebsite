package com.example.web.service;

import com.example.web.dao.VoucherDao;
import com.example.web.dao.model.Voucher;

import java.sql.SQLException;
import java.util.*;

public class VoucherService {
    private VoucherDao voucherDao = new VoucherDao();

    public List<Voucher> getAll() throws SQLException {
        return  voucherDao.getAll();
    }

    public Voucher getVoucherById(String vid) throws SQLException {
        return voucherDao.getVoucherById(vid);
    }
}
