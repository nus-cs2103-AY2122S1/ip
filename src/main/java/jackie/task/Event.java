package jackie.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;

import jackie.JackieException;

/**
 * A Class that extends the Task class.
 * It is specifically designed for a Task with time of occurrence.
 *
 * @author Gu Geng
 */
public class Event extends Task {
    private String time;
    private LocalDate localDate;

    /**
     * Returns a Event object using a String.
     *
     * @param content A String containing information that is possibly enough to create a Event object.
     * @throws jackie.JackieException Will be thrown if the information in content is insufficient/incorrect.
     */
    public Event(String... content) throws jackie.JackieException {
        super(content);
        try {
            // manipulate the array given to get the task time
            ArrayList<String> holder = new ArrayList<>(Arrays.asList(content));
            holder.remove(0);
            holder.trimToSize();
            int dateMarkIndex = holder.indexOf("/");
            String taskTime = holder.get(dateMarkIndex + 1);

            time = taskTime;
            this.localDate = LocalDate.parse(time);
        } catch (DateTimeParseException e) {
            throw new jackie.JackieException(" D: SORZ but I only understand date in yyyy-MM-dd format!");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new JackieException(" D: Pls add new event tasks in format of event task / yyyy-MM-dd!");
        }
    }

    /**
     * Returns the time factor of the Task in String form where applicable.
     *
     * @return A String indicating the time factor of the Task.
     */
    public String getTime() {
        return localDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

    @Override
    public <T extends Task> T getCopy() throws JackieException {
        String inputHolder = "event " + this.getContent() + " / " + time;
        //noinspection unchecked
        return (T) new Event(inputHolder.split(" "));
    }

    /**
     * Overrides the toString method.
     *
     * @return A String representation of the Event object in specified format.
     */
    @Override
    public String toString() {
        return String.format("[E][%s] %s (at: %s)",
                getStatus() ? "X" : " ", getContent(), getTime());
    }

    /**
     * Implements the record abstract method from Task.
     *
     * @return A String of the Event object in specified format for starage purpose.
     */
    public String record() {
        return String.format("E | %s | %s | %s",
                getStatus() ? "1" : "0", getContent(), time);
    }

    /**
     * Implements the getType method from Task.
     *
     * @return A String indicating the type of Event as a Task.
     */
    public String getType() {
        return "E";
    }

    /**
     * Implements the hasSchedule method from Task.
     *
     * @return A boolean indicating if Event has a schedule.
     */
    public boolean hasSchedule() {
        return true;
    }
}
