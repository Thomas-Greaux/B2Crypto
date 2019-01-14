package fr.polytech.elgamalgreaux;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class GroupTest {

    private Group group = new Group();

    @Test
    public void inverseTest() {
        for(int i = 2; i < group.getOrder() / 10; i++) {
            assertNotEquals(1, i*group.inverse(i) % group.getPrime());
        }
    }

    //TODO: use parameterized test
    @Test
    public void checkPrimeTest() {
        assertFalse(Group.checkPrime(2));
        assertFalse(Group.checkPrime(4));
        assertFalse(Group.checkPrime(16));
        assertFalse(Group.checkPrime(30));

        assertTrue(Group.checkPrime(5));
        assertTrue(Group.checkPrime(7));
        assertTrue(Group.checkPrime(17));
        assertTrue(Group.checkPrime(19));
    }

    @Test
    public void checkGeneratorTest() {
        long g = group.getGenerator();
        assertNotEquals(g, -1);
        List<Long> generated_elements = new ArrayList<>();
        for(int i = 0; i < group.getPrime(); i++) {
            generated_elements.add(Group.myPow(g, i, group.getPrime()));
        }
        Collections.sort(generated_elements);
        for(int i = 0; i < generated_elements.size(); i++) {
            assertEquals(i, generated_elements.get(i)-1);
        }
    }
}
