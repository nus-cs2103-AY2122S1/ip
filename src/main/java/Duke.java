import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String ENDING_COMMAND = "bye";

    private static final List<Task> taskList = new ArrayList<>();

    private static void say(String message) {
        System.out.printf("Iris: %s%n", message);
    }

    private static void say(String message, boolean isFirst) {
        String name = isFirst ? "Iris:": "     ";
        System.out.printf("%s %s%n", name, message);
    }

    private static void sayError(IrisException exception) {
        say(exception.getMessage());
    }

    private static String prompt() {
        System.out.print("me: ");
        return scanner.nextLine();
    }

    private static void echo(String message) {
        say(message);
    }

    private static void sayTaskAdded() {
        int count = taskList.size();
        say("Got it. I've added this task:");
        say(taskList.get(count - 1).toString(), false);
        say(String.format("Now you have %d %s in the list.",
                count, count == 1 ? "task" : "tasks"), false);
    }

    private static void addTodo(String item) {
        taskList.add(new ToDo(item));
    }

    private static void addDeadline(String item, String by) {
        taskList.add(new Deadline(item, by));
    }

    private static void addEvent(String item, String at) {
        taskList.add(new Event(item, at));
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

    private static String getMetadata(String command) throws IrisException {
        String[] splitted = command.split(" ", 2);
        if (splitted.length == 1 || splitted[1].equals("")) {
            throw new IrisException("The description cannot be empty");
        } else {
            return splitted[1];
        }
    }

    private static void handleCommand(String command) throws IrisException {
        if (command.equals("list")) {
            listTasks();
        } else if (command.startsWith("done")) {
            done(Integer.parseInt(getMetadata(command)));
        } else if (command.startsWith("todo")) {
            addTodo(getMetadata(command));
            sayTaskAdded();
        } else if (command.startsWith("deadline")) {
            String[] splitted = getMetadata(command).split(" /by ");
            addDeadline(splitted[0], splitted[1]);
            sayTaskAdded();
        } else if (command.startsWith("event")) {
            String[] splitted = getMetadata(command).split(" /at ");
            addEvent(splitted[0], splitted[1]);
            sayTaskAdded();
        } else {
            throw new IrisException("I'm sorry, but I don't know what that means.");
        }
    }

    public static void main(String[] args) {
        say("Hello! I'm Iris. What can I do for you?");
        String command = prompt();
        while (!command.equals(ENDING_COMMAND)) {
            try {
                handleCommand(command);
            } catch (IrisException exception) {
                sayError(exception);
            }
            command = prompt();
        }

        say("Bye. Hope to see you again soon!");
    }
}
