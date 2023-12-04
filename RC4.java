package RC4_Stuff;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class RC4 {

    public static void main(String[] args) {
        // Read input from a file
        String inputFile = "input.txt";
        String outputFile = "output.txt";

        try {
            String[] inputLines = readLinesFromFile(inputFile);

            // Parse key, plaintext, and ciphertext
            int[] key = IntegerParser.parseString(inputLines[0]);
            int[] plaintext = HexadecimalParser.parseHexadecimalString(inputLines[1]);
            int[] ciphertext = HexadecimalParser.parseHexadecimalString(inputLines[2]);

            // Encrypt and decrypt using RC4
            int[] encrypted = rc4Cipher(key, plaintext);
            int[] decrypted = rc4Cipher(key, ciphertext);

            // Write the results to an output file
            writeResultsToFile(outputFile, encrypted, decrypted);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String[] readLinesFromFile(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String[] lines = new String[3];
        for (int i = 0; i < 3; i++) {
            lines[i] = reader.readLine();
        }
        reader.close();
        return lines;
    }

    private static void writeResultsToFile(String filename, int[] encrypted, int[] decrypted) throws IOException {
        FileWriter writer = new FileWriter(filename);

        // Convert encrypted and decrypted arrays to hexadecimal strings
        String hexEncrypted = HexConverter.convertToHex(encrypted);
        String hexDecrypted = HexConverter.convertToHex(decrypted);

        writer.write(hexEncrypted + "\n");
        writer.write(hexDecrypted + "\n");

        writer.close();
    }

    private static int[] rc4Cipher(int[] key, int[] data) {
        // RC4 Key Scheduling Algorithm
        int[] sbox = new int[256];
        int[] keyStream = new int[data.length];
        int i, j;

        for (i = 0; i < 256; i++) {
            sbox[i] = i;
        }

        j = 0;
        for (i = 0; i < 256; i++) {
            j = (j + sbox[i] + key[i % key.length]) % 256;
            // Swap values
            int temp = sbox[i];
            sbox[i] = sbox[j];
            sbox[j] = temp;
        }

        // RC4 Pseudo-Random Generation Algorithm
        i = 0;
        j = 0;
        for (int k = 0; k < data.length; k++) {
            i = (i + 1) % 256;
            j = (j + sbox[i]) % 256;
            // Swap values
            int temp = sbox[i];
            sbox[i] = sbox[j];
            sbox[j] = temp;

            int keyByte = sbox[(sbox[i] + sbox[j]) % 256];
            keyStream[k] = keyByte ^ data[k];
        }

        return keyStream;
    }

    static class IntegerParser {
        public static int[] parseString(String input) {
            // Remove spaces from the input string
            String[] stringArray = input.split("\\s");

            // Parse each string as an integer and store in an array
            int[] resultArray = new int[stringArray.length];
            for (int i = 0; i < stringArray.length; i++) {
                resultArray[i] = Integer.parseInt(stringArray[i]);
            }

            return resultArray;
        }
    }

    static class HexadecimalParser {
        public static int[] parseHexadecimalString(String input) {
            // Remove spaces from the input string
            String stringWithoutSpaces = input.replaceAll("\\s", "");

            // Convert the hexadecimal string to an array of integers
            int[] resultArray = new int[stringWithoutSpaces.length() / 2];
            for (int i = 0; i < stringWithoutSpaces.length(); i += 2) {
                String hexPair = stringWithoutSpaces.substring(i, i + 2);
                resultArray[i / 2] = Integer.parseInt(hexPair, 16);
            }

            return resultArray;
        }
    }

    static class HexConverter {
        public static String convertToHex(int[] data) {
            StringBuilder hexString = new StringBuilder();
            for (int value : data) {
                // Ensure the value is within the range [0, 255]
                int positiveValue = value & 0xFF; // Mask with 0xFF to get the last 8 bits
                // Convert to hexadecimal and append to the string
                hexString.append(String.format("%02X", positiveValue));
            }
            return hexString.toString();
        }
    }
}
