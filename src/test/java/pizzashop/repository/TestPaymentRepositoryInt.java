package pizzashop.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import pizzashop.model.Payment;
import pizzashop.model.PaymentType;
import pizzashop.service.PizzaService;
import pizzashop.validator.PaymentValidator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class TestPaymentRepositoryInt {
    PaymentRepository paymentRepository;
    PizzaService paymentService;

    Payment payment;

    @BeforeEach
    void setUp() {
        payment = mock(Payment.class);
        paymentRepository = new PaymentRepository(new PaymentValidator());
        paymentService = new PizzaService(new MenuRepository(),paymentRepository);
        Mockito.when(payment.getPayment()).thenReturn(new Payment(2, PaymentType.Cash,12));
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getPayments() {
        int nr = paymentService.getPayments().size();
        assertEquals(nr, paymentService.getPayments().size());
        paymentRepository.add(payment.getPayment());
        assertEquals(nr + 1, paymentService.getPayments().size());

    }

    @Test
    void addPayment() {
        Payment payment1= new Payment(6, PaymentType.Card,19);
        int nr = paymentService.getPayments().size();
        paymentRepository.add(payment1);
        assertEquals(payment1,paymentService.getPayments().get(nr));
        assertEquals(nr + 1, paymentService.getPayments().size());
    }
}
