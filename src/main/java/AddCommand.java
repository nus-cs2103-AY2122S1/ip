import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class AddCommand extends Command{

    private enum TaskType {
        TODO, EVENT, DEADLINE
    }

    private final String taskType;
    private final String taskDescriptions;

    public AddCommand(String taskType, String taskDescriptions) {

        this.taskType = taskType;
        this.taskDescriptions = taskDescriptions;
    }

    private String[] validateCommand()
            throws IncompleteDescriptionException,
                   InvalidDateFormatException {

        String[] descriptionComponents = this.taskDescriptions.split(" /by | /at ", 2);
        String descriptionMessage = "The description of a %s is incomplete.";
        String dateMessage = "Please specify the date in yyyy-mm-dd format!";

        if (this.taskType.equals("TODO"))
            return descriptionComponents;

        if (descriptionComponents.length < 2 || descriptionComponents[1].trim().isEmpty())
            throw new IncompleteDescriptionException(String.format(descriptionMessage, this.taskType.toLowerCase()));

        if (!descriptionComponents[1].matches("\\d{4}-\\d{2}-\\d{2}"))
            throw new InvalidDateFormatException(dateMessage);

        return descriptionComponents;
    }

    private Task createTask(String description, LocalDate date) {

        switch (TaskType.valueOf(this.taskType)) {
        case TODO:
            return new Todo(description);
        case EVENT:
            return new Event(description, date);
        case DEADLINE:
            return new Deadline(description, date);
        default:
            return null;
        }
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage)
            throws IncompleteDescriptionException,
            InvalidDateFormatException {
        try {
            String[] taskComponents = validateCommand();
            String description = taskComponents[0];
            LocalDate date = this.taskType.equals("TODO") ? null : LocalDate.parse(taskComponents[1]);

            Task task = createTask(description, date);
            tasks.add(task);
            storage.save(tasks);

            System.out.println("\tGot it. I've added this task:");
            System.out.println("\t " + task);
            System.out.println("\tNow you have " + tasks.getTaskNum() + " tasks in the list.");

        } catch (DateTimeParseException e) {
            new Ui().showError(e.getMessage());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
