package seedu.duke.command;

import seedu.duke.Storage;
import seedu.duke.Ui;
import seedu.duke.task.Task;
import seedu.duke.task.TaskList;
import seedu.duke.task.ToDo;

public class ToDoCommand extends Command {
    private static final String ADD_MESSAGE = "Got it. I've added this task:\n";
    private String description;
    private Storage storage;

    public ToDoCommand(Ui ui, TaskList taskList, String description, Storage storage) {
        super(ui, taskList);
        this.description = description;
        this.storage = storage;
    }

    @Override
    public String getUsageMessage() {
        return "todo <description>  |"
                + " add a todo task to your list with the given description";
    }

    @Override
    public boolean updatesTaskList() {
        return true;
    }

    /**
     * Adds the ToDo task to the task list.
     */
    @Override
    public void execute() {
        Task task = new ToDo(description);
        taskList = taskList.add(task);
        storage.addTaskToFile(task);

        ui.divide();
        ui.outputMessage(ADD_MESSAGE);
        ui.outputMessage(task.toString());
        ui.outputMessage(taskList.status());
        ui.divide();
    }

}

