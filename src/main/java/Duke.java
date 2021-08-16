import java.util.Scanner;

public class Duke {

    final static String EXIT = "bye";

    /**
     * Display formatted message.
     * @param message Message to be printed in console.
     */
    public static void display(String message) {
        System.out.println("    * * * * * * * * * * * * * * * * * * * *");
        System.out.println("    " + message);
        System.out.println("    * * * * * * * * * * * * * * * * * * * *\n");
    }

    /**
     * Display greeting message.
     */
    public static void greet() {
        display("Hi, I'm Sync-Me Sebby.\n    " +
                "I'm here to assist you with tracking and synchronizing of your personal tasks.\n    " +
                "Let me know how I can help?");
    }

    /**
     * Display exit message.
     */
    public static void exit() {
        display("Goodbye. See you again soon!");
    }

    public static void main(String[] args) {

        // initialize Scanner object
        Scanner scan = new Scanner(System.in);

        // initialize TaskList
        TaskList tasks = new TaskList();

        // print greeting message
        greet();

        // read user input
        String userInput = scan.nextLine();

        while (!userInput.equals(EXIT)) {
            if (userInput.equals("list")) {
                tasks.displayAllItems();
            } else {
                tasks.add(userInput);
                display("added: " + userInput);
            }
            userInput = scan.nextLine();
        }

        scan.close();

        // print exit message
        exit();

    }

}
