import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

public class AddCommand extends Command {
    private String taskType;
    private String description;
    private LocalDate date;

    public AddCommand(String taskType, String description, LocalDate date) throws DukeException {
        if (description.equals("")) {
            throw new DukeException("Please provide a description to the task.");
        }
        this.taskType = taskType;
        this.description = description;
        this.date = date;
    }

    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) throws DukeException {
        try {
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
            storage.updateTasks(tasks);
            ui.showAddedNewTask(newTask, tasks);
        } catch (DateTimeParseException e) {
            throw new DukeException("Date provided should be in YYYY-MM-DD format");
        }
    }
}
