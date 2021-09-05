package katheryne.task;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.As.PROPERTY;
import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NAME;

@JsonTypeInfo(use = NAME, include = PROPERTY)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Todo.class, name = "katheryne.task.Todo"),
        @JsonSubTypes.Type(value = Event.class, name = "katheryne.task.Event"),
        @JsonSubTypes.Type(value = Deadline.class, name = "katheryne.task.Deadline")
})

/**
 * Class used for Tasks.
 */
public abstract class Task {
    private String description;
    private Boolean isDone = false;

    public Task() {

    }

    public Task(String description) {
        this.description = description;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    // getters & setters (needed for jackson)
    protected void setDescription(String description) {
        this.description = description;
    }

    protected void setIsDone(Boolean done) {
        this.isDone = done;
    }

    @Override
    public String toString() {
        if (this.isDone) {
            return "[X] " + description;
        } else {
            return "[ ] " + description;
        }
    }
}
