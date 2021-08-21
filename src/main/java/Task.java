package main.java;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonIgnoreProperties(
        ignoreUnknown = true
)
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Todo.class, name = "Todo"),
        @JsonSubTypes.Type(value = Deadline.class, name = "Deadline"),
        @JsonSubTypes.Type(value = Event.class, name = "Event")
})
public class Task {

    @JsonProperty
    protected String description;

    @JsonProperty
    protected boolean isDone;

    /**
     * Constructor of superclass Task.
     * @param description description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Required for JackSon.
     */
    public Task() {

    }

    /**
     * Gets the status of the current task object
     * @return the string status where X means done and blank is not done
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks this task as done by changing the isDone boolean to true
     */
    public void markAsDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}