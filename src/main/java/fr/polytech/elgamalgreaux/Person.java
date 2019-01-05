package fr.polytech.elgamalgreaux;

import java.util.Random;

public class Person {
    private int order;
    private int privateKey;

    public Person(int order) {
        this.order = order;
        Random rand = new Random();
        privateKey = rand.nextInt(order-2)+1; //Value between 1 and order-1
    }

    public int getOrder() {
        return order;
    }

    public int getPrivateKey() {
        return privateKey;
    }
}
