package duke.tasks;

import duke.utils.DukeDateTime;
import duke.utils.DukeException;

public abstract class Task {
    public final static DukeException FORMAT_EXCEPTION = new DukeException("I don't understand this entry, enter " + 
            "help (cmd) for assistance.");
    protected boolean isDone;
    protected String desc = "";
    protected String details = "";
    protected DukeDateTime dateTime = new DukeDateTime();

    /**
     * Instantiates an empty task.
     */
    public Task() {
    }

    protected Task(String desc) {
        this.desc = desc;
    }

    /**
     * Instantiates an Event based on a database entry.
     */
    public Task(String desc, boolean isDone) {
        this.desc = desc;
        this.isDone = isDone;
    }

    /**
     * Adds a description to this task.
     *
     * @param desc
     */
    public void addDesc(String desc) {
        this.desc = desc;
    }

    /**
     * Marks this task as complete.
     */
    public void markComplete() {
        isDone = true;
    }

    abstract void addTime(String time) throws DukeException;

    /**
     * Returns this task formatted as a database entry.
     *
     * @return String representing this event in database format.
     */
    public boolean matchWord(String word) {
        return desc.contains(word) || details.contains(word);
    }

    public abstract String toDatabaseFormat();

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Task) {
            Task that = (Task) obj;
            return (desc.equals(that.desc) && isDone == that.isDone && details.equals((that.details)) && dateTime.equals(that.dateTime));
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return (isDone ? "[X] " : "[ ] ") + desc;
    }

}
