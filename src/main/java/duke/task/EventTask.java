package duke.task;

/**
 * A task specifying an event with a date and time.
 */
public class EventTask extends Task {
    private String dateTime;

    public EventTask(String task) {
        super(task);
        String[] arr = task.split(" /at ", 2);
        this.dateTime = arr.length >= 2 ? arr[1] : "";
        this.name = arr[0];
    }

    public EventTask(String name, boolean completed) {
        super(name, completed);
        this.dateTime = "";
    }

    public EventTask(String name, boolean completed, String date) {
        super(name, completed);
        this.dateTime = date;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.dateTime + ")";
    }

    @Override
    public String getDate() {
        return this.dateTime;
    }

}
