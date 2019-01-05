package fr.polytech.elgamalgreaux;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Person {
    private int order;
    private int privateKey;
    private int generator;
    private int sharedValue;

    public Person(int order, int generator) {
        this.order = order;
        this.generator = generator;
        Random rand = new Random();
        privateKey = rand.nextInt(order-2)+1; //Value between 1 and order-1
        sharedValue = (int) Math.pow(generator, privateKey) % (order+1);
    }

    public int getOrder() {
        return order;
    }

    public int getPrivateKey() {
        return privateKey;
    }

    public int getGenerator() {
        return generator;
    }

    public int getSharedValue() {
        return sharedValue;
    }

    public List<Integer> getPublicKey() {
        List<Integer> res = new ArrayList<>();
        res.add(order);
        res.add(generator);
        res.add(sharedValue);
        return res;
    }
}
