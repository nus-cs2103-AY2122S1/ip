import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Blue {
    private static final String LOGO = " ____  _                \n"
            + "|  . \\| | _   _   ____  \n"
            + "|____/| || | | | /  _  \\\n"
            + "|  . \\| || |_| ||   ___/\n"
            + "|____/|_||_____| \\_____/\n";
    private static final String GREET_CONTENT = "Hello! I'm Blue\n"
            + "What can I do for you?";
    private static final String ERROR_CONTENT
            = "Oops something went wrong... Are you sure your input is correct?";
    private static final String EXIT_CONTENT
            = "Bye. Hope to never see you again!";
    private static final List<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println(LOGO);
        greet();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            boolean shouldContinue = handle(input);
            if (!shouldContinue)
                break;
        }
        scanner.close();
    }

    private static void greet() {
        speak(GREET_CONTENT);
    }

    private static boolean handle(String input) {
        String command = getCommand(input);
        if (command.equals(Command.EXIT)) {
            speak(EXIT_CONTENT);
            return false;
        }
        switch (command) {
            case Command.LIST:
                listTasks();
                break;
            case Command.TODO:
                handleToDo(input);
                break;
            case Command.DEADLINE:
                handleDeadline(input);
                break;
            case Command.EVENT:
                handleEvent(input);
                break;
            case Command.DONE:
                handleDone(input);
                break;
            default:
                storeAndDisplayTasks(input);
                break;
        }
        return true;
    }

    private static void storeAndDisplayTasks(String title) {
        Task task = new Task(title);
        tasks.add(task);
        String content = "added: " + title;
        speak(content);
    }

    private static void listTasks() {
        String content = "Here are the tasks in your list:\n";
        String[] lines = new String[tasks.size()];
        for (int i = 0; i < tasks.size(); i++) {
            lines[i] = (i + 1) + ". " + tasks.get(i);
        }
        content += String.join("\n", lines);
        speak(content);
    }

    private static void handleToDo(String input) {
        if (input.contains(" ")) {
            int index = input.indexOf(" ");
            String title = input.substring(index + 1);
            ToDo toDo = new ToDo(title);
            tasks.add(toDo);
            String content = "Got it. I've added this task:\n" + toDo + "\n";
            content += "Now you have " + tasks.size() + " tasks in the list.";
            speak(content);
        } else
            speak(ERROR_CONTENT);
    }

    private static void handleDeadline(String input) {
        if (input.contains(" /by ")) {
            int indexSpace = input.indexOf(" ");
            int indexBy = input.indexOf(" /by ");
            String title = input.substring(indexSpace + 1, indexBy);
            String by = input.substring(indexBy + 5);
            Deadline deadline = new Deadline(title, by);
            tasks.add(deadline);
            String content = "Got it. I've added this task:\n" + deadline + "\n";
            content += "Now you have " + tasks.size() + " tasks in the list.";
            speak(content);
        } else
            speak(ERROR_CONTENT);
    }

    private static void handleEvent(String input) {
        if (input.contains(" /at ")) {
            int indexSpace = input.indexOf(" ");
            int indexAt = input.indexOf(" /at ");
            String title = input.substring(indexSpace + 1, indexAt);
            String at = input.substring(indexAt + 5);
            Event event  = new Event(title, at);
            tasks.add(event);
            String content = "Got it. I've added this task:\n" + event + "\n";
            content += "Now you have " + tasks.size() + " tasks in the list.";
            speak(content);
        } else
            speak(ERROR_CONTENT);
    }

    private static void handleDone(String input) {
        String[] arguments = getArguments(input);
        if (arguments.length > 0) {
            int index = Integer.parseInt(arguments[0]) - 1;
            if (index < tasks.size()) {
                Task task = tasks.get(index);
                task.markDone();
                String content = "Nice! I've marked this task as done:\n" + task;
                speak(content);
                return;
            }
        }
        speak(ERROR_CONTENT);
    }

    private static String getCommand(String input) {
        if (input.length() > 0)
            return input.split(" ")[0];
        else
            return "";
    }

    private static String[] getArguments(String input) {
        if (input.length() > 0) {
            String[] split = input.split(" ");
            if (split.length >= 2)
                return Arrays.copyOfRange(split, 1, split.length);
        }
        return new String[]{};
    }

    private static void speak(String content) {
        String line = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
        System.out.println(line);
        System.out.println(content);
        System.out.println(line);
    }
}
