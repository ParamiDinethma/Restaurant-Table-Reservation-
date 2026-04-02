package com.dinetime.dinetime.ReservationServlets;

import com.dinetime.dinetime.Classes.Customer;
import com.dinetime.dinetime.Classes.Reservation;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet("/add-reservation")
public class AddReservationServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        Customer customer = (Customer) session.getAttribute("customer");

        String restaurantName = req.getParameter("restaurantName");
        int seatCount = Integer.parseInt(req.getParameter("seatCount"));
        String timeAndDate = req.getParameter("timeDate");


        LocalDateTime reservationTimeAndDate = LocalDateTime.parse(timeAndDate);
        LocalDateTime nowTimeAndDate = LocalDateTime.now();

        if (reservationTimeAndDate.isAfter(nowTimeAndDate)){
            Reservation reservation = new Reservation(restaurantName,reservationTimeAndDate,seatCount,customer.getEmail());
            reservation.saveResrvation();
            int tableID = Integer.parseInt(reservation.getTableID());
            if (tableID==-99)
                resp.sendRedirect("confirmation.jsp?status=f");
            else
                resp.sendRedirect("confirmation.jsp?status=p&tableId="+tableID+"&name="+restaurantName+"&date="+reservationTimeAndDate);

        }
        else {
            resp.sendRedirect("Reservation.jsp?r="+restaurantName+"&msg=Time must be in future");
        }

    }
}
