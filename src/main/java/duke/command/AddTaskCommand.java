package duke.command;

import duke.data.exceptions.DukeException;
import duke.data.exceptions.InvalidInputException;
import duke.storage.Storage;
import duke.ui.Ui;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;

public class AddTaskCommand extends Command {
    private static final String TODO = "todo";
    private static final String DEADLINE = "deadline";
    private static final String EVENT = "event";

    private String taskType;
    private String taskName;
    private String dateAndTime;

    public AddTaskCommand(String taskType, String taskName) {
        this.taskType = taskType;
        this.taskName = taskName;
    }

    public AddTaskCommand(String taskType, String taskName, String dateAndTime) {
        this.taskType = taskType;
        this.taskName = taskName;
        this.dateAndTime = dateAndTime;
    }

    private Task addTask(TaskList tasks) throws InvalidInputException {
        switch (taskType) {
        case TODO:
            Todo todo = new Todo(taskName);
            tasks.addTask(todo);
            return todo;
        case DEADLINE:
            Deadline deadline = new Deadline(taskName, dateAndTime);
            tasks.addTask(deadline);
            return deadline;
        case EVENT:
            Event event = new Event(taskName, dateAndTime);
            tasks.addTask(event);
            return event;
        default:
            throw new InvalidInputException("error");
        }
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task addedTask = addTask(taskList);
        storage.update(taskList);
        ui.showAddTaskMessage(addedTask, taskList);

    }
}
