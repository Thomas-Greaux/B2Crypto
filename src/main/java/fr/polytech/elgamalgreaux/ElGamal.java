package fr.polytech.elgamalgreaux;

import java.util.List;

public class ElGamal {

    public static final int MESSAGE = 1;

    public static void main(String[] args) {
        elGamal(MESSAGE);

        /*
        for(int i = -2; i < 47; i++) {
            elGamal(i);
        }
        */

    }

    public static void elGamal(int message) {
        System.out.println("ElGamal cryptography");

        Group group = new Group();
        Person alice = new Person(group);
        Person bob = new Person(group);

        message %= group.getPrime();

        if(!group.isQuadraticResidue(message)) {
            System.out.println(message + " is not a quadratic residue, exiting...");
            return;
        }


        System.out.println("Message: " + message);

        // Ciphering
        List<Long> encrypted = bob.encrypt(message, alice);

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
