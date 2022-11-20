package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import code.Database.*;
import code.Business_logic.*;
public class TestBankDatabase {
    BankDatabase testBankDatabase;

    @BeforeEach
    public void setUp() {
        testBankDatabase = new BankDatabase();
    }

    @ParameterizedTest
    @CsvSource({"12345,54321","98765,56789"})
    public void testAuthenticateUser(int account, int pin) {
        assertTrue(testBankDatabase.authenticateUser(account, pin));
    }

    @ParameterizedTest
    @CsvSource({"12345,100000","98765,20000"})
    public void testGetAvailableBalance(int account, long balance) {
        assertEquals(testBankDatabase.getAvailableBalance(account).getValore(), balance);
    }

    @ParameterizedTest
    @CsvSource({"12345,120000","98765,20000"})
    public void testGetTotalBalance(int account, long balance) {
        assertEquals(testBankDatabase.getTotalBalance(account).getValore(), balance);
    }

    @ParameterizedTest
    @CsvSource({"12345,1","12345,100.5","12345,9999","98765,1","98765,100.5","98765,9999"})
    public void testCredit(int account, double amount) {
        Euro oldAvailableAmount = testBankDatabase.getAvailableBalance(account);
        Euro oldTotalAmount = testBankDatabase.getTotalBalance(account);
        testBankDatabase.credit(account, new Euro(amount));
        assertTrue(oldAvailableAmount.ugualeA(testBankDatabase.getAvailableBalance(account)));
        assertTrue(oldTotalAmount.minoreDi(testBankDatabase.getTotalBalance(account)));
    }

    @ParameterizedTest
    @CsvSource({"12345,1","12345,100.5","12345,9999","98765,1","98765,50.5","98765,100"})
    public void testDebit(int account, double amount) {
        Euro oldAvailableAmount = testBankDatabase.getAvailableBalance(account);
        Euro oldTotalAmount = testBankDatabase.getTotalBalance(account);
        testBankDatabase.debit(account, new Euro(amount));
        assertTrue(oldAvailableAmount.minoreDi(testBankDatabase.getAvailableBalance(account)));
        assertTrue(oldTotalAmount.minoreDi(testBankDatabase.getTotalBalance(account)));
    }
}
