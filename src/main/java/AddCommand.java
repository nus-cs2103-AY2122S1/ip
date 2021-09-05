import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class AddCommand extends Command {

    private String taskType;
    private String description;

    public AddCommand(String taskType, String description) {
        this.taskType = taskType;
        this.description = description;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        switch(taskType) {
            case "todo":
                Todo newTodo = new Todo(description);
                tasks.addTask(newTodo);
                ui.printTemplate(" Got it. I've added this task:\n" + "  " + newTodo + System.lineSeparator() + " Now you have " + tasks.getSize() + " tasks in the list.");
                storage.update(tasks);
                break;
            case "deadline":
                try {
                    String[] parsedAdd = description.split(" /by ", 2);
                    LocalDate date = LocalDate.parse(parsedAdd[1]);
                    Deadline newDeadline = new Deadline(parsedAdd[0], date);
                    tasks.addTask(newDeadline);
                    ui.printTemplate(" Got it. I've added this task:\n" + "  " + newDeadline + System.lineSeparator() + " Now you have " + tasks.getSize() + " tasks in the list.");
                    storage.update(tasks);
                    break;
                } catch (DateTimeParseException e) {
                    throw new DukeException("Please use the specified date format (YYYY-MM-DD)!");
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new DukeException("Please specify a description for this deadline.");
                }
            case "event":
                try {
                    String[] parsedAdd = description.split(" /at ", 2);
                    LocalDate date = LocalDate.parse(parsedAdd[1]);
                    Event newEvent = new Event(parsedAdd[0], date);
                    tasks.addTask(newEvent);
                    ui.printTemplate(" Got it. I've added this task:\n" + "  " + newEvent + System.lineSeparator() + " Now you have " + tasks.getSize() + " tasks in the list.");
                    storage.update(tasks);
                    break;
                } catch (DateTimeParseException e) {
                    throw new DukeException("Please use the specified date format (YYYY-MM-DD)!");
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new DukeException("Please specify a description for this event.");
                }
        }
    }
}
