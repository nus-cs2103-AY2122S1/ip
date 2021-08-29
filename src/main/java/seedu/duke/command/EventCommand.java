package seedu.duke.command;

import seedu.duke.DateTimeManager;
import seedu.duke.Storage;
import seedu.duke.Ui;
import seedu.duke.task.Event;
import seedu.duke.task.Task;
import seedu.duke.task.TaskList;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;


/**
 * Represents an event command. An <code>EventCommand</code> describes
 * the action to be executed when a user input an event task description.
 */
public class EventCommand extends Command {
    private static final String ADD_MESSAGE = "Got it. I've added this task:\n";
    private Task task;
    private Storage storage;
    private LocalDate date;

    /**
     * Public constructor for <code>EventCommand</code>.
     * @param ui The Ui to handle user interactions.
     * @param taskList The task list to be updated.
     * @param description The description of the task.
     * @param date Date of the event.
     * @param storage The storage to handle modifications to the file.
     */
    public EventCommand(Ui ui, TaskList taskList, String description,
                           LocalDate date, Storage storage) {
        super(ui, taskList);
        task = new Event(description, date);
        this.date = date;
        this.storage = storage;
    }

    @Override
    public String getUsageMessage() {
        return "event <description> /at dd/MM/yyy |"
                + " add an event task to your list with the given description";
    }

    @Override
    public boolean updatesTaskList() {
        return true;
    }

    /**
     * Adds the Event task to the task list.
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

    public void updateDateTasks(HashMap<LocalDate, ArrayList<Task>> dateTasks,
                                DateTimeManager manager) {
        manager.updateDateTasks(dateTasks, date, task);
    }

}

