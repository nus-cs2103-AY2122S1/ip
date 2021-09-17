package duke.Command;

import duke.DukeException;
import duke.Parser.Type;
import duke.Storage;
import duke.Task;
import duke.TaskList;
import duke.Ui;

/**
 * Command to add task to list
 */
public class AddCommand extends Command {
    private Type type;
    private String description;


    /**
     * Constructor for AddCommand
     *
     * @param type Type of task
     * @param description Description of task
     */
    public AddCommand(Type type, String description) throws DukeException {
        this.type = type;
        this.description = description;
    }

    /**
     * Adds task to list
     *
     * @param tasks Current TaskList
     * @param ui Ui object of bot
     * @param storage Storage object of bot
     * @return Confirmation message
     * @throws DukeException when incorrect input
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = null;
        switch (type) {
        case todo:
            task = new Task.Todo(description, false);
            break;
        case deadline:
            String[] temp1 = description.split("by ");
            try {
                task = new Task.Deadline(temp1[0], false, temp1[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException("Format of deadline command is: 'deadline DESCRIPTION by DATE'");
            }
            break;
        case event:
            String[] temp2 = description.split("at ");
            try {
                task = new Task.Event(temp2[0], false, temp2[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException("Format of event command is: 'event DESCRIPTION at DATE'");
            }
            break;
        case within:
            String[] temp3 = description.split("between | and ");
            try {
                task = new Task.Within(temp3[0], false, temp3[1], temp3[2]);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException("Format of within command is: 'within DESCRIPTION between DATE and DATE'");
            }
            break;
        default:
        }

        assert task != null;
        tasks.addTask(task);
        String result = "";
        result += ("Added: " + task.getTaskType() + task.getStatusIcon() + " " + task.getDescription() + "\n");
        result += ("There are " + tasks.size() + " tasks in the list");
        storage.save(tasks);
        return result;
    }

    /**
     * Checks if Command is ExitCommand
     *
     * @return false
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
