package com.dinetime.dinetime.UserServlets;

import com.dinetime.dinetime.Classes.Customer;
import com.dinetime.dinetime.Classes.Owner;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
@WebServlet("/register-user")
public class UserRegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("register-name");
        String email = request.getParameter("register-email");
        String password = request.getParameter("register-password");
        String passwordConfirm = request.getParameter("register-password-conform");
        int accType = Integer.parseInt(request.getParameter("register-accType"));

        if (!password.equals(passwordConfirm)) {
            response.sendRedirect("register.jsp?msg=Password Did Not Match");
            return;
        }

        HttpSession session = request.getSession();

        if (accType == 1) { // Customer
            Customer customer = new Customer(name, password, email);
            customer.save();
            response.sendRedirect("login.jsp");
        } else if (accType == 2) { // Owner
            Owner owner = new Owner(name, password, email);
            owner.save();
            response.sendRedirect("login.jsp");
        } else {
            response.sendRedirect("register.jsp?msg=Invalid Account Type");
        }
    }
}
