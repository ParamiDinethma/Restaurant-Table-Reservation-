// reservation-frontend/src/TableList.js
import React, { useState } from 'react';
import axios from 'axios';

const API_BASE_URL = 'http://localhost:8080/api/reservations';

function TableList({ tables, bookingDetails }) {
    // 1. STATE MANAGEMENT
    const [selectedTable, setSelectedTable] = useState(null);
    const [customerName, setCustomerName] = useState('');
    const [customerEmail, setCustomerEmail] = useState('');
    const [customerPhone, setCustomerPhone] = useState('');
    const [isSubmitting, setIsSubmitting] = useState(false);
    const [reservationResult, setReservationResult] = useState(null);

    const handleSelectTable = (table) => {
        setSelectedTable(table);
        setReservationResult(null); // Clear previous result when selecting a new table
    };

    // 2. THE CORE POST FUNCTION
    const handleSubmitReservation = async (e) => {
        e.preventDefault();
        setIsSubmitting(true);
        setReservationResult(null);

        // Combine date and time into a single string if needed,
        // but since your backend uses separate fields, we can send them separately.

        const payload = {
            tableId: selectedTable.id,
            // Assuming customer details will be created/matched on the backend
            customerName: customerName,
            customerEmail: customerEmail,
            customerPhone: customerPhone,

            // Reservation details from the search
            date: bookingDetails.date,
            time: bookingDetails.time,
            partySize: bookingDetails.partySize,

            // NOTE: If your ReservationRequest DTO only has customerId,
            // you'll need a separate API call to create the customer first,
            // but we assume the backend handles customer creation/lookup based on name/email/phone.
        };

        try {
            // Send the POST request to the Spring Boot endpoint
            const response = await axios.post(API_BASE_URL, payload);

            // Handle successful reservation (Status 201 Created or 200 OK)
            setReservationResult({
                status: 'success',
                message: `Reservation Confirmed! ID: ${response.data.id}. Status: ${response.data.status}`,
                data: response.data
            });
            // Clear the form after success
            setSelectedTable(null);

        } catch (error) {
            console.error('Reservation Error:', error.response || error);
            let errorMessage = 'Reservation failed. Please check the details.';

            if (error.response) {
                // If backend validation fails (e.g., table suddenly booked)
                errorMessage = error.response.data.message || `Error: ${error.response.statusText}`;
            }

            setReservationResult({
                status: 'error',
                message: errorMessage
            });

        } finally {
            setIsSubmitting(false);
        };
    };

    // 3. RENDER LOGIC: Show Result, Form, or List

    // Display the result of the last attempt
    if (reservationResult) {
        return (
            <div className={`result-box ${reservationResult.status}`}>
                <h3>{reservationResult.status === 'success' ? 'Booking Successful!' : 'Booking Failed'}</h3>
                <p>{reservationResult.message}</p>
                <button onClick={() => setReservationResult(null)}>Start New Search</button>
            </div>
        );
    }

    // Display the Final Booking Form
    if (selectedTable) {
        return (
            <div className="booking-form">
                <h3>Confirm Booking on Table {selectedTable.id}</h3>
                <p>Details: {bookingDetails.partySize} guests at {bookingDetails.time} on {bookingDetails.date}.</p>

                <form onSubmit={handleSubmitReservation}>
                    <input type="text" placeholder="Your Name" value={customerName} onChange={(e) => setCustomerName(e.target.value)} required />
                    <input type="email" placeholder="Email" value={customerEmail} onChange={(e) => setCustomerEmail(e.target.value)} required />
                    <input type="tel" placeholder="Phone Number" value={customerPhone} onChange={(e) => setCustomerPhone(e.target.value)} required />

                    {/* The final booking button */}
                    <button type="submit" disabled={isSubmitting}>
                        {isSubmitting ? 'Processing...' : 'Complete Reservation'}
                    </button>
                </form>
                <button onClick={() => setSelectedTable(null)} disabled={isSubmitting}>Change Table</button>
            </div>
        );
    }

    // Display the Table Selection List
    return (
        <div className="table-list">
            <h3>Select an Available Table:</h3>
            <ul>
                {tables.map(table => (
                    <li key={table.id}>
                        Table **{table.id}** (Capacity: {table.capacity})
                        <button onClick={() => handleSelectTable(table)}>Select</button>
                    </li>
                ))}
            </ul>
        </div>
    );
}

export default TableList;