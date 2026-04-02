package com.dinetime.dinetime.UserServlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
@WebServlet("/user-account")
public class UserAccounServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session.getAttribute("customer") != null) {
            response.sendRedirect("dashboardCustomer.jsp");
        } else if (session.getAttribute("owner") != null) {
            response.sendRedirect("dashboardOwner.jsp");
        } else if (session.getAttribute("admin") != null) {
            response.sendRedirect("admin.jsp");
        }  else {
            response.sendRedirect("login.jsp");
        }

    }
}
