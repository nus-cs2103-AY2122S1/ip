import java.util.Scanner;

public class RobotFriend {

    public final static String ROBOT_ICON = "[~o_o~]";
    public final static String BYE = "bye";
    public final static String LINE = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
    public final static String NEW_LINE_FOR_ROBOT = "\n         ";

    /**
     * Prints greeting text of the robotFriend.
     */
    public static void greet() {
        System.out.println(LINE);
        System.out.println(ROBOT_ICON + ": Hello I am your RobotFriend." + NEW_LINE_FOR_ROBOT + "How can i help you?");
        System.out.println(LINE);
    }

    /**
     * Echos the user inputted String in a robot way.
     *
     * @param userInput user inputted String
     */
    public static void echo(String userInput) {
        System.out.println(LINE);
        System.out.println(ROBOT_ICON + ": " + userInput);
        System.out.println(LINE);
    }
    /**
     * Prints the exiting text in a robot way.
     */
    public static void bye() {
        System.out.println(LINE);
        System.out.println(ROBOT_ICON + ": " + "Bye~ Hope to see you soon :)");
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
            }
            echo(userInput);
        }
    }
}


