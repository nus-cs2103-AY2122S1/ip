package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Deadline extends Task {
    private final LocalDateTime deadline;
    private final static String symbol = "[D]";

    public Deadline(String action, LocalDateTime deadline){
        super(action);
        this.deadline = deadline;
    }

    @Override
    public String toSaveFormat() {
        return String.format("%s||%s||%s||%s", symbol, super.isComplete(),
                super.getAction(), this.deadline.format(DateTimeFormatter.ofPattern("d/M/yyyy HHmm")));
    }

    @Override
    public String toString(){
        return String.format("%s%s (by: %s)", symbol, super.toString(),
                this.deadline.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM, FormatStyle.SHORT)));
    }
}
