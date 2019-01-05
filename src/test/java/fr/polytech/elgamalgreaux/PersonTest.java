package fr.polytech.elgamalgreaux;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertTrue;


public class PersonTest {

    private Person person;

    @Before
    public void SetUp() {
        person = new Person(ElGamal.order);
    }

    @Test
    public void privateKeyWithinOrderTest() {
        int order = person.getOrder();
        int privateKey = person.getPrivateKey();
        assertTrue(privateKey < order);
        assertTrue(0 < privateKey);
    }
}
