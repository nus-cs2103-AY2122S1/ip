import java.util.Scanner;

/**
 * Class responsible for interactions with the user
 */
public class UI {
    public static void welcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Stay on track with Duke!\n" +
                "How can I help you?");
    }

    public static void bye() {
        System.out.println("¡Adiós! See you soon!");
    }

    public static String readCommand() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your command:");
        return sc.nextLine();
    }

    public static void dukeException() {
        System.out.println("There seems to be an error with Duke." +
                "Please try again!");
    }

    public static void done(int taskNumber) {
        System.out.printf("Solid work! I've marked task %d as done.%n", taskNumber);
    }

    public static void added(String taskType) {
        System.out.printf("Great! I've added your %s. Enter 'list' to see your tasks!%n", taskType);
    }

    public static void delete(int taskNumber) {
        System.out.printf("Got it! I've removed task %d.%n", taskNumber);
    }

    public static void numberOfTasks(int numberOfTasks) {
        System.out.printf("You currently have %d tasks.%n", numberOfTasks);
    }
}
