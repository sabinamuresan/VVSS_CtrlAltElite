package pizzashop.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import pizzashop.model.Payment;
import pizzashop.model.PaymentType;
import pizzashop.repository.PaymentRepository;
import pizzashop.service.PizzaService;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestPaymentServiceWithMockito {
    @Mock
    private PaymentRepository paymentRepository;

    @InjectMocks
    private PizzaService pizzaService;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @AfterEach
    void tearDown(){
        pizzaService = null;
    }
    @Test
    void addPayment(){
        Payment payment = new Payment(6, PaymentType.Cash, 15);
        Payment payment2 = new Payment(4, PaymentType.Cash, 20);
        Mockito.when(paymentRepository.getAll()).thenReturn(Arrays.asList(payment));

        pizzaService.addPayment(payment2.getTableNumber(),payment2.getType(),payment2.getAmount());
        assertEquals(1,pizzaService.getPayments().size());
    }
}
