package duke.commands;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task{
    private LocalDateTime when;

    public Event(String description, boolean isDone, LocalDateTime when) {
        super(description, isDone, 'E');
        this.when = when;
    }

    @Override
    public String checkStatus() {
        return super.checkStatus() + " " + this.showWhen();
    }

    public String showWhen(){
        return String.format("(at: %s)", when.format(DateTimeFormatter.ofPattern("dd MMM yyyy HHmm")));
    }

    @Override
    public String toString(){
        return super.toString() + String.format("|%s", when);
    }
}
