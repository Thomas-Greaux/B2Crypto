package fr.polytech.elgamalgreaux;

public class Group {

    //TODO: make it as parameters
    private final int order = 5;
    private final int prime = 11;
    //private final int order = 1049;
    //private final int prime = 2099;

    private long generator;

    public Group(){
        generator = findGenerator();
    }

    public boolean isInGroup(int k) {
        return isQuadraticResidue(k) && k > 0 && k < prime;
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
        return isQuadraticResidue(g) && (g != 1);
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
