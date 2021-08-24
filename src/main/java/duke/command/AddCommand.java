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


public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";
    private TaskTypes type;
    private String args;

    public AddCommand(TaskTypes type, String args) {
        this.type = type;
        this.args = args;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
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

        tasks.add(newTask);
        storage.save(tasks);
        ui.printMsg(generateMsg(newTask, tasks));
    }

    private String generateMsg(Task tsk, TaskList tasks) {
        String msg = "Got it. I've added this task:\n  " + tsk + "\nNow you have " + tasks.numTasks();
        msg = tasks.numTasks() == 1 ? msg + " task in the list" : msg + " tasks in the list.";
        return msg;
    }
}
