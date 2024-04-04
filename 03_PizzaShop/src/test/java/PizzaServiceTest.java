
import org.junit.jupiter.api.*;
import pizzashop.model.PaymentType;
import pizzashop.repository.MenuRepository;
import pizzashop.repository.PaymentRepository;
import pizzashop.service.PaymentException;
import pizzashop.service.PizzaService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PizzaServiceTest {
    private PizzaService serviceTest;

    @BeforeEach
    void setUp() {
        PaymentRepository paymentRepositoryTest = new PaymentRepository();
        MenuRepository menuRepositoryTest = new MenuRepository();
        serviceTest = new PizzaService(menuRepositoryTest, paymentRepositoryTest);
    }

    @Disabled
    @AfterEach
    void tearDown() {
    }

    @Order(1)
    @Test
    void addPaymentTC1_ECP() {
        int tableNo = 3;
        PaymentType type = PaymentType.Cash;
        double amount = 56.9;
        double cashAmountBefore = serviceTest.getTotalAmount(type);
        serviceTest.addPayment(tableNo, type, amount);
        double cashAmountAfter = serviceTest.getTotalAmount(type);

        assertEquals(cashAmountBefore + amount, cashAmountAfter);
    }

    @Order(2)
    @RepeatedTest(value = 2)
    void addPaymentTC2_ECP() {
        int tableNo = 6;
        double amount = 60.0;
        PaymentType type = PaymentType.Card;
        double cardAmountBefore = serviceTest.getTotalAmount(type);
        serviceTest.addPayment(tableNo,type, amount);
        double cardAmountAfter = serviceTest.getTotalAmount(type);

        assertEquals(cardAmountBefore + amount, cardAmountAfter);
    }
    @Order(3)
    @Test
    @Tag("InvalidTableTest")
    void addPaymentTC3_ECP() {
        int tableNoInvalid = 9;
        PaymentType type = PaymentType.Cash;
        double amount = 70;
        assertThrows(PaymentException.class, () -> serviceTest.addPayment(tableNoInvalid, type, amount));
    }
    @Order(4)
    @Test
    void addPaymentTC4_ECP() {
        int tableNoInvalid = 4;
        PaymentType type = PaymentType.Card;
        double amount = -4;
        assertThrows(PaymentException.class, () -> serviceTest.addPayment(tableNoInvalid, type, amount));
    }
    @Order(5)
    @Test
    @DisplayName("BVA - table number is 0 (invalid)")
    void addPaymentTC1_BVA() {
        int tableNo = 0;
        PaymentType type = PaymentType.Cash;
        double amount = 11.4;
        assertThrows(PaymentException.class, () -> serviceTest.addPayment(tableNo,type, amount));
    }
    @Order(6)
    @Test
    void addPaymentTC2_BVA() {
        int tableNo = 5;
        PaymentType type = PaymentType.Card;
        double amount = 70.99;
        double cardAmountBefore = serviceTest.getTotalAmount(type);
        serviceTest.addPayment(tableNo,type, amount);
        double cardAmountAfter = serviceTest.getTotalAmount(type);

        assertEquals(cardAmountBefore + amount, cardAmountAfter);
    }
    @Order(7)
    @Test
    void addPaymentTC3_BVA() {
        int tableNo = 8;
        PaymentType type = PaymentType.Cash;
        double amount = 11.4;
        double cardAmountBefore = serviceTest.getTotalAmount(type);
        serviceTest.addPayment(tableNo,type, amount);
        double cardAmountAfter = serviceTest.getTotalAmount(type);

        assertEquals(cardAmountBefore + amount, cardAmountAfter);
    }
    @Order(8)
    @Test
    @DisplayName("BVA - amount is < 0 (invalid)")
    void addPaymentTC4_BVA() {
        int tableNo = 6;
        PaymentType type = PaymentType.Cash;
        double amount = -56;
        assertThrows(PaymentException.class, () -> serviceTest.addPayment(tableNo,type, amount));
    }
}
