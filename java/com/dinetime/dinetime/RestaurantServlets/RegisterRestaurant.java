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
@WebServlet("/register-restaurant")
public class RegisterRestaurant extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String restaurantName = req.getParameter("restaurant-name");
        String restaurantType = req.getParameter("restaurant-type");
        String restaurantImage = req.getParameter("restaurant-image");
        String restaurantDescription = req.getParameter("restaurant-description");

        HttpSession session = req.getSession(false);

        Owner owner = (Owner) session.getAttribute("owner");

        Restaurant newrestaurant = new Restaurant(restaurantName,restaurantType,restaurantImage,restaurantDescription,owner.getEmail());
        newrestaurant.save();

        resp.sendRedirect("user-account");
    }

}
