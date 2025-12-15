package com.restaurant.reservationsystem.service;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;

import jakarta.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Service
public class PaymentService {

    @Value("${stripe.secret.key}")
    private String secretKey;

    // This method runs once after the service is initialized to set the API key.
    @PostConstruct
    public void init() {
        Stripe.apiKey = secretKey;
    }

    /**
     * Creates a Payment Intent with Stripe.
     * @param amount The amount to charge (in cents/lowest currency unit).
     * @param currency The currency code (e.g., "usd").
     * @param paymentMethodToken The token representing the client's payment details.
     * @param description A description for the payment.
     * @return The Stripe PaymentIntent object.
     * @throws StripeException if the payment processing fails.
     */
    public PaymentIntent createPaymentIntent(Long amount, String currency, String paymentMethodToken, String description)
            throws StripeException {

        Map<String, Object> params = new HashMap<>();
        params.put("amount", amount);
        params.put("currency", currency);
        params.put("payment_method", paymentMethodToken);
        params.put("description", description);

        // You would typically use a PaymentIntent for modern client-side flows
        PaymentIntentCreateParams createParams = PaymentIntentCreateParams.builder()
                .setAmount(amount)
                .setCurrency(currency)
                .setDescription(description)
                // In a real app, you'd confirm the payment method token/id here
                // For simplicity, we are showing a basic charge structure
                .build();

        return PaymentIntent.create(createParams);
    }
}