package duke.tasks;
import duke.utils.DukeDate;

import java.util.Date;

public class EventTask extends Task {
    String dateLiteral;
    Date dateFormatted;
    String dateReadable;

    public EventTask(String description, String at) {
        super(description, TaskType.EVENT);
        this.dateLiteral = at;
        this.dateFormatted = DukeDate.formatDate(at);
        this.dateReadable = DukeDate.parseDateToString(dateFormatted);
    }

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
