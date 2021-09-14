package duke.tasks;
import duke.utils.DukeDate;

import java.util.Date;

/**
 * Class for event tasks that contain a
 * date or datetime
 */
public class EventTask extends Task {
    private String dateLiteral;
    Date dateFormatted;
    String dateReadable;

    /**
     * Constructor that initializes an EventTask object
     * @param description description of event
     * @param at date of event
     */
    public EventTask(String description, String at) {
        super(description, TaskType.EVENT);
        assert (!at.equals(""));
        this.dateLiteral = at;
        this.dateFormatted = DukeDate.formatDate(at);
        this.dateReadable = DukeDate.parseDateToString(dateFormatted);
    }

    /**
     * Gives the date associated to this task, in the same format
     * as it was keyed in by the user in the CLI.
     *
     * @return String date
     */
    public String getDate(){
        return dateLiteral;
    }

    @Override
    public String toString(){
        String typeString = type == TaskType.TODO ? "T" : type == TaskType.EVENT? "E" : "D";
        String doneSymbol = isDone? "X" : " ";
        String result = "[" + typeString + "] " + "[" + doneSymbol + "] " + name + "(at: " + dateReadable + ")";

        return result;
    }
}
