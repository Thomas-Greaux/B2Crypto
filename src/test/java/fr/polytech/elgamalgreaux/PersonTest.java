package fr.polytech.elgamalgreaux;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class PersonTest {

    private Group group;

    {
        try {
            group = new Group();
        } catch (NotASafePrimeException e) {
            e.printStackTrace();
        }
    }

    private Person person;

    @Before
    public void SetUp() {
        person = new Person(group);
    }

    @Test
    public void privateKeyWithinOrderTest() {
        int order = group.getOrder();
        long privateKey = person.getPrivateKey();
        assertTrue(privateKey < order);
        assertTrue(0 < privateKey);
    }

    @Test
    public void sharedValueTest() {
        int prime = group.getPrime();
        long privateKey = person.getPrivateKey();
        long generator = group.getGenerator();
        long sharedValue = person.getSharedValue();

        assertEquals(Group.myPow(generator, privateKey, prime), sharedValue);
    }
}
