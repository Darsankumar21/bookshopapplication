package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/displayBooks")
public class DisplayBooksServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private static final String DELETE_QUERY = "DELETE FROM BOOKDATA WHERE BOOKNAME=?";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        PrintWriter pw = res.getWriter();
        res.setContentType("text/html");

        String bookNameToDelete = req.getParameter("delete");

        // Load JDBC driver
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException cnf) {
            cnf.printStackTrace();
            pw.println("<h2>Database driver not found</h2>");
            return;
        }

        if (bookNameToDelete != null) {
            // Delete the book from the database
            try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/book", "root", "123456");
                 PreparedStatement ps = con.prepareStatement(DELETE_QUERY)) {
                ps.setString(1, bookNameToDelete);
                ps.executeUpdate();
            } catch (SQLException se) {
                se.printStackTrace();
                pw.println("<h2>" + se.getMessage() + "</h2>");
            }
        }

        // Fetch data from database
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/book", "root", "123456");
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT BOOKNAME, BOOKEDITION, BOOKPRICE FROM BOOKDATA")) {
            
            pw.println("<h2>List of Books</h2>");
            pw.println("<table border='1'>");
            pw.println("<tr><th>Book Name</th><th>Book Edition</th><th>Book Price</th><th>Action</th></tr>");
            
            while (rs.next()) {
                String bookName = rs.getString("BOOKNAME");
                String bookEdition = rs.getString("BOOKEDITION");
                float bookPrice = rs.getFloat("BOOKPRICE");
                pw.println("<tr>");
                pw.println("<td>" + bookName + "</td>");
                pw.println("<td>" + bookEdition + "</td>");
                pw.println("<td>" + bookPrice + "</td>");
                pw.println("<td><a href='displayBooks?delete=" + bookName + "'>Remove</a></td>");
                pw.println("</tr>");
            }
            
            pw.println("</table>");
            pw.println("<br><a href='home.html'>Add a new book</a>");
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
