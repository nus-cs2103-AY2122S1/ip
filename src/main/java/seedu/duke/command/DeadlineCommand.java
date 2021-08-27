package seedu.duke.command;

import seedu.duke.Storage;
import seedu.duke.Ui;
import seedu.duke.task.Deadline;
import seedu.duke.task.Task;
import seedu.duke.task.TaskList;

import java.time.LocalDate;

public class DeadlineCommand extends Command {
    private static final String ADD_MESSAGE = "Got it. I've added this task:\n";

    private String description;
    private Storage storage;
    private LocalDate date;

    public DeadlineCommand(Ui ui, TaskList taskList, String description,
                           LocalDate date, Storage storage) {
        super(ui, taskList);
        this.description = description;
        this.date = date;
        this.storage = storage;
    }

    @Override
    public boolean updatesTaskList() {
        return true;
    }

    @Override
    public String getUsageMessage() {
        return "deadlin <description> /by dd/MM/yyy |"
                + " add a deadline task to your list with the given description";
    }

    /**
     * Adds the Deadline task to the task list.
     */
    @Override
    public void execute() {
        Task task = new Deadline(description, date);
        taskList = taskList.add(task);
        storage.addTaskToFile(task);

        ui.divide();
        ui.outputMessage(ADD_MESSAGE);
        ui.outputMessage(task.toString());
        ui.outputMessage(taskList.status());
        ui.divide();
    }

    public TaskList getUpdatedList() {
        return this.taskList;
    }

}

