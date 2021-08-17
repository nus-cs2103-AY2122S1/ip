import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This class encapsulates Duke, an interactive task management chat-bot.
 *
 * @author Kleon Ang
 */
public class Duke {
    private static final String EXIT_COMMAND = "bye";
    private static final String LIST_COMMAND = "list";
    private static List<Task> tasks = new ArrayList<>();

    private static void printReply(String string) {
        System.out.println(new Reply(string));
    }

    private static void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        printReply("Hello! I'm Duke\nWhat can I do for you?");
    }

    private static void add(Task task) {
        tasks.add(task);
        String addMessage = "Got it. I've added this task:\n  " + task
                + "\nNow you have " + tasks.size() + " tasks in the list.";
        printReply(addMessage);
    }

    private static void list() throws DukeException {
        if (tasks.size() == 0) {
            throw new DukeException("There are currently no tasks in your list.");
        }
        StringBuilder tasksBuilder = new StringBuilder();
        tasksBuilder.append("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); ++i) {
            String counter = String.valueOf(i + 1);
            Task currentTask = tasks.get(i);
            tasksBuilder.append(counter).append(".").append(currentTask);
            if (i < tasks.size() - 1) {
                // Append newline for all tasks before last task
                tasksBuilder.append("\n");
            }
        }
        printReply(tasksBuilder.toString());
    }

    private static void done(int counter) throws DukeException {
        if (counter <= 0 || counter > tasks.size()) {
            throw new DukeException("Sorry, no such task of index " + counter + ".");
        }
        Task doneTask = tasks.get(counter - 1);
        doneTask.markAsDone();
        printReply("Nice! I've marked this task as done:\n  " + doneTask);
    }

    private static void delete(int counter) throws DukeException {
        if (counter <= 0 || counter > tasks.size()) {
            throw new DukeException("Sorry, no such task of index " + counter + ".");
        }
        Task taskToRemove = tasks.remove(counter - 1);
        printReply("Noted. I've removed this task:\n  " + taskToRemove
                + "\nNow you have " + tasks.size() + " tasks in the list.");
    }

    public static void main(String[] args) {
        greet();
        String readIn;
        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                readIn = scanner.nextLine();
                if (readIn.equals(EXIT_COMMAND)) {
                    break;
                } else if (readIn.equals(LIST_COMMAND)) {
                    list();
                } else {
                    String[] commandArguments = readIn.split(" ", 2);
                    String command = commandArguments[0];
                    String arguments = "";
                    if (commandArguments.length == 2) {
                        arguments = commandArguments[1];
                    }
                    switch (command) {
                        case "done": {
                            if (commandArguments.length < 2) {
                                throw new DukeException("☹ OOPS!!! The index of '" + command + "' cannot be empty.");
                            }
                            int counter = Integer.parseInt(arguments);
                            done(counter);
                            break;
                        }
                        case "deadline": {
                            if (commandArguments.length < 2) {
                                throw new DukeException("☹ OOPS!!! The description of '" + command + "' cannot be empty.");
                            }
                            String[] splitTask = arguments.split(" /by ");
                            if (splitTask.length < 2) {
                                throw new DukeException("Please indicate a deadline using '/by'.");
                            }
                            String description = splitTask[0];
                            String by = splitTask[1];
                            add(new Deadline(description, by));
                            break;
                        }
                        case "event": {
                            if (commandArguments.length < 2) {
                                throw new DukeException("☹ OOPS!!! The description of '" + command + "' cannot be empty.");
                            }
                            String[] splitTask = arguments.split(" /at ");
                            if (splitTask.length < 2) {
                                throw new DukeException("Please indicate the event time frame using '/at'.");
                            }
                            String description = splitTask[0];
                            String at = splitTask[1];
                            add(new Event(description, at));
                            break;
                        }
                        case "todo": {
                            if (commandArguments.length < 2) {
                                throw new DukeException("☹ OOPS!!! The description of '" + command + "' cannot be empty.");
                            }
                            add(new Todo(arguments));
                            break;
                        }
                        case "delete": {
                            if (commandArguments.length < 2) {
                                throw new DukeException("☹ OOPS!!! The index of '" + command + "' cannot be empty.");
                            }
                            int counter = Integer.parseInt(arguments);
                            delete(counter);
                            break;
                        }
                        default:
                            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }
                }
            } catch (DukeException e) {
                printReply(e.getMessage());
            }
        }

        printReply("Bye. Hope to see you again soon!");
    }
}
