package duke.task;

import duke.exceptions.UnclearInstructionException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private String atString;
    private LocalDate atDate;
    public Event(String description, LocalDate at) {
        super(description);
        this.atDate = at;
    }

    /**
     * Constructor method of Event.
     *
     * @param description Description of an event.
     * @param at Time of an event.                   
     */
    public Event(String description, String at) {
        super(description);
        this.atDate = LocalDate.parse(at);
    }

    /**
     * Another constructor method of Event.
     *
     * @param isDone Done indicator of an event.  
     * @param description Description of an event.
     * @param at Time of an event.                   
     */
    public Event(String isDone, String description, String at) {
        super(description, isDone.equals("1"));
        this.atDate = LocalDate.parse(at);
    }

    /**
     * Returns task description of a full command.
     *
     * @param text  Full command given by user.
     * @return Task description.
     * @throws UnclearInstructionException  If the given command cannot be properly extracted or has empty description.
     */
    public static String extractTaskDescription(String text) throws UnclearInstructionException {
        String[] contents = text.split(" ", 3);
        String task_type = contents[0];
        String description = "";

        if (contents.length != 3) {
            throw new UnclearInstructionException(
                    "OOPS!!! The description of an event cannot be extracted properly.");
        }

        int istart = text.indexOf(" ");
        int iend = text.indexOf("/");
        
        description = text.substring(istart + 1, iend - 1);
        
        if (description.equals("")) {
            throw new UnclearInstructionException(
                    "OOPS!!! The description of an event cannot be empty.");
        }
        return description;
    }


    /**
     * Returns event time of a full command.
     *
     * @param text  Full command given by user.
     * @return Event time.
     * @throws UnclearInstructionException If the given command cannot be properly extracted or has empty time.
     */
    public static String extractTaskTime(String text) throws UnclearInstructionException {
        String[] contents = text.split(" ", 3);
        if (contents.length != 3) {
            throw new UnclearInstructionException(
                    "OOPS!!! The description of an event cannot be extracted properly.");
        }

        int istart = text.indexOf(" ");
        int iend = text.indexOf("/");
        String time = text.substring(iend + 4);

        if (time.equals("")) {
            throw new UnclearInstructionException("OOPS!!! The time of an event cannot be empty.");
        }
        return time;
    }
    
    @Override
    public String toString() {
        return "[E]" + super.toString()
                + String.format(" (at: %s)", this.atDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }

    @Override
    public String toFileString() {
        return "E" + super.toFileString() + "/" + atDate;
    }
}