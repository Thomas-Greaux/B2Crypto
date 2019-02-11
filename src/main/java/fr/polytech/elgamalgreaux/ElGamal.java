package fr.polytech.elgamalgreaux;

import java.util.ArrayList;
import java.util.List;

public class ElGamal {

    public static final int MESSAGE = 5;

    public static void main(String[] args) {
        try {
            elGamal(MESSAGE);
        } catch (NotASafePrimeException e) {
            e.printStackTrace();
        }

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

        /*
        for(int i = 1; i < 11; i++) {
            System.out.println(i + " squared = " + (i*i)%11);
        }
        */
    }

    public static void elGamal(int message) throws NotASafePrimeException {
        System.out.println("ElGamal cryptography");

        Group group = new Group();
        Person alice = new Person(group);
        Person bob = new Person(group);

        System.out.println("Message: " + message);

        // Ciphering
        List<Long> encrypted = bob.encrypt(message, alice);

        // If encrypted is null, it means the message is not a QR
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
