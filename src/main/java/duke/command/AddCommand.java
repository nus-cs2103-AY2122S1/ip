package duke.command;

import duke.Deadline;
import duke.DukeException;
import duke.Event;
import duke.Task;
import duke.TaskList;
import duke.ToDo;
import duke.Ui;

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
     * Creates a new task from user's input and adds task to the given tasklist.
     *
     * @param tasks TaskList instance which the new task is to be added to.
     * @param ui Duke's UI.
     * @throws DukeException For invalid inputs.
     */
    @Override
    public void execute(TaskList tasks, Ui ui) throws DukeException {
        if (params.length == 1) {
            throw new DukeException("☹ OOPS!!! The description of a task cannot be empty.");
        }
        Task t;
        String taskType = params[0];
        String taskInfo = params[1];
        System.out.println(taskInfo);
        switch (taskType) {
        case "event":
            String[] eventInfo = taskInfo.split(" /at ");
            if (eventInfo.length == 1) {
                throw new DukeException("☹ OOPS!!! Please enter event information in the following " +
                        "format:\nevent [event name] /at [yyyy-mm-dd HH:MM]");
            }
            t = new Event(eventInfo[0], eventInfo[1]);
            break;
        case "deadline":
            String[] deadlineInfo = taskInfo.split(" /by ");
            if (deadlineInfo.length == 1) {
                throw new DukeException("☹ OOPS!!! Please enter deadline information in the following " +
                        "format:\ndeadline [deadline name] /by [yyyy-mm-dd]");
            }
            t = new Deadline(deadlineInfo[0], deadlineInfo[1]);
            break;
        case "todo":
            t = new ToDo(taskInfo);
            break;
        default:
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        tasks.add(t);
        System.out.println(Ui.format("Got it. I've added this task: \n\t" + t +
                "\nNow you have " + ui.formatNumTasks(tasks.size()) + " in the list."));
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
