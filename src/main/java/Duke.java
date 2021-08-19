import java.util.Scanner;
import java.util.Set;

public class Duke {
    private Task[] tasks = new Task[100];
    private int count = 0;
    private static final Set<String> ACTIONS = Set.of("todo", "done", "deadline", "event", "list");

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
        printWithTabIndent(String.format("Now you have %d tasks in the list.", count));
        printLine();
    }

    /**
     * Pretty print the tasks list with the horizontal lines.
     */
    public void printTasks() {
        if (count == 0) {
            printMessage("Nothing in the list!");
        } else {
            printLine();
            for (int i = 0; i < count; i++) {
                printWithTabIndent(String.format("%d. %s", i + 1, tasks[i].toString()));
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
            int taskNoInt = Integer.parseInt(taskNo) - 1;
            Task selectedTask = tasks[taskNoInt];
            if (count == 0) {
                printMessage("Nothing in the list");
            } else if (selectedTask.isDone()) {
                printMessage(
                        String.format("Task %s is already done!\n\t  %s",
                                taskNo,
                                tasks[taskNoInt].toString()
                        )
                );
            } else {
                selectedTask.markAsDone();
                printMessage(
                        String.format("Nice! I've marked this task as done:\n\t  %s",
                                tasks[taskNoInt].toString()
                        )
                );
            }
        } catch (NumberFormatException e) {
            throw new DukeException("Enter a task number for a done action!");
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            throw new DukeException(String.format("Enter a valid number between 1 - %d", count));
        }
    }

    /**
     * Add a Todo task to tasks.
     *
     * @param message String user input. Should start with "todo"
     */
    public void addTodoTask(String message) {
        tasks[count] = new Todo(message.replace("todo ", ""));
        count++;
        printAddMessage("Got it. I've  added this task:", tasks[count - 1].toString());
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
            String deadline = message.split("/by")[1];
            tasks[count] = new Deadline(taskDescription, deadline);
            count++;
            printAddMessage("Got it. I've  added this task:", tasks[count - 1].toString());
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
            String deadline = message.split("/at")[1];
            tasks[count] = new Event(taskDescription, deadline);
            count++;
            printAddMessage("Got it. I've  added this task:", tasks[count - 1].toString());
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("The start and end time of a event cannot be empty.");
        }
    }

    public void handleInput(String message) throws DukeException {
        String inputAction = message.split(" ")[0];
        if (ACTIONS.contains(inputAction)) {
            String parsedMessage = message.replace(inputAction, "").trim();
            if (parsedMessage.isEmpty() && !inputAction.equals("list")) {
                throw new DukeException(String.format("The description of a %s cannot be empty.", inputAction));
            }

            if (inputAction.equals("list")) {
                printTasks();
            } else if (inputAction.equals("done")) {
                markTaskDone(parsedMessage);
            } else if (inputAction.equals("todo")) {
                addTodoTask(parsedMessage);
            } else if (inputAction.equals("deadline")) {
                addDeadlineTask(parsedMessage);
            } else if (inputAction.equals("event")) {
                addEventTask(parsedMessage);
            }
        } else if (!message.isEmpty()) {
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
        String message = scanner.nextLine().trim();
        while (!message.equals("bye")) {
            message = message.trim();
            try {
                duke.handleInput(message);
            } catch (DukeException e) {
                printMessage(e.getMessage());
            }
            message = scanner.nextLine();
        }
        printMessage("Bye. Hope to see you again soon!");
        scanner.close();
    }
}
