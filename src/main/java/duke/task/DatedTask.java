package duke.task;

abstract class DatedTask extends Task {
    protected String date;

    protected DatedTask(String description, String date) {
        this(description, date, false);
    }

    protected DatedTask(String description, String date, boolean isDone) {
        this.description = description;
        this.date = date;
        this.isDone = isDone;
    }

    public String toWriteString() {
        String isDone = (this.isDone ? "1" : "0");
        return DIVIDER + isDone + DIVIDER + this.description + DIVIDER + this.date;
    }

}
