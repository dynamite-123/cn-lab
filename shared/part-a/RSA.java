// Write a program for simple RSA algorithm to encrypt and decrypt the data.
import java.math.BigInteger;
import java.util.Random;
import java.util.Scanner;

public class RSA {

    private BigInteger d;
    private BigInteger e;
    private BigInteger n;

    public RSA(int bitLength) {
        generateKeyPairs(bitLength);
    }

    private void generateKeyPairs(int bitLength) {
        Random random = new Random();
        BigInteger p = BigInteger.probablePrime(bitLength, random);
        BigInteger q = BigInteger.probablePrime(bitLength, random);

        n = p.multiply(q);

        BigInteger phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));

        // Choose public exponent 'e' such that 1 < e < φ(n) and e is coprime to φ(n)
        e = BigInteger.probablePrime(bitLength / 2, random);

        while(phi.gcd(e).intValue() > 1) {
            e = e.add(BigInteger.ONE);
        }

        d = e.modInverse(phi);
    }

    public BigInteger encrypt(BigInteger message) {
        return message.modPow(e, n);
    }

    public BigInteger decrypt(BigInteger encryptedMessage) {
        return encryptedMessage.modPow(d, n);
    }

    public static void main(String[] args) {
        int bitLength = 1024;
        String originalMessage;
        Scanner sc=new Scanner(System.in);

        RSA rsa = new RSA(bitLength);

        System.out.print("Enter a string: ");
        originalMessage=sc.nextLine();

        BigInteger message = new BigInteger(originalMessage.getBytes());
        
        BigInteger encryptedMessage = rsa.encrypt(message);
        System.out.println("Encrypted message: " + encryptedMessage);
        
        BigInteger decryptedMessage = rsa.decrypt(encryptedMessage);
        System.out.println("Decrypted message: " + new String(decryptedMessage.toByteArray()));
    }
}