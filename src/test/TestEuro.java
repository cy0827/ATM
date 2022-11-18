package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import code.Business_logic.*;;

public class TestEuro {
    Euro testEuro;

    @BeforeEach
    public void setUp() {
        testEuro = new Euro(100);
    }

    @Test
    public void testGetValore() {
        assertEquals(10000,testEuro.getValore());
    }

    @Test
    public void testSomma() {
        assertEquals(11000,testEuro.somma(new Euro(10)).getValore());
    }

    @Test
    public void testSottrai() {
        assertEquals(9000,testEuro.sottrai(new Euro(10)).getValore());
    }

    @Test
    public void testUgualeA() {
        assertTrue(testEuro.ugualeA(new Euro(100)));
    }

    @ParameterizedTest
    @ValueSource(doubles = {101,200.50,9999})
    public void testMinoreDi(double value) {
        assertTrue(testEuro.minoreDi(new Euro(value)));
    }

    @Test
    public void testStampa() {
        assertEquals((double)testEuro.getValore()/100 + " euro",testEuro.stampa());
    }
}
