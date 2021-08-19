/**
 * This class encapsulates a
 * deadline task with a specified deadline.
 *
 * @author Pawandeep Singh
 * @version Level-4
 *
 * */
public class Deadline extends Task{
    private String deadline;

    public Deadline(String task, String deadline) {
        super(task);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString()
                + "(by:"+ this.deadline  +")";
    }
}
