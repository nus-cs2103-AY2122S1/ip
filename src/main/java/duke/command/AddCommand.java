package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.TaskList;
import duke.task.Todo;

public class AddCommand extends Command {

    private String description;
    private String date;
    private String time;
    private String type;

    /**
     * Constructs a AddCommand object for Todo items.
     *
     * @param description Description of task.
     * @param type        Type of task.
     */
    public AddCommand(String description, String type) {
        super(false);
        this.description = description;
        this.type = type;
    }

    /**
     * Constructs a AddCommand object for Deadline items.
     *
     * @param description Description of task.
     * @param type        Type of task.
     */
    public AddCommand(String description, String date, String type) {
        super(false);
        this.description = description;
        this.date = date;
        this.type = type;
    }

    /**
     * Constructs a AddCommand object for Event items.
     *
     * @param description Description of task.
     * @param type        Type of task.
     */
    public AddCommand(String description, String date, String time, String type) {
        super(false);
        this.description = description;
        this.date = date;
        this.time = time;
        this.type = type;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        assert tasks != null : "Task list is not initialized";
        switch (this.type) {
        case "todo":
            Todo todo = tasks.createTodo(this.description);
            storage.writeToFile(tasks.getAllTasks());
            return ui.displaySuccessMessage(todo, tasks.length());
        case "deadline":
            Deadline dl = tasks.createDeadline(this.description, this.date);
            storage.writeToFile(tasks.getAllTasks());
            return ui.displaySuccessMessage(dl, tasks.length());
        case "event":
            Event event = tasks.createEvent(this.description, this.date, this.time);
            storage.writeToFile(tasks.getAllTasks());
            return ui.displaySuccessMessage(event, tasks.length());
        default:
            return "";
        }
    }
}
