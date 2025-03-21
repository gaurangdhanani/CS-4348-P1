import java.util.Scanner;

public class Encryption {
    private static String passKey = null;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) continue;

            String[] parts = input.split("\\s+", 2);
            String command = parts[0].toUpperCase();
            String argument = (parts.length > 1) ? parts[1].toUpperCase() : "";

            switch (command) {
                case "PASS":
                    handlePass(argument);
                    break;
                case "ENCRYPT":
                    handleEncrypt(argument);
                    break;
                case "DECRYPT":
                    handleDecrypt(argument);
                    break;
                case "QUIT":
                    scanner.close();
                    return;
                default:
                    System.out.println("ERROR Invalid command.");
            }
        }
    }

    private static void handlePass(String key) {
        if (key.matches("[A-Z]+")) {
            passKey = key;
            System.out.println("RESULT");
        } else {
            System.out.println("ERROR Passkey must contain only uppercase letters.");
        }
    }

    private static void handleEncrypt(String text) {
        if (passKey == null) {
            System.out.println("ERROR Password not set");
            return;
        }

        if (!text.matches("[A-Z]+")) {
            System.out.println("ERROR Input must contain only uppercase letters.");
            return;
        }

        System.out.println("RESULT " + vigenere(text, passKey, true));
    }

    private static void handleDecrypt(String text) {
        if (passKey == null) {
            System.out.println("ERROR Password not set");
            return;
        }

        if (!text.matches("[A-Z]+")) {
            System.out.println("ERROR Input must contain only uppercase letters.");
            return;
        }

        System.out.println("RESULT " + vigenere(text, passKey, false));
    }

    private static String vigenere(String text, String key, boolean encrypt) {
        StringBuilder result = new StringBuilder();
        int keyLen = key.length();

        for (int i = 0; i < text.length(); i++) {
            char textChar = text.charAt(i);
            char keyChar = key.charAt(i % keyLen);

            int textVal = textChar - 'A';
            int keyVal = keyChar - 'A';
            int shifted;

            if (encrypt) {
                shifted = (textVal + keyVal) % 26;
            } else {
                shifted = (textVal - keyVal + 26) % 26;
            }

            result.append((char) ('A' + shifted));
        }

        return result.toString();
    }
}
