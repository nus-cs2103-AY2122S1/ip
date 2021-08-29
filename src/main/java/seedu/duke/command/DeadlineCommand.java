package seedu.duke.command;

import seedu.duke.DateTimeManager;
import seedu.duke.Storage;
import seedu.duke.Ui;
import seedu.duke.task.Deadline;
import seedu.duke.task.Task;
import seedu.duke.task.TaskList;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class DeadlineCommand extends Command {
    private static final String ADD_MESSAGE = "Got it. I've added this task:\n";
    private Task task;
    private LocalDate date;
    private Storage storage;

    public DeadlineCommand(Ui ui, TaskList taskList, String description,
                           LocalDate date, Storage storage) {
        super(ui, taskList);
        task = new Deadline(description, date);
        this.date = date;
        this.storage = storage;
    }

    @Override
    public boolean updatesTaskList() {
        return true;
    }

    @Override
    public String getUsageMessage() {
        return "deadline <description> /by dd/MM/yyy |"
                + " add a deadline task to your list with the given description";
    }

    /**
     * Adds the Deadline task to the task list.
     */
    @Override
    public void execute() {
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

    public void updateDateTasks(HashMap<LocalDate, ArrayList<Task>> dateTasks,
                                DateTimeManager manager) {
        manager.updateDateTasks(dateTasks, date, task);
    }

}

