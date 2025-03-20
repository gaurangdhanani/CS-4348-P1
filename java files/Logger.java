import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Logger {
    public static void main(String[] args) {
        // Ensure a log file name is provided
        if (args.length != 1) {
            System.err.println("Usage: java Logger <log_filename>");
            System.exit(1);
        }

        String logFileName = args[0];

        try (PrintWriter writer = new PrintWriter(new FileWriter(logFileName, true));
             Scanner scanner = new Scanner(System.in)) {

            while (scanner.hasNextLine()) {
                String input = scanner.nextLine().trim();

                // Stop logging when "QUIT" is received
                if ("QUIT".equalsIgnoreCase(input)) {
                    writer.flush();
                    System.out.println("Logger shutting down.");
                    break;
                }

                // Split input into action and message
                String[] parts = input.split("\\s+", 2);
                if (parts.length < 2) {
                    System.err.println("Invalid log format. Must be: ACTION MESSAGE");
                    continue;
                }

                String action = parts[0].toUpperCase();
                String message = parts[1];

                // Get current timestamp in YYYY-MM-DD HH:MM format
                String timestamp = LocalDateTime.now()
                        .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

                // Format and write log entry
                String logEntry = String.format("%s [%s] %s", timestamp, action, message);
                writer.println(logEntry);
                writer.flush();  // Ensure immediate writing to file

                System.out.println("Logged: " + logEntry);
            }

        } catch (IOException e) {
            System.err.println("Error writing to log file: " + e.getMessage());
            System.exit(1);
        }
    }
}
