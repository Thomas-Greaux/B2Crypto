package fr.polytech.elgamalgreaux;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Group {

    //TODO: make it as parameters
    private final int order = 5;
    private final int prime = 11;
    //private final int prime = 2099;
    //private final int order = 1049;
    private long generator;

    public static void main(String[] args) {
        System.out.println(myPow(2, 3, 5));
        System.out.println(myPow(2, 7, 5));
    }

    public Group(){
        generator = findGenerator();
    }

    public boolean isQuadraticResidue(int k) {
        return jacobi(k) == 1;
    }

    public int jacobi(int k) {
        return (int) myPow(k, (prime-1)/2, prime);
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
        long tmp = myPow(g, order, prime);
        return (tmp == 1) && (myPow(g, 2, prime) != 1);
    }

    public static long myPow(long a, long b, long m) {
        long tmp = a;
        if(b == 0) return 1;
        for (long i = 1; i < b; i++) {
            tmp *= a;
            tmp %= m;
        }
        return tmp;
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
