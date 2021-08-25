package Duke.Task;

import Duke.Main.DukeException;
import Duke.Main.Parser;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Event extends Task {

    private String taskDescription;
    private LocalDate date;
    private String date1;

    public Event(String taskName) {
        super(taskName.split("/at")[0].trim());
        if (!taskName.contains("/at")) {
            throw new DukeException("", DukeException.TYPE.INCOMPLETE);
        }
        String[] divide = taskName.split("/at");
        this.taskDescription = divide[0];
        String taskTime = divide[1].trim();
        try {
            this.date = LocalDate.parse(taskTime);
        } catch (DateTimeParseException e) {
            throw new DukeException("", DukeException.TYPE.INVALID_FORMAT);
        }
        date1 = Parser.convert(this.date);
    }

    @Override
    public String save() {
        return this.toString().replaceAll(date1, Parser.reverse(date1));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at " + date1 + ")";
    }

    public String getTaskDescription() {
        return this.taskDescription;
    }

    public LocalDate getDate() {
        return this.date;
    }
}
