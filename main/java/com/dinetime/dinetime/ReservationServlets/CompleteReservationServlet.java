package com.dinetime.dinetime.ReservationServlets;

import com.dinetime.dinetime.Classes.Restaurant;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/complete-reservation")
public class CompleteReservationServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String tableId = req.getParameter("tableId");
        String retaurant = req.getParameter("restaurant");
        Restaurant restaurantObj = new Restaurant(retaurant);
        restaurantObj.completeReservation(Integer.parseInt(tableId));
        resp.sendRedirect("tableReservations.jsp?name="+retaurant);
    }
}
