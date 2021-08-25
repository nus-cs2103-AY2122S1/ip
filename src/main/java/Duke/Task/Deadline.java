package Duke.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDate date;

    public Deadline(String description, LocalDate date) {
        super(description);
        this.taskType = TaskType.D;
        this.date = date;
    }

    @Override
    public String toString() {
        String str = super.toString();
        System.out.println(date);
        str += String.format(" (by: %s)", this.date.format(DateTimeFormatter.ofPattern("dd MMM yyyy")));
        return str;
    }

    @Override
    public String getDataString() {
        return String.format("%s_~_%s_~_%s_~_%s", this.taskType, this.getStatusInt(), this.description, this.date.format(DateTimeFormatter.ofPattern("d/MM/yyyy")));
    }
}
