import java.util.Scanner;

/**
 * Duke is a Personal Assistant Chatbot that keeps track of various tasks.
 *
 * @author Adam Oh Zhi Hong
 */
public class Duke {
    public static void main(String[] args) {
        // Welcome the user
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Stay on track with Duke!\n" +
                "How can I help you?");

        Scanner sc = new Scanner(System.in);
        TaskManager tm = new TaskManager();
        boolean running = true;

        // Start taking input from the user
        while (running) {
            System.out.println("Enter your command:");
            String command = sc.nextLine();

            // Handles basic user input such as list and exit
            // All other commands are handled by TaskManager
            switch (command) {
                case "bye":
                    System.out.println("¡Adiós! See you soon!");
                    running = false;
                    break;
                case "list":
                    tm.list();
                    break;
                default:
                    tm.handle(command);
            }
        }
    }
}

