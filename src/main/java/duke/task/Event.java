package duke.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task with a <code>description</code> corresponding to the content
 * and a <code>at</code> time representing the event time.
 */
public class Event extends Task {
    protected LocalDateTime at;
    private Boolean hasTime = true;

    public Event(String description, String at) {
        super(description);
        this.at = parseTime(at);
    }

    /**
     * Gets an At time of an Event object.
     *
     * @return at time of type LocalDateTime
     */
    public LocalDateTime getAt() {
        return at;
    }

    /**
     * Parses a time String into a LocalDateTime.
     *
     * @param time a String of format dd/mm/yyyy hhmm(optional)
     * @return transformed time of type LocalDateTime
     */
    public LocalDateTime parseTime(String time) {
        String newDate;
        String[] str = time.split(" ");
        String[] oldDate = str[0].split("/");
        LocalDateTime localTime;
        if(str.length > 1) {
            String hour = str[1].substring(0,2);
            String min = str[1].substring(2,4);

            localTime = LocalDateTime.of(Integer.parseInt(oldDate[2]), Integer.parseInt(oldDate[1]), Integer.parseInt(oldDate[0]),
                    Integer.parseInt(hour), Integer.parseInt(min));
        } else {
            localTime = LocalDate.of(Integer.parseInt(oldDate[2]), Integer.parseInt(oldDate[1]), Integer.parseInt(oldDate[0]))
                    .atStartOfDay();
            hasTime = false;
        }
        return localTime;
    }


    @Override
    public String toString() {
        String string = "[E]" + super.toString() + "(at: " ;
        if (hasTime) {
            string += at.format(DateTimeFormatter.ofPattern("HH:mm, MMM dd yyyy")) + ")";
        } else {
            string += at.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
        }
        return string;
    }

    public static void main(String[] args) {
        Event e = new Event("return book", "2/12/2019 1800");
        System.out.println(e);
    }
}

