package agent.tasks;

import agent.exceptions.EmptyEventBodyException;
import agent.exceptions.InvalidEventBodyException;

/**
 * Represents a completable <code>Task</code> with a description and a venue.
 *
 * @author kevin9foong
 */
public class Event extends Task {
    private final String at;

    /**
     * Instantiates an <code>Event</code> from its data String representation.
     *
     * @param eventDataText String containing data in the form 'description /at venue'.
     * @throws InvalidEventBodyException thrown when the data String representation is invalid.
     * @throws EmptyEventBodyException   thrown when the description or venue is missing from the
     *                                   data String representation.
     */
    public Event(String eventDataText) throws InvalidEventBodyException, EmptyEventBodyException {
        if (eventDataText == null || eventDataText.isEmpty()) {
            throw new EmptyEventBodyException();
        }
        String[] eventData = eventDataText.split("/at ", 2);
        boolean isEventBodyInvalid = eventData.length != 2 || eventData[0].isEmpty() || eventData[1].isEmpty();
        if (isEventBodyInvalid) {
            throw new InvalidEventBodyException();
        }
        super.setDescription(eventData[0].trim());
        this.at = eventData[1].trim();
    }


    /**
     * Constructs an instance of <code>Event</code> with the provided description,
     * completion status and venue.
     *
     * @param description description of what the <code>Event</code> entails.
     * @param isDone      completion status of <code>Event</code>.
     * @param at          venue which <code>Event</code> is held at.
     */
    public Event(String description, boolean isDone, String at) {
        super.setDescription(description);
        super.setIsDone(isDone);
        this.at = at;
    }

    @Override
    public String getTaskRepresentation() {
        return TaskType.EVENT + " |;; " + super.getTaskRepresentation() + this.at + " |;; ";
    }

    /**
     * Returns the <code>String</code> representation representing the task type,
     * completion status, description and venue of this <code>Event</code>.
     *
     * @return <code>String</code> representation of this <code>Event</code>.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
