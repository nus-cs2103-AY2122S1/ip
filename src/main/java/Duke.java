import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static String command;
    private static ArrayList<Task> arr = new ArrayList<>();
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
            command = in.nextLine();
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
        String[] inputWords = command.split(" ", 2);
        switch (inputWords[0]) {
            case "bye":
                break;
            case "list":
                for (int i = 0; i < arr.size(); i++) {
                    System.out.println(i + 1 + ": " + arr.get(i));;
                }
                break;
            case "done":
                int taskId = Integer.parseInt(inputWords[1]);
                arr.get(taskId - 1).setIsDone(true);
                System.out.println("Nice! I've marked this task as done.");
                System.out.println("   " + arr.get(taskId - 1));
                break;
            default:
                arr.add(new Task(command));
                System.out.println("Added " + command);
        }
    }
}
