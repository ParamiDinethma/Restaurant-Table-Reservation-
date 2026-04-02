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

@WebServlet("/restaurant-delete")
public class DeleteRestarantServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession(false);

        String name = (String) req.getParameter("name");
        Owner owner = (Owner) session.getAttribute("owner");

        Restaurant restaurant = new Restaurant(name);

        restaurant.delete();

        resp.sendRedirect("dashboardOwner.jsp");

    }
}
