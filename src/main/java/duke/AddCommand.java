package duke;

import java.time.LocalDate;

/**
 * Represents a command to add tasks to the task list.
 */
public class AddCommand extends Command {
    private String taskType;
    private String description;
    private LocalDate date;

    /**
     * Sole constructor for this class
     *
     * @param taskType Task type to add.
     * @param description Task description.
     * @param date Task date for events and deadlines.
     * @throws DukeException If description is empty.
     */
    public AddCommand(String taskType, String description, LocalDate date) throws DukeException {
        if (description.equals("")) {
            throw new DukeException("Please provide a description to the task.");
        }
        this.taskType = taskType;
        this.description = description;
        this.date = date;
    }

    /**
     * Creates the task, adds it to the task list, updates the data file and displays the the new task to the UI.
     *
     * @param tasks Task list component.
     * @param storage Storage component.
     * @param ui UI component.
     * @throws DukeException If invalid task type was provided.
     */
    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) throws DukeException {
        Task newTask;
        switch (taskType) {
        case "todo":
            newTask = new Todo(description);
            break;
        case "deadline":
            newTask = new Deadline(description, date);
            break;
        case "event":
            newTask = new Event(description, date);
            break;
        default:
            throw new DukeException("Invalid task type provided!");
        }
        tasks.addTask(newTask);
        assert tasks.doesContain(newTask) : "Should have added new task into TaskList";
        storage.updateTasks(tasks);
        ui.showAddedNewTask(newTask, tasks);
    }
}
