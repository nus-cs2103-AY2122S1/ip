package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class AddTaskCommand implements  Command {
    private String type, detail;
    private Task task;

    public AddTaskCommand(String[] arr) {
        this.type = arr[0];
        this.detail = arr[1];
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        if (type.equals("todo")) {
            task = new ToDo(detail);

        } else if (type.equals("deadline")) {
            String[] description = detail.split("/by ", 2);

            if (description.length == 1) {
                throw new DukeException("Invalid duke.task.Deadline entry.Try something like: deadline HW due /by 19/8/2021 14:00");
            } else {
                try {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                    task = new Deadline(description[0].trim(), LocalDateTime.parse(description[1].trim(), formatter));
                } catch (DateTimeParseException e) {
                    throw new DukeException("Unable to parse time. Valid deadline format: deadline do HW /by 19/08/2021 23:59");
                }
            }

        } else {
            String[] description = detail.split("/at ", 2);
            if (description.length == 1) {
                throw new DukeException("Invalid duke.task.Event entry. Try something like: event meeting /at 19/08/2021 14:00");
            } else {
                try {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                    task = new Event(description[0].trim(), LocalDateTime.parse(description[1].trim(), formatter));
                } catch (DateTimeParseException e) {
                    throw new DukeException("Unable to parse time. Valid event format: event meeting /at 19/08/2021 23:59");
                }
            }
        }

        if (task != null) {
            taskList.addTask(task);
            ui.echo("Got it! I added this duke.task: " + task);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
