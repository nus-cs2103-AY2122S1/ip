/**
 *
 * @author Pawandeep Singh
 * @version Level-3
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
                + " (by: "+ this.deadline  +")";
    }
}
