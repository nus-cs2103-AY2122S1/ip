package duke;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Deadlines are Tasks that have an attached date as the time a Task needs to be completed by.
 * @author spdpnd98.
 */
public class Deadline extends Task {
    protected static final String DEADLINE_LABEL = "D";
    private Date date;

    /**
     * Creates a Deadline instance.
     *
     * @param str the arguments provided to chatbot to initialize the Deadline Task.
     * @throws DukeDateParseException when there is an error parsing the date provided.
     * @throws DukeArgumentException when there is insufficient/incorrect argument(s) provided.
     */
    public Deadline(String str) throws DukeDateParseException, DukeArgumentException {
        super(str.split(" /", 2)[0]);
        String[] command = str.split(" /", 2);
        if (command.length == 1) {
            throw new DukeArgumentException("No commands specified for task 'deadline'!");
        }
        String[] commandAndDate = command[1].split(" ", 2);
        if (!commandAndDate[0].equals("by")) {
            throw new DukeArgumentException("Unknown command provided to duke.Deadline! did you use '/by'?");
        } else if (commandAndDate.length == 1) {
            throw new DukeArgumentException("No date specified!");
        }

        try {
            this.date = new SimpleDateFormat("dd/mm/yyyy").parse(commandAndDate[1]);
        } catch (ParseException e) {
            throw new DukeDateParseException(e);
        }
    }

    protected Deadline(String isDone, String description, String date) throws DukeDateParseException {
        super(description);
        try {
            this.date = (new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy")).parse(date);
            this.isDone = Boolean.valueOf(isDone);
        } catch (ParseException e) {
            throw new DukeDateParseException(e);
        }
    }

    @Override
    protected String getTaskType() {
        return DEADLINE_LABEL;
    }

    @Override
    protected String getDate() {
        return this.date.toString();
    }

    @Override
    public String toString() {
        return "[" + DEADLINE_LABEL + "]" + super.toString() + " (by: " + date + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Deadline) {
            Deadline deadline = (Deadline) obj;
            return deadline.date.equals(this.date) && deadline.description.equals(this.description);
        }
        return false;
    }
}
