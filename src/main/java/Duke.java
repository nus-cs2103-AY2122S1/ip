import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    enum Command {
        LIST, DONE, TODO, EVENT, DEADLINE, DELETE
    }

    enum TaskType {
        TODO, EVENT, DEADLINE
    }

    /**
     * Print added or deleted message accordingly based on the operation made on the task.
     *
     * @param isAdd The task is added.
     * @param task The task being added/deleted.
     * @param numOfTask The latest number of task after task is added/deleted.
     */
    public static void printAddOrDelete(boolean isAdd, Task task, int numOfTask) {
        Printer.prettyPrint(String.format("%s. I've %s this task:\n\t %s\n\tNow you have %d tasks in the list.",
                isAdd ? "Got it" : "Noted",
                isAdd ? "added" : "deleted",
                task.toString(),
                numOfTask));
    }

    /**
     * If the user command is valid, then return the task description from the command.
     * Otherwise, throw corresponding exception.
     *
     * @param command The command entered by the user which is divided into 2 parts (Type of task and its description).
     * @return Task description or throw exception.
     * @throws EmptyDescriptionException An exception where user provide empty task description.
     * @throws IncompleteDescriptionException An exception where user provide incomplete task description.
     */
    public static String[] extractCommand(String[] command) throws EmptyDescriptionException, IncompleteDescriptionException {
        if (command.length < 2 || command[1].trim().isEmpty())
            throw new EmptyDescriptionException(String.format("The description of a %s cannot be empty.", command[0]));
        String[] description = command[1].split(" /by | /at ", 2);
        if (!command[0].equals("todo") &&
                (description.length < 2 || description[0].trim().isEmpty() || description[1].trim().isEmpty()))
            throw new IncompleteDescriptionException(String.format("The description of a %s is incomplete.", command[0]));
        return description;
    }

    /**
     * Add the task into the task list then print success message.
     *
     * @param command The command entered by the user which is divided into 2 parts (Type of task and its description).
     * @param tasks The list consists of all the added tasks.
     * @param numOfTask The latest number of task after task is added/deleted.
     */
    public static void addThenPrint(String[] command, ArrayList<Task> tasks, int numOfTask) {
        try {
            String[] descriptions = extractCommand(command);
            Task task = null;
            switch (TaskType.valueOf(command[0].toUpperCase())) {
                case TODO:
                    task = new Todo(descriptions[0]);
                    break;
                case EVENT:
                    task = new Event(descriptions[0], descriptions[1]);
                    break;
                case DEADLINE:
                    task = new Deadline(descriptions[0], descriptions[1]);
                    break;
            }
            if (task != null) {
                tasks.add(task);
                printAddOrDelete(true, task, ++numOfTask);
            }
        } catch (EmptyDescriptionException | IncompleteDescriptionException e) {
            Printer.prettyPrint(e.toString());
        }
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
                    case EVENT:
                    case DEADLINE:
                        addThenPrint(command, tasks, numOfTask++);
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
