package fr.polytech.elgamalgreaux;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Group {

    //TODO: make it as parameters
    private final int order = 1049;
    private final int prime = 2099;
    private long generator;
    private List<Integer> quadratic_residue = new ArrayList<>();

    public Group(){
        generator = findGenerator();
        for(int i = 1; i < prime; i++) {
            if(!quadratic_residue.contains(i*i)) quadratic_residue.add(i*i);
        }
        Collections.sort(quadratic_residue);
    }

    public boolean isQuadraticResidue(int k) {
        return quadratic_residue.contains(k);
    }

    public int inverse(int k) {
        return (int) (Math.pow(k, prime-2) % prime);
    }

    private int findGenerator() {
        for(int i = 2; i < prime; i++) {
            if(checkGenerator(i)) return i;
        }
        return -1;
    }

    private boolean checkGenerator(int g) {
        /*
        List<Integer> generated_elements = new ArrayList<>();
        for(int i = 0; i < prime; i++) {
            generated_elements.add((int) Math.pow(g, i) % prime);
        }

        Collections.sort(generated_elements);

        for(int i = 0; i < generated_elements.size(); i++) {
            if(i != generated_elements.get(i)) return false;
        }
        return true;
        */

        long tmp = myPow(g, order, prime);
        return tmp == 1;
    }

    public static long myPow(long a, long b, long m) {
        for (long i = 1; i <= b; i++) {
            a *= a;
            a %= m;
        }
        return a;
    }

    public static boolean checkPrime(int p) {
        for (int i = 2; i <= (int) Math.sqrt(p)+1; i++) {
            if (p%i == 0) return false;
        }
        return true;
    }

    public int getOrder() {
        return order;
    }

    public int getPrime() {
        return prime;
    }

    public long getGenerator() {
        return generator;
    }
}
