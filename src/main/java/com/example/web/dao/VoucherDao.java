package com.example.web.dao;

import com.example.web.dao.db.DbConnect;
import com.example.web.dao.model.Voucher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VoucherDao {
    private Connection conn = DbConnect.getConnection();

    public List<Voucher> getAll() throws SQLException {
        List<Voucher> list = new ArrayList<Voucher>();
        String sql = "select * from vouchers";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Voucher voucher = new Voucher();
            voucher.setId(rs.getInt("id"));
            voucher.setName(rs.getString("name"));
            voucher.setDiscount(rs.getDouble("discount"));
            voucher.setActive(rs.getBoolean("is_active"));
            voucher.setCreateAt(rs.getDate("created_at"));
            list.add(voucher);
        }
        return list;

    }
    public Voucher getVoucherById(String id) throws SQLException {
        String sql = "select * from vouchers where id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            Voucher voucher = new Voucher();
            voucher.setId(rs.getInt("id"));
            voucher.setName(rs.getString("name"));
            voucher.setDiscount(rs.getDouble("discount"));
            voucher.setActive(rs.getBoolean("is_active"));
            voucher.setCreateAt(rs.getDate("created_at"));
            return voucher;
        }
        return null;
    }

    public static void main(String[] args) throws SQLException {
        VoucherDao dao = new VoucherDao();
        System.out.println(dao.getVoucherById("2"));
    }

}
