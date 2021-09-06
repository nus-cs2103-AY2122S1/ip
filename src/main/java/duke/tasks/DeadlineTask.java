package duke.tasks;
import duke.utils.DukeDate;

import java.util.Date;

public class DeadlineTask extends Task{
    String dateLiteral;
    Date dateFormatted;
    String dateReadable;


    public DeadlineTask(String description, String by) {
        super(description, TaskType.DEADLINE);
        this.dateLiteral = by;
        this.dateFormatted = DukeDate.formatDate(by);
        this.dateReadable = DukeDate.parseDateToString(dateFormatted);
    }

    public String getDate(){
        return dateLiteral;
    }

    @Override
    public String toString(){
        String typeString = type == TaskType.TODO ? "T" : type == TaskType.EVENT? "E" : "D";
        String doneSymbol = isDone? "X" : " ";
        String result = "[" + typeString + "] " + "[" + doneSymbol + "] " + name + "(by: " + dateReadable + ")";

        return result;
    }

}
