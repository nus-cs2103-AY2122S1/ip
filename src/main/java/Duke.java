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
                Processor.process(userInput);
            } catch (IOException e) {
                System.err.println(e.getMessage());
            } catch (BadInputFormatException e) {
                Printer.print("The input is badly formatted.");
            } catch (EmptyCommandException e) {
                Printer.print("Command input cannot be empty!");
            } catch (EmptyDescriptionException e) {
                Printer.print("Missing task description");
            } catch (TaskOutOfRangeException e) {
                Printer.print("Task does not exist!");
            } catch (UnknownCommandException e) {
                Printer.print("Command not found.");
            } catch (InvalidInputException e) {
                Printer.print("Input is invalid");
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
