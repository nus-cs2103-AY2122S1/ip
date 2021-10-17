package duke.command;

import java.time.LocalDate;

import duke.IncompleteCommandException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Event;
import duke.task.Task;



/**
 * EventCommand instructions to add task into the program.
 */
public class EventCommand extends Command {

    /**
     * constructor for EventCommand.
     *
     * @param input String command.
     */
    public EventCommand(String input) {
        super(input);
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws IncompleteCommandException {
        if (input.length() <= 5) {
            throw new IncompleteCommandException("OOPS!!! The description of a event cannot be empty.");
        }
        Task task = null;

        boolean containsAt = input.contains("/at");
        if (containsAt) {
            String[] stringArr = input.substring(6).split("/at");
            task = new Event(stringArr[0], LocalDate.parse(stringArr[1].strip()));
            taskList.addTask(task);
        } else {
            System.out.println("Your duke.task.Event is missing a /at (date)");
        }

        return ui.taskWithDateAddedMessage(task, taskList.getTotalNumberOfTask());
    }


}
