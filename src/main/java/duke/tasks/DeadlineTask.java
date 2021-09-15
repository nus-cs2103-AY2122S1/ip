package duke.tasks;

import java.util.Date;

import duke.utils.DukeDate;

/**
 * Class for deadline tasks that contain a
 * date or datetime
 */
public class DeadlineTask extends Task {
    private String dateLiteral;
    private Date dateFormatted;
    private String dateReadable;

    /**
     * Constructor that initializes an EventTask object
     * @param description description of event
     * @param by date of deadline
     */
    public DeadlineTask(String description, String by) {
        super(description, TaskType.DEADLINE);
        this.dateLiteral = by;
        this.dateFormatted = DukeDate.formatDate(by);
        this.dateReadable = DukeDate.parseDateToString(dateFormatted);
    }

    /**
     * Gives the date associated to this task, in the same format
     * as it was keyed in by the user in the CLI.
     *
     * @return String date
     */
    public String getDate() {
        return dateLiteral;
    }

    @Override
    public String toString() {
        String typeString = type == TaskType.TODO ? "T" : type == TaskType.EVENT ? "E" : "D";
        String doneSymbol = isDone ? "X" : " ";
        String result = "[" + typeString + "] " + "[" + doneSymbol + "] " + name + "(by: " + dateReadable + ")";

        return result;
    }

}
