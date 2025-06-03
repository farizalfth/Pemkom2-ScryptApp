import com.lambdaworks.crypto.SCrypt;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== SCrypt Hashing ===");

        System.out.print("Masukkan teks yang ingin di-hash: ");
        String input = scanner.nextLine();

        try {
            // Buat salt acak
            byte[] salt = generateSalt(16);

            // Parameter scrypt: N = 16384, r = 8, p = 1, outputLength = 32 bytes
            byte[] hash = SCrypt.scrypt(input.getBytes(), salt, 16384, 8, 1, 32);

            // Encode ke Base64 supaya bisa dibaca manusia
            String encodedSalt = Base64.getEncoder().encodeToString(salt);
            String encodedHash = Base64.getEncoder().encodeToString(hash);

            System.out.println("\nSalt (Base64): " + encodedSalt);
            System.out.println("Hash (Base64): " + encodedHash);
            System.out.println("\nKombinasi disimpan: " + encodedSalt + "$" + encodedHash);

        } catch (Exception e) {
            System.out.println("Terjadi kesalahan: " + e.getMessage());
        }
    }

    public static byte[] generateSalt(int length) {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[length];
        random.nextBytes(salt);
        return salt;
    }
}
