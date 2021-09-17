package tasks;

import duke.Parser;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import exceptions.DukeException;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The Event class encapsulates all the details of each event.
 *
 * @author Quan Teng Foong
 */
public class Event extends Task implements Recurring {
    private LocalDate eventDate;
    private final Recurrence recurrence;

    /**
     * Constructor for Event object.
     *
     * @param message name of Event
     * @param extraDetails date of Event and optionally, a Recurrence
     */
    public Event(String message, String extraDetails) {
        super(message);
        String[] eventDateAndRecurrence = Parser.splitBy(extraDetails, "/recur");
        this.eventDate = LocalDate.parse(eventDateAndRecurrence[0]);
        this.recurrence = Recurring.stringToRecurrence(eventDateAndRecurrence[1]);
    }

    /**
     * Overrides toString() method.
     * @return String representation of the Event object.
     */
    @Override
    public String toString() {
        String recurrenceString = this.recurrence != Recurrence.NEVER
                ? "[recurs: " + Recurring.recurrenceToString(this.recurrence) + "]"
                : "";
        return "[E]" + super.toString() + " (at: "
                + eventDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ") "
                + recurrenceString;
    }

    /**
     * Converts contents to a storable String.
     * 
     * @return a String that represents this Event in storage
     */
    @Override
    public String toStorage() {
        return "E|" + super.toStorage() + "/at " + this.eventDate + "/recur "
                + Recurring.recurrenceToString(this.recurrence);
    }

    /**
     * Sets the task as complete. If it is a recurring task, also creates a new task at eventDate + 1 recurrence later.
     *
     * @param taskList the current TaskList
     */
    @Override
    public void doTask(TaskList taskList) {
        if (this.recurrence != Recurrence.NEVER) {
            this.incrementBy(1);
            taskList.add(this.clone());
            this.incrementBy(-1);
        }
        super.doTask();
    }

    /**
     * Increments the date of the Event by a number of days/weeks/months depending on the recurrence.
     *
     * @param noOfIncrements no of recurrences to add
     */
    public void incrementBy(int noOfIncrements) {
        switch (this.recurrence) {
        case DAILY:
            this.eventDate = this.eventDate.plusDays(noOfIncrements);
        case WEEKLY:
            this.eventDate = this.eventDate.plusWeeks(noOfIncrements);
        case MONTHLY:
            this.eventDate = this.eventDate.plusMonths(noOfIncrements);
        default:
            //do nothing
        }
    }

    /**
     * Overrides clone method.
     *
     * @return another instance of the exact same Event
     */
    @Override
    public Task clone() {
        return new Event(this.getMessage(), this.eventDate + "/recur "
                + Recurring.recurrenceToString(this.recurrence));
    }
}
