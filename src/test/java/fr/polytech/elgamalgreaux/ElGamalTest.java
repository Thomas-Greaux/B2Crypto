package fr.polytech.elgamalgreaux;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ElGamalTest {

    public Person alice, bob;
    public Group group;

    @Before
    public void setUp() {
        group = new Group();
        alice = new Person(group);
        bob = new Person(group);
    }

    @Test
    public void validEncryptionTest() {
        long message1 = 2;
        long message2 = group.getPrime()/2;

        List<Long> messages = new ArrayList<>();
        messages.add(message1);
        messages.add(message2);

        for(long m : messages) {
            assertEquals(m, alice.decrypt(bob.encrypt((int) m, alice)));
        }
    }
}
