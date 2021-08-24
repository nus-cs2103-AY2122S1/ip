import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class AddTaskCommand implements  Command {
    private String type, detail;
    private Task t;

    public AddTaskCommand(String[] arr) {
        this.type = arr[0];
        this.detail = arr[1];
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        if (type.equals("todo")) {
            t = new ToDo(detail);

        } else if (type.equals("deadline")) {
            String[] desc = detail.split("/by ", 2);

            if (desc.length == 1) {
                throw new DukeException("Invalid Deadline entry.Try something like: deadline HW due /by 19/8/2021 14:00");
            } else {
                try {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                    t = new Deadline(desc[0].trim(), LocalDateTime.parse(desc[1].trim(), formatter));
                } catch (DateTimeParseException e) {
                    throw new DukeException("Unable to parse time. Valid deadline format: deadline do HW /by 19/08/2021 23:59");
                }
            }

        } else {
            String[] desc = detail.split("/at ", 2);
            if (desc.length == 1) {
                throw new DukeException("Invalid Event entry. Try something like: event meeting /at 19/08/2021 14:00");
            } else {
                try {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                    t = new Event(desc[0].trim(), LocalDateTime.parse(desc[1].trim(), formatter));
                } catch (DateTimeParseException e) {
                    throw new DukeException("Unable to parse time. Valid event format: event meeting /at 19/08/2021 23:59");
                }
            }
        }

        if (t != null) {
            taskList.addTask(t);
            ui.echo("Got it! I added this task: " + t);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
