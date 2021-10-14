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

public abstract class Task {
    private String description;
    private Boolean isDone = false;

    public Task() {

    }

    /**
     * Constructor for the task.
     * 
     * @param description
     */
    public Task(String description) {
        this.description = description;
    }

    /**
     * Mark a task as complete. This currently cannot be undone.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Checks if the keyword is in the description
     * 
     * @param keyword
     * @return
     */
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
