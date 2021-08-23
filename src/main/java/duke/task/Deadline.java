package duke.task;

import duke.DukeDate;

import java.time.LocalDate;

public class Deadline extends Task{
    private LocalDate time;

    public Deadline(String name, LocalDate time) {
        super(name, "D");
        this.time = time;
    }

    public Deadline(String name, LocalDate time, boolean completed) {
        super(name,  completed, "D");
        this.time = time;
    }

    public LocalDate getTime() {
        return time;
    }

    public String getSaveFormat() {
        return String.format(
                "%s|%s",
                super.getSaveFormat(),
                DukeDate.formatDateSave(this.time)
        );
    }
}
