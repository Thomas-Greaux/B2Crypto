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

    public void sendEncrypted(int m, Person dest) {

        System.out.println("Message: " + m);

        List<Integer> dest_publicKey = dest.getPublicKey();
        int eph_key = new Random().nextInt(order-2)+1;
        int c1 = (int) (Math.pow(generator, eph_key) % (order+1));
        int s = (int) (Math.pow(dest_publicKey.get(2), eph_key) % (order+1));
        int c2 = s*m % (order+1);

        System.out.println("Encrypted: (" + c1 + ", " + c2 + ")");

        List<Integer> encrypted = new ArrayList<>();
        encrypted.add(c1);
        encrypted.add(c2);
        dest.receive(encrypted);
    }

    private void receive(List<Integer> encrypted) {
        int c1 = encrypted.get(0);
        int c2 = encrypted.get(1);
        int s = (int) (Math.pow(c1, privateKey) % (order+1));
        int inv_s = Person.findModularMultiplicativeInverse(s, order+1);
        int m = inv_s*c2 % (order+1);

        System.out.println("Decrypted: " + m);
    }

    public static int findModularMultiplicativeInverse(int a, int m) {
        for (int i = 1; i < m; i++) {
            if((i*a)%m == 1) {
                return i;
            }
        }
        return 0;
    }
}
