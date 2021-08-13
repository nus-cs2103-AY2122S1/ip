import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

public class Duke {
    /** Handles user input for the program. */
    private static void run() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        // Greeting
        Printer.print(
                "\033[3m*booting up......*\033[0m",
                "I'm the Hui Zhuan Bot v1.0!",
                "What do you want me to do?");

        while (true) {
            try {
                String userInput = reader.readLine();
                if (Objects.equals(userInput, "bye")) {
                    break;
                }
                Printer.print(userInput);
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }

        // Goodbye
        Printer.print("Bye. See ya l8er allig8er!", "\033[3m*shutting down......*\033[0m");
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        run();
    }
}
