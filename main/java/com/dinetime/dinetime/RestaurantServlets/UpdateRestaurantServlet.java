package com.dinetime.dinetime.RestaurantServlets;

import com.dinetime.dinetime.Classes.Owner;
import com.dinetime.dinetime.Classes.Restaurant;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/update-restaurant")
public class UpdateRestaurantServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        String newName = (String) req.getParameter("newName");
        String name = (String) req.getParameter("name");
        String type = (String) req.getParameter("type");
        String link = (String) req.getParameter("link");
        String description = (String) req.getParameter("description");
        Owner owner = (Owner) session.getAttribute("owner");


        Restaurant restaurant = new Restaurant(name);

        if (restaurant.nameAlreadyTaken()){
            restaurant.update(newName,type,link,description);
            resp.sendRedirect("user-account");
        }else{
            resp.sendRedirect("dashboardCustomer.jsp?msg= restaurant name did not match");
        }
    }
}
