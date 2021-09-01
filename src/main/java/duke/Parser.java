package duke;

import java.time.format.DateTimeParseException;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.TaskList;
import duke.task.ToDo;

/**
 * Handles the user commands to Duke and executes commands accordingly.
 */
public class Parser {

    /** The task list to execute the commands to. */
    private TaskList tasks;

    /**
     * Constructs a Parser object that will handles user commands and execute
     * them accordingly.
     *
     * @param tasks The TaskList that the commands will affect.
     */
    public Parser(TaskList tasks) {
        this.tasks = tasks;
    }

    /**
     * Reads the user commands and executes the commands accordingly.
     *
     * @param command The user command to be read.
     * @return A String containing the message to be shown to the user.
     * @throws DukeException If the user enters an invalid input command, a DukeException
     * will be thrown.
     */
    public String parse(String command) throws DukeException {
        String[] commandSplit = command.split(" ", 2);
        String commandWord = commandSplit[0].toLowerCase();
        String commandDesc = "";
        if (commandSplit.length == 2) {
            commandDesc = commandSplit[1];
        }

        switch (commandWord) {
        case "done":
            if (commandDesc.equals("")) {
                throw new DukeException("OOPS!!! Please specify the task number for the task "
                        + "you want to complete.");
            }
            try {
                int taskNumber = Integer.parseInt(commandDesc);
                return tasks.markDone(taskNumber - 1);
            } catch (NumberFormatException e) {
                System.out.println("OOPS!!! Please input a task number instead.");
            }

            break;
        case "list":
            return tasks.listTasks();
        case "todo":
            if (commandDesc.equals("")) {
                throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
            }
            return tasks.addTask(new ToDo(commandDesc));
        case "deadline":
            if (commandDesc.equals("")) {
                throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
            }
            try {
                String[] commandDescSplit = commandDesc.split("/by");
                String dateAndTime = commandDescSplit[1].trim();
                String[] dateAndTimeSplit = dateAndTime.split(" ");
                String date = dateAndTimeSplit[0];
                String time = dateAndTimeSplit[1];
                return tasks.addTask(new Deadline(commandDescSplit[0].trim(), date, time));
            } catch (DateTimeParseException e) {
                throw new DukeException("You've entered a date or time in an invalid format! "
                        + "\nIt should be in the form: yyyy-mm-dd hrs:mins");
            }
        case "event":
            if (commandDesc.equals("")) {
                throw new DukeException("OOPS!!! The description of a event cannot be empty.");
            }
            String[] commandDescSplit = commandDesc.split("/at");
            return tasks.addTask(new Event(commandDescSplit[0].trim(), commandDescSplit[1].trim()));
        case "delete":
            if (commandDesc.equals("")) {
                throw new DukeException("OOPS!!! Please specify the task number for the task "
                        + "you want to delete.");
            }
            try {
                int taskNumber = Integer.parseInt(commandDesc);
                return tasks.deleteTask(taskNumber - 1);
            } catch (NumberFormatException e) {
                System.out.println("OOPS!!! Please input a task number instead.");
            }
            break;
        case "find":
            if (commandDesc.equals("")) {
                throw new DukeException("OOPS!!! The description of a event cannot be empty.");
            }
            return tasks.findTask(commandDesc.toLowerCase());
        default:
            throw new DukeException("OOPS!!! You have entered an invalid command, please try again!");
        }
        return "";
    }
}
