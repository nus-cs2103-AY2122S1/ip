import java.util.Scanner;
import java.lang.String;
/**
 * The Duke class encapsulates the Duke project's chat-bot for CS2103T individual project 1.
 *
 * @author Chesterwongz
 */
public class Duke {

    private static final String[] todoList = new String[100];
    private static int listIndex = 0;

    /**
     * Frames the message with underscore lines.
     *
     * @param msg The String we want to frame.
     * @return The framed String.
     */
    private static void print(String msg) {
        String line = "________________________________";
        System.out.println(line);
        System.out.println(msg);
        System.out.println(line);
        System.out.println();
    }

    /**
     * Prints the Duke logo.
     */
    private static void logo(){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|";
        print("Hello from\n" + logo);
    }
    /**
     * Prints our greeting.
     */
    private static void greet() {
        String greeting = "Hello! I'm Duke\n"
                + "What can I do for you?";
        print(greeting);
    }
    /**
     * Prints our goodbye.
     */
    private static void bye() {
        String bye = "Bye! Hope to see you again soon!";
        print(bye);
    }
    /**
     * Echos the user's input.
     */
    private static void echo(String msg) {
        print(msg);  // Output user input
    }

    private static void addToList(String str) {
        todoList[listIndex++] = str;
        print("added: " + str);
    }

    private static void printList() {
        int index = 1;
        String line = "________________________________";
        System.out.println(line);
        for (String item : todoList) {
            if (item == null) {
                break;
            }
            System.out.println(index++ + ". " + item);
        }
        System.out.println(line);
        System.out.println();
    }

    public static void main(String[] args) {
        logo();
        greet();
        Scanner sc = new Scanner(System.in);
        boolean isExited = false;
        while (!isExited) {
            // String input
            String input = sc.nextLine();
            switch (input) {
                case "bye":
                    isExited = true;
                    bye();
                    break;
                case "list":
                    printList();
                    break;
                default:
                    addToList(input);
            }
        }
    }
}
