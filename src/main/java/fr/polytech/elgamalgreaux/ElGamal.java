package fr.polytech.elgamalgreaux;

public class ElGamal {

    public static final int order = 16;

    private int generator;

    public static void main(String[] args) {
        System.out.println("ElGamal cryptography");

        //ElGamal elGamal = new ElGamal();
        Group group = new Group();
        Person alice = new Person(group);
        Person bob = new Person(group);

        bob.sendEncrypted(12, alice);
    }
}
