package katheryne.task;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.As.PROPERTY;
import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NAME;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

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
    
    public boolean find(String keyword) {
        return this.description.contains(keyword);
    }

    // getters & setters (needed for jackson)
    protected void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
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
    
    /**
     * Checks if an object is equal to this task.
     * The object is equal if and only if it is a task of the same type, with the same description
     *
     * @param obj Object to compare to this task.
     * @return Whether object is equal to this task.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Task) {
            Task other = (Task) obj;
            return description.equals(other.getDescription());
        }
        return false;
    }
}
