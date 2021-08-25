package Duke.Task;

import Duke.Main.DukeException;
import Duke.Main.Parser;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {

    private String taskDescription;
    private LocalDate date;
    private String date1;
    public Deadline(String taskName) {
        super(taskName.split("/by")[0].trim());
        if (!taskName.contains("/by")) {
            throw new DukeException("", DukeException.Type.INCOMPLETE);
        }
        String[] divide = taskName.split("/by");
        this.taskDescription = divide[0].trim();
        String taskTime = divide[1].trim();
        try {
            this.date = LocalDate.parse(taskTime);
        } catch (DateTimeParseException e) {
            throw new DukeException("", DukeException.Type.INVALID_FORMAT);
        }
        date1 = Parser.convert(this.date);
    }

    @Override
    public String save() {
        return this.toString().replaceAll(date1, Parser.reverse(date1));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by " + date1 + ")";
    }

    public String getTaskDescription() {
        return this.taskDescription;
    }

    public LocalDate getDate() {
        return this.date;
    }
}
