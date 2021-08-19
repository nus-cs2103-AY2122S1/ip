import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private final ArrayList<Task> tasks = new ArrayList<>();

    private enum Action {
        BYE("bye"),
        DEADLINE("deadline"),
        DELETE("delete"),
        DONE("done"),
        EVENT("event"),
        LIST("list"),
        TODO("todo");

        private final String name;

        Action (String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    /**
     * Print with 4 spaces infront of param str.
     *
     * @param str A String to be printed
     */
    public static void printWithTabIndent(String str) {
        System.out.println("\t" + str);
    }

    /**
     * Print horizontal line.
     */
    public static void printLine() {
        printWithTabIndent("-------------------------------------------------------------");
    }

    /**
     * Pretty print the message with the horizontal lines and the param message.
     *
     * @param message A String to be printed
     */
    public static void printMessage(String message) {
        printLine();
        printWithTabIndent(message);
        printLine();
    }

    /**
     * Pretty print the message with the horizontal lines and the param message.
     *
     * @param message A String to be printed
     */
    public void printAddMessage(String message, String taskTitle) {
        printLine();
        printWithTabIndent(message);
        printWithTabIndent("  " + taskTitle);
        printWithTabIndent(String.format("Now you have %d tasks in the list.", tasks.size()));
        printLine();
    }

    /**
     * Pretty print the tasks list with the horizontal lines.
     */
    public void printTasks() {
        if (tasks.isEmpty()) {
            printMessage("Nothing in the list!");
        } else {
            printLine();
            for (int i = 0; i < tasks.size(); i++) {
                printWithTabIndent(String.format("%d. %s", i + 1, tasks.get(i).toString()));
            }
            printLine();
        }
    }

    /**
     * Marks the corresponding task as done.
     * If message does not contain a number, this method will print an error message.
     *
     * @param taskNo String user input
     */
    public void markTaskDone(String taskNo) throws DukeException {
        try {
            validateActionDescription(taskNo, Action.DONE);
            int taskNoInt = Integer.parseInt(taskNo) - 1;
            Task selectedTask = tasks.get(taskNoInt);
            if (tasks.isEmpty()) {
                printMessage("Nothing in the list");
            } else if (selectedTask.isDone()) {
                printMessage(
                        String.format("Task %s is already done!\n\t  %s",
                                taskNo,
                                selectedTask
                        )
                );
            } else {
                selectedTask.markAsDone();
                printMessage(
                        String.format("Nice! I've marked this task as done:\n\t  %s",
                                selectedTask
                        )
                );
            }
        } catch (NumberFormatException e) {
            throw new DukeException("Enter a number for a done action!");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(String.format("Enter a valid number between 1 - %d", tasks.size()));
        }
    }

    private Task lastTask() {
        return tasks.get(tasks.size() - 1);
    }

    /**
     * Add a Todo task to tasks.
     *
     * @param message String user input. Should start with "todo"
     */
    public void addTodoTask(String message) throws DukeException {
        validateActionDescription(message, Action.TODO);
        tasks.add(new Todo(message.replace("todo ", "")));
        printAddMessage("Got it. I've  added this task:", lastTask().toString());
    }

    /**
     * Add a Deadline task to tasks.
     * Throws DukeException if deadline description or end time is missing.
     *
     * @param message String user input. Should start with "deadline"
     */
    public void addDeadlineTask(String message) throws DukeException {
        try {
            String taskDescription = message.replaceAll("/by.*", "");
            validateActionDescription(taskDescription, Action.DEADLINE);
            String deadline = message.split("/by")[1];
            tasks.add(new Deadline(taskDescription, deadline));
            printAddMessage("Got it. I've  added this task:", lastTask().toString());
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("The end time of a deadline cannot be empty.");
        }
    }

    /**
     * Add an Event task to tasks.
     * Throws DukeException if event description or deadline is missing.
     *
     * @param message String user input
     */
    public void addEventTask(String message) throws DukeException {
        try {
            String taskDescription = message.replaceAll("/at.*", "");
            validateActionDescription(taskDescription, Action.EVENT);
            String deadline = message.split("/at")[1];
            tasks.add(new Event(taskDescription, deadline));
            printAddMessage("Got it. I've  added this task:", lastTask().toString());
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("The start and end time of a event cannot be empty.");
        }
    }

    public void deleteTask(String message) throws DukeException {
        try {
            validateActionDescription(message, Action.DELETE);
            Task selectedTask = tasks.get(Integer.parseInt(message) - 1);
            tasks.remove(selectedTask);
            printAddMessage("Noted. I've removed this task:", selectedTask.toString());
        } catch (NumberFormatException e) {
            throw new DukeException("Enter a number for a delete action");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(String.format("Enter a valid number between 1 - %d", tasks.size()));
        }
    }

    private void validateActionDescription(String input, Action type) throws DukeException {
        if (input.isEmpty()) {
            throw new DukeException(String.format("The description of a %s cannot be empty.", type.getName()));
        }
    }

    public boolean handleInput(String message) throws DukeException {
        try {
            String inputAction = message.split(" ")[0];
            Action actionEnum = Action.valueOf(inputAction.toUpperCase());
            String parsedMessage = message.replace(inputAction, "").trim();

            switch (actionEnum) {
                case BYE:
                    printMessage("Bye. Hope to see you again soon!");
                    return false;
                case DEADLINE:
                    addDeadlineTask(parsedMessage);
                    break;
                case DELETE:
                    deleteTask(parsedMessage);
                    break;
                case DONE:
                    markTaskDone(parsedMessage);
                    break;
                case EVENT:
                    addEventTask(parsedMessage);
                    break;
                case LIST:
                    printTasks();
                    break;
                case TODO:
                    addTodoTask(parsedMessage);
                    break;
                default:
                    throw new DukeException("I'm sorry, but I don't know what that means :-(");
            }
            return true;
        } catch (IllegalArgumentException e) {
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Scanner scanner = new Scanner(System.in);
        Duke duke = new Duke();

        printMessage("Hello! I'm Duke\n\tWhat can I do for you?");
        String message;
        boolean isActive = true;
        while (isActive) {
            message = scanner.nextLine().trim();
            try {
                isActive = duke.handleInput(message);
            } catch (DukeException e) {
                printMessage(e.getMessage());
            }
        }
        scanner.close();
    }
}
