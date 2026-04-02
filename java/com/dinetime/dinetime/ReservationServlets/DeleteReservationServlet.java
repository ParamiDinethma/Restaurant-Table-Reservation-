package com.dinetime.dinetime.ReservationServlets;

import com.dinetime.dinetime.Classes.Table;
import com.dinetime.dinetime.Handlers.StorageHandler;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/delete-reservation")
public class DeleteReservationServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String tableId = req.getParameter("tableId");
        String restaurant = req.getParameter("restaurant");

        StorageHandler handler = new StorageHandler("reservations.txt");
        HttpSession session = req.getSession(false);
        handler.deleteLine(restaurant,1,tableId,4);
        Table table = new Table(Integer.parseInt(tableId),restaurant);
        table.update(false);
        resp.sendRedirect("dashboardCustomer.jsp?msg=Reservation Deleted Successful");
    }
}
