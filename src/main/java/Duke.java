import java.util.Scanner;

public class Duke {
    /**
     * Prints a line.
     */
    private static void printLine() {
        System.out.println("_________________________________");
    }

    /**
     * Prints the greeting message.
     */
    private static void greetUser() {
        printLine();
        System.out.println("Greetings! I am Duke.");
        System.out.println("How can I assist you?");
        printLine();
    }

    /**
     * Prints the farewell message.
     */
    private static void farewellUser() {
        printLine();
        System.out.println("Bye! See you soon!");
        printLine();
    }

    /**
     * Interact with the user.
     *
     * @param args array of strings.
     */
    public static void main(String[] args) {
        greetUser();

        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        while (!userInput.equalsIgnoreCase("bye")) {
            printLine();
            System.out.println(userInput);
            printLine();
            userInput = scanner.nextLine();
        }
        scanner.close();

        farewellUser();
    }
}
