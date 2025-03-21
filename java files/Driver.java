import java.io.*;
import java.util.*;

public class Driver {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: java Driver <log_filename>");
            return;
        }

        String logFile = args[0];
        List<String> history = new ArrayList<>();

        try {
            // Start Logger
            Process logger = new ProcessBuilder("java", "Logger", logFile).start();
            BufferedWriter logWriter = new BufferedWriter(new OutputStreamWriter(logger.getOutputStream()));

            // Start Encryption
            Process encryption = new ProcessBuilder("java", "Encryption").start();
            BufferedWriter encWriter = new BufferedWriter(new OutputStreamWriter(encryption.getOutputStream()));
            BufferedReader encReader = new BufferedReader(new InputStreamReader(encryption.getInputStream()));

            Scanner scanner = new Scanner(System.in);
            log(logWriter, "START", "Driver started.");

            while (true) {
                System.out.println("\nCommands: password, encrypt, decrypt, history, quit");
                System.out.print("Enter command: ");
                String command = scanner.nextLine().trim().toLowerCase();

                switch (command) {
                    case "password":
                        String pass = getInput(scanner, history, false);
                        if (pass == null) continue;

                        encWriter.write("PASS " + pass + "\n");
                        encWriter.flush();
                        String passResponse = encReader.readLine();
                        System.out.println(passResponse);
                        log(logWriter, "COMMAND", "password set");
                        log(logWriter, "RESULT", passResponse);
                        break;

                    case "encrypt":
                        String plain = getInput(scanner, history, true);
                        if (plain == null) continue;

                        encWriter.write("ENCRYPT " + plain + "\n");
                        encWriter.flush();
                        String encResp = encReader.readLine();
                        System.out.println(encResp);
                        log(logWriter, "COMMAND", "encrypt " + plain);
                        log(logWriter, "RESULT", encResp);

                        if (encResp.startsWith("RESULT")) {
                            history.add(plain);
                            history.add(encResp.substring(7).trim());
                        }
                        break;

                    case "decrypt":
                        String cipher = getInput(scanner, history, true);
                        if (cipher == null) continue;

                        encWriter.write("DECRYPT " + cipher + "\n");
                        encWriter.flush();
                        String decResp = encReader.readLine();
                        System.out.println(decResp);
                        log(logWriter, "COMMAND", "decrypt " + cipher);
                        log(logWriter, "RESULT", decResp);

                        if (decResp.startsWith("RESULT")) {
                            history.add(cipher);
                            history.add(decResp.substring(7).trim());
                        }
                        break;

                    case "history":
                        if (history.isEmpty()) {
                            System.out.println("History is empty.");
                        } else {
                            for (int i = 0; i < history.size(); i++) {
                                System.out.println(i + ": " + history.get(i));
                            }
                        }
                        break;

                    case "quit":
                        log(logWriter, "EXIT", "Driver exiting.");
                        encWriter.write("QUIT\n");
                        encWriter.flush();
                        logWriter.write("QUIT\n");
                        logWriter.flush();

                        scanner.close();
                        encWriter.close();
                        encReader.close();
                        logWriter.close();
                        return;

                    default:
                        System.out.println("Invalid command.");
                }
            }

        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    // Write formatted log entries
    private static void log(BufferedWriter writer, String action, String message) throws IOException {
        writer.write(action + " " + message + "\n");
        writer.flush();
    }

    // Unified method to handle user input with or without history
    private static String getInput(Scanner scanner, List<String> history, boolean saveToHistory) {
        String useHistory;
        while (true) {
            System.out.print("Use history? (y/n): ");
            useHistory = scanner.nextLine().trim().toLowerCase();
            if (useHistory.equals("y") || useHistory.equals("n")) {
                break;
            } else {
                System.out.println("Please enter 'y' or 'n' only.");
            }
        }

        if (useHistory.equals("y")) {
            if (history.isEmpty()) {
                System.out.println("History is empty.");
            } else {
                for (int i = 0; i < history.size(); i++) {
                    System.out.println(i + ": " + history.get(i));
                }

                System.out.print("Choose index or -1 to enter new string: ");
                try {
                    int index = Integer.parseInt(scanner.nextLine().trim());
                    if (index >= 0 && index < history.size()) {
                        return history.get(index);
                    } else if (index == -1) {
                        return promptNewString(scanner);
                    } else {
                        System.out.println("Invalid index.");
                        return null;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input.");
                    return null;
                }
            }
        }

        return promptNewString(scanner);
    }


    // Prompts for a new string and validates it
    private static String promptNewString(Scanner scanner) {
        System.out.print("Enter input (letters only): ");
        String input = scanner.nextLine().trim().toUpperCase();

        if (!input.matches("[A-Z]+")) {
            System.out.println("Invalid input. Only letters are allowed.");
            return null;
        }

        return input;
    }
}
