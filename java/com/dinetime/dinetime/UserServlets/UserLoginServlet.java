package com.dinetime.dinetime.UserServlets;

import com.dinetime.dinetime.Classes.Customer;
import com.dinetime.dinetime.Classes.Owner;
import com.dinetime.dinetime.Handlers.StorageHandler;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/user-login")
public class UserLoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("singin-email");
        String password = request.getParameter("singin-password");
        HttpSession session = request.getSession();

        //Check for admin
        if (email.equals("admin@dinetime.com")&&password.equals("admin@DineTime")){
            Admin admin = new Admin();
            session.setAttribute("admin",admin);
            response.sendRedirect("admin.jsp");
            return;
        }

        StorageHandler handler = new StorageHandler("users.txt");
        String[] userLine = handler.findLine(email, 0);

        if (userLine.length > 0 && userLine[2].equals(password)) {
             if (userLine[3].equals("1")){
                Customer customer = new Customer(userLine[1],userLine[2],userLine[0]);
                session.setAttribute("customer",customer);
                response.sendRedirect("dashboardCustomer.jsp");
            } else{
                Owner owner = new Owner(userLine[1],userLine[2],userLine[0]);
                session.setAttribute("owner",owner);
                response.sendRedirect("dashboardOwner.jsp");
            }
        } else {
            response.sendRedirect("login.jsp?msg=incorrect password or userrname try again");
        }
    }
}
