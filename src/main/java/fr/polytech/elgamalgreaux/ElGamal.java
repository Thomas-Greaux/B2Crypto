package fr.polytech.elgamalgreaux;

import java.util.ArrayList;
import java.util.List;

public class ElGamal {

    public static final int MESSAGE = 2;

    public static void main(String[] args) {
        elGamal(MESSAGE);

        /*
        List<Integer> test = new ArrayList<>();
        test.add(-2);
        test.add(0);
        test.add(2);
        test.add(11);
        test.add(12);

        for(int i : test) {
            elGamal(i);
        }
        */
    }

    public static void elGamal(int message) {
        System.out.println("ElGamal cryptography");

        Group group = new Group();
        Person alice = new Person(group);
        Person bob = new Person(group);

        System.out.println("Message: " + message);

        // Ciphering
        List<Long> encrypted = bob.encrypt(message, alice);

        if(encrypted == null) return;

        // Verifying that the cipher is QR
        long c1 = encrypted.get(0);
        long c2 = encrypted.get(1);
        System.out.println("Encrypted: (" + c1 + ", " + c2 + ")");
        System.out.println(c2 + " residue quadratic? " + group.isQuadraticResidue((int) c2));

        // Decrypting
        long m = alice.decrypt(encrypted);
        System.out.println("Decrypted: " + m);
    }


}
