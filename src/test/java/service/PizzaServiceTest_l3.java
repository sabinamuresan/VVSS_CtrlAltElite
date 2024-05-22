package service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pizzashop.model.Payment;
import pizzashop.model.PaymentType;
import pizzashop.repository.MenuRepository;
import pizzashop.repository.PaymentRepository;
import pizzashop.service.PizzaService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PizzaServiceTest_l3 {
    private PizzaService serviceTest;

    @BeforeEach
    void setUp() {
        PaymentRepository paymentRepositoryTest = new PaymentRepository();
        MenuRepository menuRepositoryTest = new MenuRepository();
        serviceTest = new PizzaService(menuRepositoryTest, paymentRepositoryTest);
    }

    @Test
    void getTotalAmount_TC01() {
        Payment payment = new Payment(2, PaymentType.Cash, 500);
        List<Payment> list = new ArrayList<>();
        list.add(payment);
        serviceTest.setPaymentList(list);
        double total = serviceTest.getTotalAmount(PaymentType.Cash);
        assertEquals(total, payment.getAmount());
    }

    @Test
    void getTotalAmount_TC02() {
        List<Payment> list = new ArrayList<>();
        serviceTest.setPaymentList(list);
        double total = serviceTest.getTotalAmount(PaymentType.Cash);
        assertEquals(total,0);
    }
    @Test
    void getTotalAmount_TC03() {
        serviceTest.setPaymentList(null);
        double total = serviceTest.getTotalAmount(PaymentType.Cash);
        assertEquals(total,0);
    }

    @Test
    void getTotalAmount_TC04() {
        Payment payment = new Payment(2, PaymentType.Card, 10500);
        List<Payment> list = new ArrayList<>();
        list.add(payment);
        serviceTest.setPaymentList(list);
        double total = serviceTest.getTotalAmount(PaymentType.Cash);
        assertEquals(total, 0);
    }
    @Test
    void getTotalAmount_TC05() {
        Payment payment = new Payment(2, PaymentType.Cash, 10500);
        List<Payment> list = new ArrayList<>();
        list.add(payment);
        serviceTest.setPaymentList(list);
        double total = serviceTest.getTotalAmount(PaymentType.Cash);
        assertEquals(total, payment.getAmount());
    }

    @Test
    void getTotalAmount_TC06() {
        Payment payment = new Payment(2, PaymentType.Cash, 10500);
        Payment payment2 = new Payment(3, PaymentType.Cash, 10500);
        List<Payment> list = new ArrayList<>();
        list.add(payment);
        list.add(payment2);
        serviceTest.setPaymentList(list);
        double total = serviceTest.getTotalAmount(PaymentType.Cash);
        assertEquals(total, payment.getAmount());
    }
}
