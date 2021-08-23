package duke.tasks;

import duke.utils.DukeDateTime;
import duke.utils.DukeException;

import java.time.format.DateTimeFormatter;

public abstract class Task {
    protected final static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    protected String desc;
    protected boolean done;
    protected String details = new String();
    protected DukeDateTime dateTime;
    public final static DukeException FORMAT_EXCEPTION = 
            new DukeException("I don't understand this entry, enter 'help' to learn the correct formatting!");

    public Task() {}

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
    public String toString() {
        return (done ? "[X] " : "[ ] ") + desc;
    }

}
