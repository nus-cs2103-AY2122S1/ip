package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.ui.Ui;
import duke.task.Deadline;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;
import duke.task.Event;
import duke.utility.Utility;

import java.time.LocalDateTime;

/**
 * An AddCommand class that extends from the Command class.
 *
 * @author KelvinSoo
 * @version A-MoreOOP
 */
public class AddCommand extends Command{

    private final String command;
    private final String description;

    /**
     * A constructor to initialize an add command.
     * @param command The type of add command eg. todo, deadline, event
     * @param description the description of the command
     */
    public AddCommand(String command, String description) {
        this.command = command;
        this.description = description;
    }

    /**
     * a method to execute a command.
     * @param taskList The task list to execute the command on
     * @param ui The user interface to display the reply
     * @param storage The place to store the session
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        String[] parameter;
        LocalDateTime ldt;
        Task task;
        switch (command) {
        case "todo":
            task = new ToDo(description);
            break;
        case "deadline":
            if (description.split(" /by ").length != 2) {
                throw new DukeException("OOPS!!! Parameter /by is missing.\n"
                        + "eg. deadline Read Book /by 31/12/2021 1800\n"
                        + "    deadline Read Book /by Friday");
            }
            parameter = description.split(" /by ");
            ldt = Utility.stringToDate(parameter[1]);
            task = (ldt == null)
                    ? new Deadline(parameter[0], parameter[1])
                    : new Deadline(parameter[0], ldt);
            break;
        case "event":
            if (description.split(" /at ").length != 2) {
                throw new DukeException("OOPS!!! Parameter /at is missing.\n"
                        + "eg. event Meeting /at 31/12/2021 1800\n"
                        + "    event Meeting /at Friday");
            }
            parameter = description.split(" /at ");
            ldt = Utility.stringToDate(parameter[1]);
            task = (ldt == null)
                    ? new Event(parameter[0], parameter[1])
                    : new Event(parameter[0], ldt);
            break;
        default:
            throw new DukeException("OOPS!!! Something went wrong.");
        }
        taskList.addTask(task);
        storage.save(taskList);
        ui.printStringInBox(
                String.format("Got it. I've added this task:\n  %s %s\nNow you have %d tasks in the list.",
                        task.getStatusIcon(), task.getDescription(), taskList.size()));

    }

    /**
     * A boolean to notate if this is an exit command.
     * @return false
     */
    @Override
    public boolean isExit() {
        return false;
    }

}
