package RSA_Stuff;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.SecureRandom;

public class RSA {
    
    private static final SecureRandom random = new SecureRandom();

    public static void main(String[] args) throws IOException {
        
        // read input parameters from input.txt
        Path inputPath = Paths.get("input.txt");
        String[] inputParams = Files.readAllLines(inputPath, StandardCharsets.UTF_8).toArray(new String[0]);

        BigInteger p = new BigInteger(inputParams[0], 16);
        BigInteger q = new BigInteger(inputParams[1], 16);
        BigInteger e = new BigInteger(inputParams[2], 16);
        BigInteger m = new BigInteger(inputParams[3], 16);
        BigInteger c = new BigInteger(inputParams[4], 16);

        // compute n and phi(n)
        BigInteger n = p.multiply(q);
        BigInteger phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));

        // compute d
        BigInteger d = e.modInverse(phi);

        // write private key information to output.txt
        FileWriter writer = new FileWriter(new File("output.txt"));
        writer.write(d.toString(16) + "\n");

        // encrypt plaintext
        BigInteger ciphertext = m.modPow(e, n);
        writer.write(ciphertext.toString(16) + "\n");

        // decrypt ciphertext
        BigInteger plaintext = c.modPow(d, n);
        writer.write(plaintext.toString(16));
        
        writer.close();
    }
}
