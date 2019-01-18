package fr.polytech.elgamalgreaux;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Person {
    private long privateKey;
    private long sharedValue;
    private Group group;

    public Person(Group group) {
        this.group = group;
        Random rand = new Random();
        privateKey = rand.nextInt(group.getOrder()-2)+1; //Value between 1 and order-1
        sharedValue = Group.myPow(group.getGenerator(), privateKey, group.getPrime());
    }

    public long getPrivateKey() {
        return privateKey;
    }

    public long getSharedValue() {
        return sharedValue;
    }

    public List<Long> getPublicKey() {
        List<Long> res = new ArrayList<>();
        res.add((long) group.getOrder());
        res.add(group.getGenerator());
        res.add(sharedValue);
        return res;
    }

    public void sendEncrypted(int m, Person dest) {

        // We make sure that the message is in the group
        m %= group.getPrime();

        System.out.println("Message: " + m + " residue quadratic? " + group.isQuadraticResidue(m));

        List<Long> dest_publicKey = dest.getPublicKey();
        long eph_key = new Random().nextInt(group.getOrder()-2)+1;
        long c1 = Group.myPow(group.getGenerator(), eph_key, group.getPrime());
        long s = Group.myPow(dest_publicKey.get(2), eph_key, group.getPrime());
        long c2 = s*m % group.getPrime();

        System.out.println("Encrypted: (" + c1 + ", " + c2 + ")");
        System.out.println(c2 + " residue quadratic? " + group.isQuadraticResidue((int) c2));

        List<Long> encrypted = new ArrayList<>();
        encrypted.add(c1);
        encrypted.add(c2);
        dest.receive(encrypted);
    }

    private void receive(List<Long> encrypted) {
        long c1 = encrypted.get(0);
        long c2 = encrypted.get(1);
        int s = (int) Group.myPow(c1, privateKey, group.getPrime());
        long inv_s = group.inverse(s);
        long m = inv_s*c2 % group.getPrime();

        System.out.println("Decrypted: " + m);
    }
}
