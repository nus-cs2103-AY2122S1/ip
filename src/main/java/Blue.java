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
    private static final String GREET_CONTENT = "Hello! I'm Blue\n" + "What can I do for you?";
    private static final String EXIT_CONTENT = "Bye. Hope to never see you again!";
    private static final List<Task> tasks = new ArrayList<>();
    private static final TaskList taskList = new TaskList(tasks);
    private static final ListHandler listHandler = new ListHandler(taskList);
    private static final ToDoHandler toDoHandler = new ToDoHandler(taskList);
    private static final DeadlineHandler deadlineHandler = new DeadlineHandler(taskList);
    private static final EventHandler eventHandler = new EventHandler(taskList);
    private static final DoneHandler doneHandler = new DoneHandler(taskList);


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
                speak(listHandler.handle(input));
                break;
            case Command.TODO:
                try {
                    speak(toDoHandler.handle(input));
                } catch (BlueException e) {
                    speak(e.getMessage());
                }
                break;
            case Command.DEADLINE:
                try {
                    speak(deadlineHandler.handle(input));
                } catch (BlueException e) {
                    speak(e.getMessage());
                }
                break;
            case Command.EVENT:
                try {
                    speak(eventHandler.handle(input));
                } catch (BlueException e) {
                    speak(e.getMessage());
                }
                break;
            case Command.DONE:
                try {
                    speak(doneHandler.handle(input));
                } catch (BlueException e) {
                    speak(e.getMessage());
                }
                break;
            case Command.DELETE:
                handleDelete(input);
                break;
            default:
                speak("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                break;
        }
        return true;
    }

    private static void handleDelete(String input) {
        String[] arguments = getArguments(input);
        if (arguments.length > 0) {
            try {
                int index = Integer.parseInt(arguments[0]) - 1;
                if (0 <= index && index < tasks.size()) {
                    Task task = tasks.remove(index);
                    String content = "Noted. I've removed this task:\n" + task + "\n";
                    content += "Now you have " + tasks.size() + " tasks in the list.";
                    speak(content);
                } else
                    speak("☹ OOPS!!! No task found at index " + (index + 1) + ".");
            } catch (NumberFormatException e) {
                speak("☹ OOPS!!! Index must be a number.");
            }
        } else
            speak("☹ OOPS!!! The index of delete cannot be empty.");
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
