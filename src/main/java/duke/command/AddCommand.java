package duke.command;

import duke.DukeException;
import duke.TaskList;
import duke.Ui;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

/**
 * Encapsulates the add task command.
 */
public class AddCommand implements Command {
    private String[] params;

    /**
     * Constructor for an AddCommand instance.
     *
     * @param params String array containing user input split by whitespace.
     */
    public AddCommand(String[] params) {
        this.params = params;
    }

    /**
     * Creates a new task from user's input and adds task to the given task list.
     * @param tasks TaskList instance which the new task is to be added to.
     * @param ui Duke's UI.
     * @return The String representation of Duke's response.
     * @throws DukeException For invalid inputs.
     */
    @Override
    public String execute(TaskList tasks, Ui ui) throws DukeException {
        if (params.length == 1) {
            throw new DukeException("OOPS!!! The description of a task cannot be empty. \uD83D\uDEAB");
        }
        Task t;
        String taskType = params[0];
        String taskInfo = params[1];
        switch (taskType) {
        case "event":
        //fallthrough
        case "e":
            if (taskInfo.startsWith("/at")) {
                throw new DukeException("OOPS!!! You are missing the name of the event."
                        + "\nThe correct format is:"
                        + "\nevent [event name] /at [yyyy-mm-dd HH:MM]");
            }
            String[] eventInfo = taskInfo.split(" /at ");
            if (eventInfo.length == 1) {
                throw new DukeException("OOPS!!! Please enter event information in the following "
                        + "format:\nevent [event name] /at [yyyy-mm-dd HH:MM]");
            }
            t = new Event(eventInfo[0], eventInfo[1]);
            break;
        case "deadline":
        //fallthrough
        case "dl":
            if (taskInfo.startsWith("/by")) {
                throw new DukeException("OOPS!!! You are missing the name of the deadline."
                        + "\nThe correct format is:"
                        + "\ndeadline [deadline name] /by [yyyy-mm-dd]");
            }
            String[] deadlineInfo = taskInfo.split(" /by ");
            if (deadlineInfo.length == 1) {
                throw new DukeException("OOPS!!! Please enter deadline information in the following "
                        + "format:\ndeadline [deadline name] /by [yyyy-mm-dd]");
            }
            t = new Deadline(deadlineInfo[0], deadlineInfo[1]);
            break;
        case "todo":
        //fallthrough
        case "t":
            t = new ToDo(taskInfo);
            break;
        default:
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        tasks.add(t);
        return "Got it. I've added this task: \n\t" + t
                + "\nNow you have " + ui.formatNumTasks(tasks.getSize()) + " in the list.";
    }

    /**
     * Indicates if the command is an exit command.
     *
     * @return If the command is an exit command.
     */
    public boolean isExit() {
        return false;
    }
}
