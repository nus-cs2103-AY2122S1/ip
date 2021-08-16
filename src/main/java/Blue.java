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
    private static final String UNKNOWN_CONTENT = "Oops something went wrong";
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
        if (command.equals(Command.LIST))
            listTasks();
        else if (command.equals(Command.DONE))
            handleDone(input);
        else
            storeAndDisplayTasks(input);
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
            String intString = Integer.toString(i + 1);
            Task task = tasks.get(i);
            lines[i] = intString + ". " + task;
        }
        content += String.join("\n", lines);
        speak(content);
    }

    private static void handleDone(String input) {
        String[] arguments = getArguments(input);
        if (arguments.length > 0) {
            int index = Integer.parseInt(arguments[0]) - 1;
            if (index < tasks.size()) {
                Task task = tasks.get(index);
                task.markDone();
                String content = "Nice! I've marked this task as done:\n";
                content += task;
                speak(content);
                return;
            }
        }
        speak(UNKNOWN_CONTENT);
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
