package com.example.web.dao;

import com.example.web.dao.db.DbConnect;
import com.example.web.dao.model.User;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.net.PasswordAuthentication;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;


public class UserDao {
    Connection conn = DbConnect.getConnection();

    private Connection getConnection() throws SQLException {
        return DbConnect.getConnection();
    }
    public UserDao() {
        conn = DbConnect.getConnection();
    }

    public User findByUsername(String username) throws SQLException {
        String sql = "SELECT * FROM users WHERE username = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, username);
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                int id = rs.getInt("id");
                String fullName = rs.getString("fullName");
                String uname = rs.getString("username");
                String address = rs.getString("address");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                User.Role role = User.Role.valueOf(rs.getString("role"));

                return new User(id, fullName, uname, address, email, phone, role);
            }

        }
        return null;
    }

    public User findByEmail(String email) throws SQLException {
        String sql = "SELECT * FROM users WHERE email = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, email);
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                int id = rs.getInt("id");
                String fullName = rs.getString("fullName");
                String uname = rs.getString("username");
                String address = rs.getString("address");
                String uemail = rs.getString("email");
                String phone = rs.getString("phone");
                User.Role role = User.Role.valueOf(rs.getString("role"));

                return new User(id, fullName, uname, address, uemail, phone, role);
            }

        }
        return null;
    }

    //check login
    public User checkLogin(String username, String password) throws SQLException {
        String hashPass = hashPassword(password);
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, username);
        ps.setString(2, hashPass);
        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String fullName = rs.getString("fullName");
                String uname = rs.getString("username");
                String address = rs.getString("address");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                User.Role role = User.Role.valueOf(rs.getString("role"));
                return new User(id, fullName, uname, address, email, phone, role);
            }
        }
        return null;

    }

    public boolean registerUser(String fullName, String username, String password, String address, String email, String phone, String role) throws SQLException {
        if (findByUsername(username) != null) {
            return false;
        }

        String hashedPassword = hashPassword(password);
        String sql = "INSERT INTO users (fullName, username, password, address, email, phone, role) VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, fullName);
        ps.setString(2, username);
        ps.setString(3, hashedPassword);
        ps.setString(4, address);
        ps.setString(5, email);
        ps.setString(6, phone);
        ps.setString(7, role);

        return ps.executeUpdate() > 0;

    }

    public String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hash = md.digest(password.getBytes());

            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            return hexString.toString(); // Trả về chuỗi mã hóa MD5
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error while hashing password with MD5", e);
        }
    }

    public static void main(String[] args) throws SQLException {
        UserDao userDao = new UserDao();
        System.out.println(userDao.checkLogin("hieuhieu", "462004"));
    }
    public boolean updatePassword(String username, String newPassword) throws SQLException {
        String sql = "UPDATE users SET password = ? WHERE username = ?";
        String hashedPassword = hashPassword(newPassword); // Mã hóa mật khẩu mới
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, hashedPassword);
            ps.setString(2, username);
            return ps.executeUpdate() > 0;
        }
    }
    public String getPasswordByUsername(String username) throws SQLException {
        String sql = "SELECT password FROM users WHERE username = ?";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("password");
                }
            }
        }
        return null; // Không tìm thấy mật khẩu
    }
    public static boolean sendMail(String to, String subject, String text) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            @Override
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("shopsand22@gmail.com", "shopsand22@");
            }
        });
        try {
            Message message = new MimeMessage(session);
            message.setHeader("Content-Type", "text/plain; charset=UTF-8");
            message.setFrom(new InternetAddress("shopsand22@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);
            message.setText(text);
            Transport.send(message);
        } catch (MessagingException e) {
            return false;
        }
        return true;
    }
    public boolean passwordRecovery(String username, String email) throws SQLException {
        User user = findByEmail(email);
        if (user != null) {
            sendMail(email, "Password recovery", getPasswordByUsername(username));
            return true;
        }
        return false;
    }
}
