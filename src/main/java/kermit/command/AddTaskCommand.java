package kermit.command;

import kermit.KermitException;
import kermit.TaskList;
import kermit.Ui;
import kermit.Storage;
import kermit.tasks.Deadline;
import kermit.tasks.Event;
import kermit.tasks.Task;
import kermit.tasks.ToDo;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class AddTaskCommand extends Command {
    private Task task;

    // parses dates in form dd-mm-yyyy to localdate
    private static LocalDate parseDate(String dateString) throws KermitException {
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
            this.task = new ToDo(description);
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
                    throw new KermitException("Invalid task type!");
                }
            } catch (DateTimeParseException e) {
                throw new KermitException("That is an invalid date!");
            }
        }
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws KermitException {
        taskList.add(task);
        ui.showAddTaskMessage(task, taskList);
        storage.save(taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}