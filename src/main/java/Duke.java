import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Duke {

    enum Command {
        LIST, DONE, TODO, EVENT, DEADLINE, DELETE
    }

    public static void printAddOrDelete(boolean isAdd, Task task, int numOfTask) {
        Printer.prettyPrint(String.format("%s. I've %s this task:\n\t %s\n\tNow you have %d tasks in the list.",
                isAdd ? "Got it" : "Noted",
                isAdd ? "added" : "deleted",
                task.toString(),
                numOfTask));
    }

    public static String[] extractCommand(String[] command) throws EmptyDescriptionException {
        if (command.length < 2 || command[1].equals("") || command[1].trim().isEmpty())
            throw new EmptyDescriptionException(String.format("The description of a %s cannot be empty.", command[0]));
        return command[1].split(" /by | /at ", 2);
    }

    public static void main(String[] args) {
        // Greet the user
        Printer.prettyPrint("Welcome to\n" +
                Printer.logo +
                "\tI'm Desmond,\n" +
                "\thow may I serve you?\n");

        // Initialize string array to store the list
        ArrayList<Task> tasks = new ArrayList<>();
        int numOfTask = 0;

        // Initialize scanner to get user input
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        // Execute based on command (user input)
        // Exit when user commands "bye"
        while (!input.equals("bye")) {
            String[] command = input.split(" ", 2);
            String[] taskDetail;
            try {
                switch (Command.valueOf(command[0].toUpperCase())) {
                    case LIST:
                        Printer.prettyPrint("Here are the tasks in your list:\n" +
                                Printer.listTask(tasks, numOfTask));
                        break;
                    case DONE:
                        tasks.get(Integer.parseInt(command[1]) - 1).markAsDone();
                        break;
                    case DELETE:
                        printAddOrDelete(false, tasks.get(Integer.parseInt(command[1]) - 1), --numOfTask);
                        tasks.remove(Integer.parseInt(command[1]) - 1);
                        break;
                    case TODO:
                        try {
                            taskDetail = extractCommand(command);
                            Todo todo = new Todo(taskDetail[0]);
                            tasks.add(todo);
                            printAddOrDelete(true, todo, ++numOfTask);
                        } catch (EmptyDescriptionException e) {
                            Printer.prettyPrint(e.toString());
                        }
                        break;
                    case EVENT:
                        try {
                            taskDetail = extractCommand(command);
                            Event event = new Event(taskDetail[0], taskDetail[1]);
                            tasks.add(event);
                            printAddOrDelete(true, event, ++numOfTask);
                        } catch (EmptyDescriptionException e) {
                            Printer.prettyPrint(e.toString());
                        }
                        break;
                    case DEADLINE:
                        try {
                            taskDetail = extractCommand(command);
                            Deadline deadline = new Deadline(taskDetail[0], taskDetail[1]);
                            tasks.add(deadline);
                            printAddOrDelete(true, deadline, ++numOfTask);
                        } catch (EmptyDescriptionException e) {
                            Printer.prettyPrint(e.toString());
                        }
                        break;
                    default:
                        throw new UnknownCommandException("I'm sorry, but I don't know what that means :-(");
                }
            } catch (UnknownCommandException e) {
                Printer.prettyPrint(e.toString());
            }
            input = scanner.nextLine();
        }
        Printer.prettyPrint("Bye (*´▽｀)ノシ. Have a good day!\n");
    }
}
