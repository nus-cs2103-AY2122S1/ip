import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class AddTask extends Command {
    Task task;

    public AddTask(String taskType, String description, String flag) throws KermitException {
        if (description.equals("")) {
            throw new KermitException("The argument cannot be blank!");
        }

        if (taskType.equals("todo")) {
            this.task = new ToDos(description);
        } else {
            try {
                LocalDate date = LocalDate.parse(flag);
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