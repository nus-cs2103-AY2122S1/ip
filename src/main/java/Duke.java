import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    private static TaskManager taskManager = new TaskManager();

    public static String taskDescription(String input) {
        return input.split(" ", 2)[1];
    }

    public static void main(String[] args) {
        Message.greet();
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        while (!input.equals("bye")) {
            String[] words = input.split(" ", 2);
            String command = words[0];

            switch (command) {
                case "list":
                    Message.list();
                    break;
                case "done":
                    TaskManager.markDone(Integer.parseInt(taskDescription(input)));
                    break;
                case "todo":
                case "deadline":
                case "event":
                    TaskManager.addTask(taskDescription(input), command);
                    break;
                default:
                    return;

            }
            input = sc.nextLine();
        }
        Message.exit();
    }
}
