package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import code.Business_logic.*;

public class TestAccount {
    Account testAccount;

    @BeforeEach
    public void setUp() {
        testAccount = new Account(00000,00000,new Euro(2000), new Euro(2200));
    }

    @Test
    public void testValidatePIN() {
        assertTrue(testAccount.validatePIN(00000));
    }

    @Test
    public void testGetAvailableBalance() {
        assertEquals(new Euro(2000).getValore(), testAccount.getAvailableBalance().getValore());
    }

    @Test
    public void testGetTotalBalance() {
        assertEquals(new Euro(2200).getValore(), testAccount.getTotalBalance().getValore());
    }

    @ParameterizedTest
    @ValueSource(doubles = {1,100.5,9999})
    public void testCredit(double sum) {
        Euro oldAvailableAmount = testAccount.getAvailableBalance();
        Euro oldTotalAmount = testAccount.getTotalBalance();
        testAccount.credit(new Euro(sum));
        assertTrue(testAccount.getAvailableBalance().ugualeA(oldAvailableAmount));
        assertTrue(oldTotalAmount.minoreDi(testAccount.getTotalBalance()));
    }

    @ParameterizedTest
    @ValueSource(doubles = {1,100.5,999})
    public void testDebit(double sum) {
        Euro oldAvailableAmount = testAccount.getAvailableBalance();
        Euro oldTotalAmount = testAccount.getTotalBalance();
        testAccount.debit(new Euro(sum));
        assertTrue(oldAvailableAmount.minoreDi(testAccount.getAvailableBalance()));
        assertTrue(oldTotalAmount.minoreDi(testAccount.getTotalBalance()));
    }

    @Test
    public void testGetAccountNumber() {
        assertEquals(testAccount.getAccountNumber(), 00000);
    }
}
