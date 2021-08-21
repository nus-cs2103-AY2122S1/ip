package main.java;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Event extends Task {

    @JsonProperty
    protected String at;

    /**
     * Constructor for new Event object.
     * @param description description of the event.
     * @param at date in which the event is to be held at.
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * Required for JackSon.
     */
    private Event() {}

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
