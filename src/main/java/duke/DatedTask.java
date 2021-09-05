package duke;

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

}
