package com.dinetime.dinetime.RestaurantServlets;

import com.dinetime.dinetime.Classes.Table;
import com.dinetime.dinetime.Handlers.StorageHandler;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/register-table")
public class AddTablesServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String restaurantName = req.getParameter("name");
        int tableId = Integer.parseInt(req.getParameter("id"));
        int tableSeats = Integer.parseInt(req.getParameter("seats"));

        StorageHandler handler = new StorageHandler("tables.txt");
        String[][] allTables = handler.readAllLines();
        for (String[] table: allTables){
            if (table[0].equals(restaurantName)&&table[1].equals(String.valueOf(tableId))){
                Table updateTable = new Table(tableId,restaurantName);
                updateTable.update(tableSeats);
                resp.sendRedirect("tableReservations.jsp?name="+restaurantName);
                return;
            }
        }

        Table newTable = new Table(tableId,restaurantName,tableSeats,false);
        newTable.save();

        resp.sendRedirect("tableReservations.jsp?name="+restaurantName);
    }
}
