package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.TaskTypes;
import duke.task.ToDo;


/**
 * The AddCommand encapsulates a Command that adds Tasks to a TaskList.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";
    private TaskTypes type;
    private String args;

    /**
     * Constructor of an AddCommand.
     *
     * @param type the type of the Task to add.
     * @param args the arguments to create the Task.
     */
    public AddCommand(TaskTypes type, String args) {
        this.type = type;
        this.args = args;
    }

    /**
     * Will add the Task corresponding to this AddCommand to the given TaskList.
     * Will also store the updated TaskList to taskList.txt.
     *
     * @param tasks the given TaskList.
     * @param ui the given Ui.
     * @param storage the given Storage.
     * @return the string for the Ui to print.
     * @throws DukeException when a Task cannot be created.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task newTask;

        switch (type) {
        case TODO:
            newTask = new ToDo(args);
            break;
        case DEADLINE:
            newTask = new Deadline(args);
            break;
        case EVENT:
            newTask = new Event(args);
            break;
        default:
            throw new DukeException("something went wrong with adding a task!");
        }

        assert newTask!= null;

        tasks.add(newTask);
        storage.save(tasks);
        return generateMsg(newTask, tasks);
    }

    private String generateMsg(Task tsk, TaskList tasks) {
        String msg = "Got it. I've added this task:\n  " + tsk + "\nNow you have " + tasks.numTasks();
        msg = tasks.numTasks() == 1 ? msg + " task in the list" : msg + " tasks in the list.";
        return msg;
    }
}
