package duke.task;

import duke.date.Date;

public abstract class DatedTask extends Task {
    protected Date date;

    protected DatedTask(String description, Date date) {
        super(description);
        this.date = date;
    }

    public Date getDate() {
        return date;
    }
}
