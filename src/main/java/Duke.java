import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static ArrayList<String> list = new ArrayList<>();

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
     * Adds the task to the list.
     *
     * @param text the text that will be added to the list
     */
    private static void addToList(String text) {
        list.add(text);
    }

    /**
     * Prints all the stored text in the list in order.
     */
    private static void printTasks() {
        for (int i = 0; i < list.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, list.get(i));
        }
    }

    /**
     * Interacts with the user.
     *
     * @param args array of strings.
     */
    public static void main(String[] args) {
        greetUser();

        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        while (!userInput.equalsIgnoreCase("bye")) {
            printLine();
            if (userInput.equals("list")) {
                printTasks();
            } else {
                addToList(userInput);
                System.out.printf("added: %s%n", userInput);
            }
            printLine();
            userInput = scanner.nextLine();
        }
        scanner.close();

        farewellUser();
    }
}
