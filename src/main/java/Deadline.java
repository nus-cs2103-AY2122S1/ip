import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    protected LocalDateTime by;

    public Deadline(String description, LocalDateTime by){
        super(description);
        this.by = by;
    }

    @Override
    public String checkStatus() {
        return "[D]" + (isDone ? "[X]" : "[ ]") + " " + this.showDescription() + " " + this.showDeadline();
    }

    public String showDeadline(){
        return String.format("(by: %s)", by.format(DateTimeFormatter.ofPattern("dd MMM yyyy HHmm")));
    }
}
