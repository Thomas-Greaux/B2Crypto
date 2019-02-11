package fr.polytech.elgamalgreaux;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class GroupTest {

    private Group group;

    {
        try {
            group = new Group();
        } catch (NotASafePrimeException e) {
            e.printStackTrace();
        }
    }

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
        for(int i = 0; i < group.getOrder()-1; i++) {
            generated_elements.add(Group.myPow(g, i, group.getOrder()));
        }
        Collections.sort(generated_elements);
        for(int i = 0; i < generated_elements.size(); i++) {
            assertEquals(i, generated_elements.get(i)-1);
        }
    }

    @Test
    public void checkMyPow() {
        assertEquals(1, Group.myPow(2, 0, 5));
        assertEquals(1, Group.myPow(2, 4, 5));

        assertEquals(2, Group.myPow(2, 1, 5));
        assertEquals(2, Group.myPow(2, 5, 5));

        assertEquals(4, Group.myPow(2, 2, 5));
        assertEquals(4, Group.myPow(2, 6, 5));

        assertEquals(3, Group.myPow(2, 3, 5));
        assertEquals(3, Group.myPow(2, 7, 5));
    }

    @Test
    public void jacobiTest() {
        assertEquals(1, group.jacobi(3));
        assertEquals(1, group.jacobi(4));
        assertEquals(1, group.jacobi(5));
        assertEquals(1, group.jacobi(9));

        assertNotEquals(1, group.jacobi(2));
    }

    @Test
    public void isQuadraticResidueTest() {
        assertTrue(group.isQuadraticResidue(3));
        assertTrue(group.isQuadraticResidue(4));
        assertTrue(group.isQuadraticResidue(5));
        assertTrue(group.isQuadraticResidue(9));

        assertFalse(group.isQuadraticResidue(2));
    }
}
