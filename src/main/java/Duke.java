import java.util.Scanner;

public class Duke {
    private static boolean inSession;
    private static String greetMessage = "Hello! I'm Duke \nWhat can I do for you?";
    private static String exitMessage = "Bye. Hope to see you again soon!";

    public static void start() {
        inSession = true;
        greet();
        while (inSession) {
            Scanner sc = new Scanner(System.in);
            String input = sc.nextLine().toLowerCase();
            switch (input) {
                case ("bye"):
                    exit();
                    break;
                default:
                    echo(input);
            }
        }
    }

    public static void greet() {
        System.out.println(greetMessage);
    }

    public static void exit() {
        inSession = false;
        System.out.println(exitMessage);
    }

    public static void echo(String input) {
        System.out.println(input);
    }

    public static void main(String[] args) {
        Duke.start();
    }
}
