import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String ENDING_COMMAND = "bye";

    private static List<Task> taskList = new ArrayList<>();

    private static void say(String message) {
        System.out.println(String.format("Iris: %s", message));
    }

    private static void say(String message, boolean isFirst) {
        String name = isFirst ? "Iris:": "     ";
        System.out.printf("%s %s%n", name, message);
    }

    private static String prompt() {
        System.out.print("me: ");
        return scanner.nextLine();
    }

    private static void echo(String message) {
        say(message);
    }

    private static void add(char type, String item) {
        switch(type) {
            case 'T':
                taskList.add(new ToDo(item));
                break;
            case 'D':
                taskList.add(new Deadline(item));
                break;
            case 'E':
                taskList.add(new Event(item));
                break;
            default:
                break;
        }
        say("Got it. I've added this task:");
        say(taskList.get(taskList.size() - 1).toString(), false);
        // TODO: say the number of incomplete tasks in the list
    }

    private static void done(int index) {
        Task task = taskList.get(index - 1);
        task.markComplete();
        say(String.format("Good job! I've marked this task as done: %s", task));
    }

    private static void listTasks() {
        for (int i = 0; i < taskList.size(); i++) {
            say(String.format("%d. %s", i + 1, taskList.get(i)), i == 0);
        }
    }

    public static void main(String[] args) {
        say("Hello! I'm Iris. What can I do for you?");
        String command = prompt();
        while (!command.equals(ENDING_COMMAND)) {
            if (command.equals("list")) {
                listTasks();
            } else if (command.startsWith("done")) {
                String[] splitted = command.split(" ");
                done(Integer.parseInt(splitted[1]));
            } else if (command.startsWith("todo")) {
                String[] splitted = command.split(" ", 2);
                add('T', splitted[1]);
            } else if (command.startsWith("deadline")) {
                // TODO: implement deadline by
                String[] splitted = command.split(" ", 2);
                add('D', splitted[1]);
            } else if (command.startsWith("event")) {
                // TODO: implement event at
                String[] splitted = command.split(" ", 2);
                add('E', splitted[1]);
            }
            command = prompt();
        }

        say("Bye. Hope to see you again soon!");
    }
}
