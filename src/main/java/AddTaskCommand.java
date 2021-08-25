import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class AddTaskCommand extends Command {
    Task task;

    // parses dates in form dd-mm-yyyy to localdate
    private static LocalDate parseDate(String dateString) throws KermitException{
        String[] components = dateString.split("-");
        try {
            String day = components[0];
            String month = components[1];
            String year = components[2];
            LocalDate parsedDate = LocalDate.parse(String.join("-", year, month, day));
            return parsedDate;
        } catch (IndexOutOfBoundsException | DateTimeParseException e) {
            throw new KermitException("That is an invalid date!");
        }
    }

    public AddTaskCommand(String taskType, String description, String flag) throws KermitException {
        if (description.equals("")) {
            throw new KermitException("The argument of the " + taskType + " command cannot be empty!");
        }

        if (taskType.equals("todo")) {
            this.task = new ToDos(description);
        } else {
            try {
                LocalDate date = parseDate(flag);
                switch (taskType) {
                    case "deadline":
                        task = new Deadline(description, date);
                        break;
                    case "event":
                        task = new Event(description, date);
                        break;
                    default:
                        throw new KermitException("Invalid tasktype!");
                }
            } catch (DateTimeParseException e) {
                throw new KermitException("That is an invalid date!");
            }
        }
    }

    @Override
    void execute(ToDo taskList, Ui ui, Storage storage) throws KermitException {
        taskList.add(task);
        ui.showAddTaskMessage(task, taskList);
        storage.save(taskList);
    }

    @Override
    boolean isExit() {
        return false;
    }
}