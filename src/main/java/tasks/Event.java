package tasks;

import exceptions.EmptyEventBodyException;
import exceptions.InvalidEventBodyException;

public class Event extends Task {
    private final String at;

    public Event(String eventDataText) throws InvalidEventBodyException, EmptyEventBodyException {
        if (eventDataText == null || eventDataText.isEmpty()) {
            throw new EmptyEventBodyException();
        }
        String[] eventData = eventDataText.split("/at ", 2);
        if (eventData.length != 2 || eventData[0].isEmpty() || eventData[1].isEmpty()) {
            throw new InvalidEventBodyException();
        }
        super.setDescription(eventData[0].trim());
        this.at = eventData[1].trim();
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
