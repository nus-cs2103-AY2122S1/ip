import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * This class is a subclass of task.
 *
 * @author Deng Huaiyu(G12)
 * @version CS2103T AY21/22 Semester 1
 */
public class Deadline extends Task{
    protected LocalDateTime ddl;
    protected LocalDate date;
    private int whichOne;


    /**
     * The construction method for a deadline.
     *
     * @param description detail of a deadline
     * @param ddl time of a deadline
     */
    public Deadline(String description, LocalDateTime ddl) {
        super(description);
        this.ddl = ddl;
        this.whichOne = 0;
    }

    /**
     * The construction method for a deadline.
     *
     * @param description detail of a deadline
     * @param date time of a deadline
     */
    public Deadline(String description, LocalDate date) {
        super(description);
        this.date = date;
        this.whichOne = 1;
    }

    /**
     * Returns date of a deadline
     *
     * @return LocalDate of the deadline task
     */
    @Override
    public LocalDate getDate() {
        LocalDate result;
        if (whichOne == 0) {
            result = ddl.toLocalDate();
        } else {
            result = date;
        }
        return result;
    }

    /**
     * The toString method to output a deadline.
     *
     * @return return the string form of the deadline
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + (this.whichOne == 0 ? Task.toTimeFormat(ddl) : Task.toDateFormat(date)) + ")";
    }

    /**
     * Gets type of the deadline event
     */
    @Override
    public String getType() {
        return "D";
    }

    /**
     * Gets time of the deadline event
     */
    @Override
    public String getTime() {
        return (this.whichOne == 0 ? ddl.toLocalDate()+" "+ddl.toLocalTime() : date.toString());
    }
}
