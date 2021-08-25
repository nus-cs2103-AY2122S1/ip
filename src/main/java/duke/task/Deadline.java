package duke.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task with a <code>description</code> corresponding to the content
 * and a <code>by</code> time representing the deadline time.
 */
public class Deadline extends Task{
    protected LocalDateTime by;
    private Boolean hasTime = true;

    public Deadline(String description, String by) {
        super(description);
        this.by = parseTime(by);
    }

    /**
     * Gets a By time of a Deadline object.
     *
     * @return by time of type LocalDateTime
     */
    public LocalDateTime getBy() {
        return by;
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
        if (str.length > 1) {
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
        String string = "[D]" + super.toString() + "(by: " ;
        if (hasTime) {
            string += by.format(DateTimeFormatter.ofPattern("HH:mm, MMM dd yyyy")) + ")";
        } else {
            string += by.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
        }
        return string;
    }

    public static void main(String[] args) {
        Deadline d = new Deadline("return book", "2/12/2019 1800");
        System.out.println(d);
    }
}
