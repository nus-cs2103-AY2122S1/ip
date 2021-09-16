import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected String dateNum;
    protected LocalDate date;

    /**
     * Constructor for deadline class
     * @param description name of the task
     * @param date date of deadline
     */
    public Deadline(String description, String date) {
        super(description);
        this.dateNum = date;
            this.date = LocalDate.parse(date);
    }

    /**
     * Method to check if it is a deadline task
     * @return string representing deadline task
     */
    @Override
    public String getTask() {
        return "D"; // mark done task with X
    }

    /**
     * Method to check the date
     * @return string format of date
     */
    public String getDate() {
        return "(by: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    /**
     * Method to check numerical input of the date
     * @return string format of numerical of the input date
     */
    public String getDateNum() {
        return dateNum;
    }
}
