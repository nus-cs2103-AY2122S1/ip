import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static String command;
    private static ArrayList<String> arr = new ArrayList<>();
    private static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        do {
            System.out.print(">> ");
            command = in.next();
            handleInput(command);
        }
        while(!command.equals("bye"));
        System.out.println("Bye, hope to see you again!");
    }

    /**
     * Handles the different input commands to Duke.
     * @param command input command from the user
     */
    public static void handleInput(String command) {
        switch (command) {
            case "bye":
                break;
            case "list":
                for (int i = 0; i < arr.size(); i++) {
                    System.out.println(i + 1 + ": " + arr.get(i));;
                }
                break;
            default:
                arr.add(command);
                System.out.println("Added " + command);
        }
    }
}
