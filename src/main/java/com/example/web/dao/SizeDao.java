package com.example.web.dao;

import com.example.web.dao.db.DbConnect;
import com.example.web.dao.model.PaintingSize;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SizeDao {
    static Connection conn = DbConnect.getConnection();

    public SizeDao() {}

    public List<PaintingSize> getAllSizes() throws SQLException {
        List<PaintingSize> sizes = new ArrayList<PaintingSize>();
        String sql = "select * from sizes";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("id");
            String size = rs.getString("sizeDescription");
            PaintingSize siz = new PaintingSize(id, size);
            sizes.add(siz);
        }
        return sizes;
    }
    public static PaintingSize getSizeById(int idSize) {
        PaintingSize size = null;
        try {
            String sql = "SELECT * FROM sizes WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, idSize);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                size = new PaintingSize();
                size.setIdSize(resultSet.getInt("id"));
                size.setSizeDescriptions(resultSet.getString("sizeDescription"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return size;
    }


}
