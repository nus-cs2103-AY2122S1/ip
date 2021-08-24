package duke.task;

import duke.main.DukeException;

import java.time.LocalDate;
import java.util.Objects;

public class Deadline extends Task {

    private LocalDate by;

    public Deadline(String description, String by) {
        super(description);
        this.by = Task.parseTime(by);
    }

    public Deadline(String descAndTime) throws DukeException {
        this(extractDesc(descAndTime), extractTime(descAndTime));
    }

    public Deadline(String desc, String time, boolean completed) {
        this(desc, time);
        super.completed = completed;
    }

    private static String extractDesc(String descAndTime) throws DukeException {
        if (descAndTime.equals("")) {
            throw new DukeException("\t☹ OOPS!!! Your deadline needs a description.\n");
        }
        return descAndTime.split(" by ")[0];
    }

    private static String extractTime(String descAndTime) throws DukeException {
        try {
            return descAndTime.split(" by ")[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("\t☹ OOPS!!! You need to specify a time.\n");
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + Task.printTime(by) + ")";
    }

    @Override
    public String storageString() {
        return "D | " + super.completed + " | " + super.description + " | " + this.by;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        Deadline deadline = (Deadline) o;
        return by.equals(deadline.by);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), by);
    }
}
