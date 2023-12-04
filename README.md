# Main
Main Repository with branches of work

RC4 Info:  RC4 Java code takes an input file that has:
    1. First line is the key, represented by integers (base-10), separated by one blank space.
    2. Second line is the plaintext, represented by (even number of) hexadecimal digits.
    3. Third line is the ciphertext, represented by (even number of) hexadecimal digits.
and spits an output file that has:
    1. First line is the ciphertext, represented by hexadecimal digits, which is the encryption result of the plaintext in input.txt.
    2. Second line is the plaintext, represented by hexadecimal digits, which is the decryption result of the ciphertext in input.txt.

RSA Info:  RSA Java code that takes an input file that has:
    1. First line is a prime number p, represented by hexadecimal digits.
    2. Second line is another prime number q, represented by hexadecimal digits.
    3. Third line is a randomly selected integer e, represented by hexadecimal digits, satisfying
    GCD(e,(n)) = 1 where n = p * q.
    4. Fourth line is the plaintext, represented by hexadecimal digits.
    5. Fifth line is the ciphertext, represented by hexadecimal digits.
and spits an output file that has:
    1. First line is d, represented by hexadecimal digits, satisfying d = e−1 mod (n).
    2. Second line is the ciphertext, presented by hexadecimal digits, which is the encryption result of the plaintext in input.txt.
    3. Third line is the plaintext, presented by hexadecimal digits, which is the decryption result of the ciphertext in input.txt.
