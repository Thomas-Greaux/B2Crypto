package fr.polytech.elgamalgreaux;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ElGamal {

    public static final int order = 16;

    private int generator;

    public static void main(String[] args) {
        System.out.println("ElGamal cryptography");

        ElGamal elGamal = new ElGamal();
        Person alice = new Person(order, elGamal.getGenerator());
        Person bob = new Person(order, elGamal.getGenerator());

        bob.sendEncrypted(12, alice);
    }

    public ElGamal() {
        for (int i = 2; i <= order; i++) {
            if (checkGenerator(i)) {
                generator = i;
                break;
            }
        }
    }

    private boolean checkGenerator(int g) {

        List<Integer> generatedElements = new ArrayList<>();

        //Generating the group
        for(int i = 0; i < order; i++) {
            generatedElements.add((int) Math.pow(g, i) % (order+1));
        }

        //Checking that the generation is ok
        Collections.sort(generatedElements);
        for (int i = 1; i <= order; i++) {
            if(i != generatedElements.get(i-1)) {
                return false;
            }
        }
        return true;
    }

    public int getGenerator() {
        return generator;
    }

    public int getOrder() {
        return order;
    }
}
