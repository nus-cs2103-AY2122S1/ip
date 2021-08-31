package duke.Tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private TaskType type = TaskType.DEADLINE;
    private LocalDateTime timeBy;

    public Deadline (String description, String timeBy){
        super(description);
        this.timeBy = LocalDateTime.parse(timeBy, DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
    }

    public Deadline (String description, String timeBy, boolean isDone) {
        super(description, isDone);
        this.timeBy = LocalDateTime.parse(timeBy, DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
    }

    @Override
    public String showTask(){
        return "[D][" + (isDone ? "✗" : " ") + "] " + description + " (by: " + timeBy.format(DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm")) + ")";
    }

    @Override
    public String saveTask() {
        return "D | " + (isDone ? 1 : 0) + " | " + description + " | " + timeBy.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
    }

}
