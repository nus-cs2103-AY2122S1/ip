package duke.task;

import duke.util.DukeException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Event extends Task {

    private static final DateTimeFormatter DATE_SHORT_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final DateTimeFormatter DATE_MED_FORMATTER = DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG);

    private LocalDate on;

    public Event(String description, LocalDate on) {
        super(description);
        this.on = on;
    }

    private static void checkFormat(String formattedString) throws DukeException {
        int onIndex = formattedString.indexOf("/on ");
        if (onIndex == -1)
            onIndex = formattedString.length();

        String keyword = formattedString.split(" ", 2)[0];

        if (!keyword.startsWith("event"))
            throw new DukeException("I can't seem to find the event keyword");
        else if (formattedString.length() <= 6 || formattedString.substring(6, onIndex).isEmpty())
            throw new DukeException("the description of event cannot be empty");
        else if (onIndex == formattedString.length() || formattedString.length() < onIndex + 5)
            throw new DukeException("the [/on] time of event cannot be empty");
    }

    //Format: "Task.Event: [description] /by [on]
    public static Event create(String formattedString) throws DukeException {
        checkFormat(formattedString);

        int onIndex = formattedString.indexOf("/on ");
        LocalDate time = LocalDate.parse(
                formattedString.substring(onIndex + 4),
                DATE_SHORT_FORMATTER);

        return new Event(formattedString.substring(6, onIndex), time);
    }

    @Override
    public String toString() {
        char statusIcon = this.isDone ? 'X' : ' ';
        String timeString = DATE_MED_FORMATTER.format(this.on);

        return String.format("[%c] Task.Event: %s(on: %s)", statusIcon, this.description, timeString);
    }
}