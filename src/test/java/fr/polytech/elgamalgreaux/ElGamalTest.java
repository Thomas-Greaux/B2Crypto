package fr.polytech.elgamalgreaux;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ElGamalTest {

    private ElGamal elGamal;

    @Before
    public void SetUp() {
        elGamal = new ElGamal();
    }

    @Test
    public void generatorTest() {
        int generator = elGamal.getGenerator();
        int order = elGamal.getOrder();

        List<Integer> generatedElements = new ArrayList<>();

        //Generating the group
        for(int i = 0; i < order; i++) {
            generatedElements.add((int) Math.pow(generator, i) % (order+1));
        }

        //Checking that the generation is ok
        Collections.sort(generatedElements);
        for (int i = 1; i <= order; i++) {
            assertEquals((Integer) i, generatedElements.get(i-1));
        }
    }

}