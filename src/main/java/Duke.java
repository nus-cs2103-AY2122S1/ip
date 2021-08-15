import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Duke {

    private static final String SEPARATOR = "_".repeat(50);
    private static final String WELCOME_MESSAGE = "Hello! I'm Duke\nWhat can I do for you?";
    private static final String BYE_MESSAGE = "Bye. Hope to see you again soon!";
    private static final String TAB = " ".repeat(4);

    private static void parseInput(String input, List<Task> tasks) throws DukeException {
        String[] commands = input.split("\\s+");
        String keyWord = commands[0];
        String taskDescription;
        String extraDetails;
        String message = "";
        Task newTask = null;
        int index = commands.length;
        switch(keyWord) {
            case "list":
                if (commands.length != 1) throw new UnknownCommandException();
                if (tasks.size() == 0) {
                    message = "No tasks added so far!";
                } else {
                    index = 1;
                    for (Task task : tasks) {
                        message += String.format("%d. %s\n", index, task);
                        index++;
                    }
                    message = message.substring(0, message.length() - 1);
                }
                break;
            case "todo":
                taskDescription = String.join(" ", Arrays.copyOfRange(commands, 1, commands.length));
                if (taskDescription.length() == 0) throw new EmptyDescriptionException();
                newTask = new Todo(taskDescription);
                break;
            case "deadline":
                for (int i = 1; i < commands.length; i++) {
                    if (commands[i].equals("/by")) {
                        index = i;
                        break;
                    }
                }

                if (commands.length == 1 || index == 1) throw new EmptyDescriptionException();
                taskDescription = String.join(" ", Arrays.copyOfRange(commands, 1, index));

                if (index+1 == commands.length) throw new EmptyTimestampException();
                extraDetails = String.join(" ", Arrays.copyOfRange(commands, index+1, commands.length));

                newTask = new Deadline(taskDescription, extraDetails);
                break;
            case "event":
                for (int i = 1; i < commands.length; i++) {
                    if (commands[i].equals("/at")) {
                        index = i;
                        break;
                    }
                }

                if (commands.length == 1 || index == 1) throw new EmptyDescriptionException();
                taskDescription = String.join(" ", Arrays.copyOfRange(commands, 1, index));

                if (index+1 == commands.length) throw new EmptyTimestampException();
                extraDetails = String.join(" ", Arrays.copyOfRange(commands, index+1, commands.length));

                newTask = new Event(taskDescription, extraDetails);
                break;
            case "done":
                if (commands.length != 2) throw new UnknownCommandException();
                try {
                    int taskIndex = Integer.parseInt(commands[1]) - 1;
                    if (taskIndex < 0 || taskIndex >= tasks.size()) {
                        throw new InvalidTaskNumberException(tasks.size());
                    } else {
                        Task task = tasks.get(taskIndex);
                        message = task.markDone()
                                ? String.format("Nice! I've marked this task as done:\n    %s", task)
                                : String.format("This was completed previously!:\n    %s", task);
                    }
                } catch (NumberFormatException e) {
                    throw new InvalidTaskNumberException(tasks.size());
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new InvalidTaskNumberException(tasks.size());
                }
                break;
            default:
                throw new UnknownCommandException();
        }
        if (newTask != null) {
            tasks.add(newTask);
            message = String.format(
                    "Got it. I've added this task:\n    %s\nNow you have %d tasks in the list.",
                    newTask,
                    tasks.size()
            );
        }
        printOut(message);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Task> tasks = new ArrayList<>();

        printOut(WELCOME_MESSAGE);
        String input = scanner.nextLine();
        while (!input.equals("bye")) {
            try {
                parseInput(input, tasks);
            } catch (DukeException e) {
                printOut(e.getMessage());
            }
            input = scanner.nextLine();
        }
        printOut(BYE_MESSAGE);
    }

    private static void printOut(String message) {
        String[] lines = message.split("\n");
        for (int i = 0; i < lines.length; i++) {
            lines[i] = TAB + lines[i];
        }
        System.out.println(String.format("%s\n%s\n%s", TAB+SEPARATOR, String.join("\n", lines), TAB+SEPARATOR));
    }
}
