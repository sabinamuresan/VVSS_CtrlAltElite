package repository;

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
import pizzashop.validator.PaymentValidator;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TestPaymentRepository {
    @Mock
    private PaymentValidator paymentValidator;
    @InjectMocks
    private PaymentRepository paymentRepository;
    private Payment payment;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        //paymentRepository = new PaymentRepository(paymentValidator);
        payment = mock(Payment.class);
    }
    @AfterEach
    void tearDown(){
        payment = null;
        paymentRepository = null;
    }

    @Test
    void add_invalid() {
        int nr = paymentRepository.getAll().size();
        Mockito.when(paymentValidator.validate(payment)).thenReturn(new ArrayList<>(Collections.singleton("abc")));
        try{
            paymentRepository.add(payment);
        }catch(Exception e){
            System.out.println("Test passed");
        }
        assertEquals(nr, paymentRepository.getAll().size());
        Mockito.verify(paymentValidator, times(1)).validate(payment);
    }

    @Test
    void add_payment(){
        int nr = paymentRepository.getAll().size();
        Payment payment1 = new Payment(6, PaymentType.Cash, 15);
        Mockito.when(paymentValidator.validate(payment1)).thenReturn(new ArrayList<>());
        try{
            paymentRepository.add(payment1);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Failed test");
        }
        assertEquals(nr+1, paymentRepository.getAll().size());
        Mockito.verify(paymentValidator, times(1)).validate(payment1);
    }
}
