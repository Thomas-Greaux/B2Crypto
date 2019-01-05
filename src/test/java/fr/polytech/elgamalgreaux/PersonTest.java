package fr.polytech.elgamalgreaux;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class PersonTest {

    private  ElGamal elGamal = new ElGamal();
    private Person person;

    @Before
    public void SetUp() {
        person = new Person(ElGamal.order, elGamal.getGenerator());
    }

    @Test
    public void privateKeyWithinOrderTest() {
        int order = person.getOrder();
        int privateKey = person.getPrivateKey();
        assertTrue(privateKey < order);
        assertTrue(0 < privateKey);
    }

    @Test
    public void sharedValueTest() {
        int order = person.getOrder();
        int privateKey = person.getPrivateKey();
        int generator = person.getGenerator();
        int sharedValue = person.getSharedValue();

        assertEquals((int) Math.pow(generator, privateKey) % (order+1), sharedValue);
    }
}
