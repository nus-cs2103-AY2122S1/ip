package duke.tasks;

import duke.Ui;
import duke.exceptions.MissingEventDetailsException;

public class Event extends Task {
    protected String details;
    protected String taskType;

    public Event(String description, String details) {
        super(description);
        this.details = details;
        this.taskType = "E";
    }

    @Override
    public String getTaskType() {
        return this.taskType;
    }

    @Override
    public void markAsDone() {
        this.isDone = true;
        System.out.println(this.toString());
    }

    public static String getEventDescription(String input) throws MissingEventDetailsException {
        String[] strArr = input.split(" /at", 2);
        if (strArr.length < 2) {
            Ui.missingEventDetails();
            throw new MissingEventDetailsException();
        } else {
            return strArr[0] + " (at:" + strArr[1] + ")";
        }
    }

    public static String getEventDetails(String input) {
        String[] strArr = input.split("/at", 2);
        return strArr[1];
    }

    @Override
    public String toString() {
        return "[" + this.taskType + "]" + super.toString();
    }
}
