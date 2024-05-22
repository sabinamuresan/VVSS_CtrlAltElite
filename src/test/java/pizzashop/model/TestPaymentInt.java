package pizzashop.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pizzashop.repository.MenuRepository;
import pizzashop.repository.PaymentRepository;
import pizzashop.service.PizzaService;
import pizzashop.validator.PaymentValidator;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestPaymentInt {
    Payment payment = new Payment(2,PaymentType.Cash,12);;

    PaymentRepository paymentRepository;
    PizzaService paymentService;


    @BeforeEach
    void setUp() {
        paymentRepository = new PaymentRepository(new PaymentValidator());
        paymentService = new PizzaService(new MenuRepository(),paymentRepository);
    }

    @AfterEach
    void tearDown() {
        payment = null;
    }

    @Test
    void getPayments() {
        int nr = paymentService.getPayments().size();
        assertEquals(nr, paymentService.getPayments().size());
        paymentRepository.add(payment);
        assertEquals(nr + 1, paymentService.getPayments().size());

    }

    @Test
    void addPayment() {
        Payment payment1= new Payment(8,PaymentType.Card,19);
        int nr = paymentService.getPayments().size();
        paymentRepository.add(payment1);
        assertEquals(payment1,paymentService.getPayments().get(nr));
        assertEquals(nr + 1, paymentService.getPayments().size());
    }
}
