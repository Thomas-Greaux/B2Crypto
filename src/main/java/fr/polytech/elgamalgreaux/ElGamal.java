package fr.polytech.elgamalgreaux;

import java.util.List;

public class ElGamal {

    public static final int MESSAGE = 2;
    public static final int SALT = 3;
    public static void main(String[] args) {
        //unsecure();
        secure();
    }

    private static void secure() {
        System.out.println("ElGamal cryptography");

        Group group = new Group();
        Person alice = new Person(group);
        Person bob = new Person(group);

        System.out.println("Message: " + ((MESSAGE)%group.getPrime()) + " residue quadratic? " + group.isQuadraticResidue(MESSAGE));
        System.out.println("Message salted: " + ((MESSAGE+SALT)%group.getPrime()) + " residue quadratic? " + group.isQuadraticResidue(MESSAGE));

        List<Long> encrypted = bob.encrypt((MESSAGE+SALT), alice);

        long c1 = encrypted.get(0);
        long c2 = encrypted.get(1);
        System.out.println("Encrypted: (" + c1 + ", " + c2 + ")");
        System.out.println(c2 + " residue quadratic? " + group.isQuadraticResidue((int) c2));

        long m = alice.decrypt(encrypted);
        System.out.println("Decrypted salted: " + m);
        m -= SALT;
        m %= group.getPrime();
        System.out.println("Decrypted: " + m);
    }

    public static void unsecure() {

        System.out.println("ElGamal cryptography");

        Group group = new Group();
        Person alice = new Person(group);
        Person bob = new Person(group);

        System.out.println("Message: " + (MESSAGE%group.getPrime()) + " residue quadratic? " + group.isQuadraticResidue(MESSAGE));

        List<Long> encrypted = bob.encrypt(MESSAGE, alice);

        long c1 = encrypted.get(0);
        long c2 = encrypted.get(1);
        System.out.println("Encrypted: (" + c1 + ", " + c2 + ")");
        System.out.println(c2 + " residue quadratic? " + group.isQuadraticResidue((int) c2));

        long m = alice.decrypt(encrypted);
        System.out.println("Decrypted: " + m);
    }
}
