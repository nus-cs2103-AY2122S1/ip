package duke.tasks;

import duke.utils.DukeDateTime;
import duke.utils.DukeException;

public abstract class Task {
    public final static DukeException FORMAT_EXCEPTION = new DukeException("I don't understand this entry, enter " + 
            "'help' to learn the correct formatting!");
    protected String desc;
    protected boolean done;
    protected String details = "";
    protected DukeDateTime dateTime = new DukeDateTime();

    public Task() {
    }

    public Task(String desc) {
        this.desc = desc;
    }

    public Task(String desc, boolean done) {
        this.desc = desc;
        this.done = done;
    }

    public void addDesc(String desc) {
        this.desc = desc;
    }

    public void markComplete() {
        done = true;
    }

    abstract void addTime(String time) throws DukeException;

    public abstract String toDB();

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Task) {
            Task that = (Task) obj;
            return (desc.equals(that.desc) && done == that.done && details.equals((that.details)) && dateTime.equals(that.dateTime));
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return (done ? "[X] " : "[ ] ") + desc;
    }

}
