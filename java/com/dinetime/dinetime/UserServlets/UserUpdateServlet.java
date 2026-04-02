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
@WebServlet("/update-user")
public class UserUpdateServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String newName = req.getParameter("name");
        String newEmail = req.getParameter("email");
        String newPassword1 = req.getParameter("newPassword1");
        String newPassword2 = req.getParameter("newPassword2");

        HttpSession session = req.getSession(false);

        //if user updates the password
        if (newPassword1.equals(newPassword2) && !newPassword1.isEmpty()){
            if (session.getAttribute("owner")!= null){
                Owner owner = (Owner) session.getAttribute("owner");
                owner.update(newName,newPassword1,newEmail);
            }else {
                Customer customer = (Customer) session.getAttribute("customer");
                customer.update(newName,newPassword1,newEmail);
            }
        }//if user did not update
        else if (newPassword1.equals(newPassword2) && newPassword1.isEmpty()) {
            if (session.getAttribute("owner")!= null){
                Owner owner = (Owner) session.getAttribute("owner");
                owner.update(newName,owner.getPassword(),newEmail);
            }else {
                Customer customer = (Customer) session.getAttribute("customer");
                customer.update(newName,customer.getPassword(),newEmail);
            }
        }//password did not match
        else {
            resp.sendRedirect("user-account");
        }

        resp.sendRedirect("user-logout");

    }
}
