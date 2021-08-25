package duke.command;

import duke.task.Task;
import duke.task.TaskType;
import duke.task.TaskList;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;
import duke.util.DukeException;
import duke.util.Storage;
import duke.util.Ui;

import java.time.LocalDateTime;

public class AddCommand extends Command {
    Task task;

    public AddCommand(TaskType taskType, String name, LocalDateTime dateTime) {
        switch (taskType){
            case DEADLINE:
                this.task = new Deadline(false, name, dateTime);
                break;
            case EVENT:
                this.task = new Event(false, name, dateTime);
                break;
            case TODO:
                this.task = new Todo(false, name);
                break;
            default:
                this.task = null;
        }
    }

    @Override
    public void execute(Storage storage, TaskList taskList, Ui ui) throws DukeException {
        try {
            taskList.addTask(task);
            ui.respond(
                    String.format("Caan Do!\n" +
                                    "  added: %s\n" +
                                    "Look at me! %d tasks in the list now!",
                            this.task,
                            taskList.getSize())
            );
        } catch (NullPointerException e) {
            throw new DukeException("No task to execute");
        }
    }
}
