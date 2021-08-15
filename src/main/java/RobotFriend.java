import java.util.Locale;
import java.util.Scanner;

public class RobotFriend {

    private final static String ROBOT_ICON = "[~o_o~]";
    private final static String BYE = "bye";
    private final static String LINE = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
    private final static String ROBOT_TEXT_SPACE = "         ";

    private static final String[] list = new String[100];
    private static int listIndex = 0;

    /**
     * Prints greeting text of the robotFriend.
     */
    private static void greet() {
        System.out.println(LINE);
        System.out.println(ROBOT_ICON + ": Hello I am your RobotFriend.\n" + ROBOT_TEXT_SPACE + "How can i help you?");
        System.out.println(ROBOT_TEXT_SPACE + "To exit type bye, to view list type list");
        System.out.println(LINE);
    }

    /**
     * Echos the user inputted String in a robot way.
     *
     * @param userInput user inputted String
     */
    private static void echo(String userInput) {
        System.out.println(LINE);
        System.out.println(ROBOT_ICON + ": " + userInput);
        System.out.println(LINE);
    }

    /**
     * Prints the exiting text in a robot way.
     */
    private static void bye() {
        System.out.println(LINE);
        System.out.println(ROBOT_ICON + ": " + "Bye~ Hope to see you soon :)");
        System.out.println(LINE);
    }

    /**
     * Adds user inputted String to global list and prints the user added item
     *
     * @param userInput user inputted String
     */
    private static void addToList(String userInput) {
        list[listIndex] = userInput;
        listIndex++;
        System.out.println(LINE);
        System.out.println(ROBOT_ICON + ": " + "You have added this following item to the list:");
        System.out.println(userInput);
        System.out.println(LINE);

    }
    /**
     * Prints all the items in the list
     */
    private static void printList() {
        System.out.println(LINE);
        System.out.println(ROBOT_ICON + ": " + "Your list contains the following item/s:");
        for (int i = 0; i < listIndex; i++) {
            System.out.println((i + 1) + ". " + list[i]);
        }
        System.out.println(LINE);
    }

    public static void main(String[] args) {
        greet();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String userInput = scanner.next();
            if (userInput.equals(BYE)) {
                bye();
                break;
            } else if (userInput.equals("list")) {
                printList();
            } else {
                addToList(userInput);
            }
        }
    }
}


