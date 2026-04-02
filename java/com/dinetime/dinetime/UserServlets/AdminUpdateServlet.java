package com.dinetime.dinetime.UserServlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/admin-update")
public class AdminUpdateServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        HttpSession session = req.getSession(false);

        Admin admin = (Admin) session.getAttribute("admin");
        if (admin==null){
            resp.sendRedirect("user-logout");
        }
        admin.changeAdminEmail(email);
        admin.changeAdminPassword(password);
        admin.changeAdminName(name);
        resp.sendRedirect("user-logout");
    }
}
