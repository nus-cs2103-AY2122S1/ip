package main.java;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Deadline extends Task {

    @JsonProperty
    protected String by;

    /**
     * Constructor to create a new Deadline object.
     * @param description the description of the Deadline Task.
     * @param by due date of Deadline Task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Required for JackSon.
     */
    private Deadline() {}

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
