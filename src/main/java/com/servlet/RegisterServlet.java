package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private static final String query = "INSERT INTO BOOKDATA(BOOKNAME, BOOKEDITION, BOOKPRICE) VALUES(?, ?, ?)";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        PrintWriter pw = res.getWriter();
        res.setContentType("text/html");

        // Get book info
        String bookName = req.getParameter("bookName");
        String bookEdition = req.getParameter("bookEdition");
        float bookPrice;
        try {
            bookPrice = Float.parseFloat(req.getParameter("bookPrice"));
        } catch (NumberFormatException e) {
            pw.println("<h2>Invalid book price format</h2>");
            return;
        }

        // Load JDBC driver
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException cnf) {
            cnf.printStackTrace();
            pw.println("<h2>Database driver not found</h2>");
            return;
        }

        // Insert data into database
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/book", "root", "123456");
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, bookName);
            ps.setString(2, bookEdition);
            ps.setFloat(3, bookPrice);
            int count = ps.executeUpdate();
            if (count == 1) {
                pw.println("<h2>Record registered successfully</h2>");
            } else {
                pw.println("<h2>Record not registered successfully</h2>");
            }
        } catch (SQLException se) {
            se.printStackTrace();
            pw.println("<h2>" + se.getMessage() + "</h2>");
        } catch (Exception e) {
            e.printStackTrace();
            pw.println("<h2>" + e.getMessage() + "</h2>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doGet(req, res);
    }
}
