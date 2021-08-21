package duke.io;

import duke.Command;
import duke.DukeException;
import duke.data.TaskList;
import duke.task.Task;

import java.time.LocalDateTime;
import java.util.Scanner;

public class Ui {

    private Scanner sc;

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

    public void showIntError() {
        System.err.println(format("☹ OOPS!!! The index of a task must be specified."));
    }

    public void showLoadingError() {
        System.out.println(format("Task description cannot be found in database","A new file will be created"));
    }

    public void showSavingError() {
        System.err.println(format("File cannot be saved"));
    }

    public void showDukeException(DukeException e) {
        System.err.println(format(e.toString()));
    }

    public void showWelcome() {
        System.out.println(format("Hello! I'm duke.Duke", "What can I do for you?"));
    }

    public void showDateTimeException() {
        System.err.println(format("Date'T'time inputted is not of valid format: YYYY-MM-DDThh:mm" ));
    }

    public String readCommand() {
        return sc.nextLine();
    }

    public void displayCommand(Command.COMMANDS c, TaskList list) {
        switch (c) {
        case BYE:
            System.out.println(format("Bye. Hope to see you again soon!"));
            break;
        case HELP:
            System.out.println(format("  bye : Closes Duke", "  list : Returns all tasks added",
                    "  todo <description> : Adds a todo task",
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
            //fallthrough
        case EVENT:
            //fallthrough
        case DEADLINE:
            System.out.println(format("Got it. I've added this task:",
                    "  " + list.returnLastTask(), list.returnItemCount(0)));
            break;
        }
    }

    public void displayCommand(Command.COMMANDS c, TaskList list, LocalDateTime dt) {
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
        }
    }

    public void displayCommand(Command.COMMANDS c, int index, Task t, TaskList list) {
        switch (c) {
        case DONE:
            System.out.println(format("Nice! I've marked this task as done:",
                    "  " + t));
            break;
        case DELETE:
            System.out.println(format("Noted. I've removed this task:",
                    "  " + t, list.returnItemCount(1)));
            break;
        }
    }
}
