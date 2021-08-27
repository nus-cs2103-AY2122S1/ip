package duke.io;

import java.time.LocalDateTime;
import java.util.Scanner;

import duke.Command;
import duke.DukeException;
import duke.data.TaskList;
import duke.task.Task;

public class Ui {


    private Scanner sc;

    /**
     * Returns a new Ui object.
     */
    public Ui() {
        sc = new Scanner(System.in);
    }

    private static String format(String... inputs) {
        StringBuilder str = new StringBuilder();
        String line = "    ____________________________________________________________\n";
        String space = "     ";
        str.append(line);
        for (String s : inputs) {
            str.append(space).append(s).append("\n");
        }
        str.append(line);
        return str.toString();
    }

    /**
     * Prints an error statement to screen if a user fails to input an int when required.
     */
    public void showIntError() {
        System.err.println(format("☹ OOPS!!! The index of a task must be specified."));
    }

    /**
     * Prints a statement to screen if the saved file cannot be loaded.
     */
    public void showLoadingError() {
        System.out.println(format("Task description cannot be found in database", "A new file will be created"));
    }

    /**
     * Prints an error statement to screen if the data file cannot be saved.
     */
    public void showSavingError() {
        System.err.println(format("File cannot be saved"));
    }

    /**
     * Prints the content of a DukeException as an error message.
     *
     * @param e DukeException to be printed.
     */
    public void showDukeException(DukeException e) {
        System.err.println(format(e.toString()));
    }

    /**
     * Prints a welcome statement when Duke is launched.
     */
    public void showWelcome() {
        System.out.println(format("Hello! I'm Duke", "What can I do for you?"));
    }

    /**
     * Prints an error statement to screen if the user inputs the date and time in a wrong format.
     */
    public void showDateTimeException() {
        System.err.println(format("Date'T'time inputted is not of valid format: YYYY-MM-DDThh:mm"));
    }

    /**
     * Returns a line of user input.
     *
     * @return String User input formatted as a String.
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Prints a statement showing the outcome of a the command the user inputted.
     * Used for commands involving a single word or for addition of tasks.
     *
     * @param c Type of command the user has inputted.
     * @param list Current TaskList used.
     */
    public void displayCommand(Command.Commands c, TaskList list) {
        switch (c) {
        case BYE:
            System.out.println(format("Bye. Hope to see you again soon!"));
            break;
        case HELP:
            System.out.println(format("  bye : Closes Duke", "  list : Returns all tasks added",
                    "  todo <description> : Adds a todo task",
                    "  find <description> : Returns all tasks with <description> in their description",
                    "  event <description> /at <time: YYYY-MM-DDThh:mm> : Adds an event at time <time>",
                    "  deadline <description> /by <time: YYYY-MM-DDThh:mm> : Adds a task with deadline at time <time>",
                    "  done <index> : Marks the task at <index> as done",
                    "  delete <index> : deletes the task at <index>",
                    "  at <time: YYYY-MM-DDThh:mm> : Returns all events up till <time>",
                    "  by <time: YYYY-MM-DDThh:mm> : Returns all tasks with deadline due before or at <time>",
                    "  all <time: YYYY-MM-DDThh:mm> : Returns all timed tasks with times up till <time>"));
            break;
        case LIST:
            System.out.println(format(list.returnItems()));
            break;
        case TODO:
            //Fallthrough
        case EVENT:
            //Fallthrough
        case DEADLINE:
            System.out.println(format("Got it. I've added this task:",
                    "  " + list.returnLastTask(), list.returnItemCount(0)));
            break;
        default:
            break;
        }
    }

    /**
     * Prints a statement showing the outcome of a the command the user inputted.
     * Used for commands involving involving retrieval of tasks based on date and time.
     *
     * @param c Type of command the user has inputted.
     * @param list Current TaskList used.
     * @param dt Date and Time used by the command.
     */
    public void displayCommand(Command.Commands c, TaskList list, LocalDateTime dt) {
        switch (c) {
        case AT:
            System.out.println(format(list.getEventsAt(dt)));
            break;
        case BY:
            System.out.println(format(list.getDeadlinesBy(dt)));
            break;
        case ALL:
            System.out.println(format(list.getAllBy(dt)));
            break;
        default:
            break;
        }
    }

    /**
     * Prints a statement showing the outcome of a the command the user inputted.
     * Used for commands involving modification of tasks.
     *
     * @param c Type of command the user has inputted
     * @param index Index of task modified by the command.
     * @param t Task modified by the command.
     * @param list Current TaskList used.
     */
    public void displayCommand(Command.Commands c, int index, Task t, TaskList list) {
        switch (c) {
        case DONE:
            System.out.println(format("Nice! I've marked this task as done:",
                    "  " + t));
            break;
        case DELETE:
            System.out.println(format("Noted. I've removed this task:",
                    "  " + t, list.returnItemCount(1)));
            break;
        default:
            break;
        }
    }

    public void displayCommand(Command.Commands c, String toFind, TaskList list) {
        System.out.print(format(list.returnFoundItem(toFind)));
    }
}
