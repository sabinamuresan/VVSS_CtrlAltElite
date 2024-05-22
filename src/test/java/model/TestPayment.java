package model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pizzashop.model.Payment;
import pizzashop.model.PaymentType;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestPayment {
    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {
    }
    @Test
    void getType(){
        Payment payment = new Payment(6, PaymentType.Cash, 15);
        PaymentType type = payment.getType();
        assertEquals(type, PaymentType.Cash);
    }
    @Test
    void getAmount(){
        Payment payment = new Payment(6, PaymentType.Cash, 15);
        double amount = payment.getAmount();
        assertEquals(amount, 15);
    }
}
