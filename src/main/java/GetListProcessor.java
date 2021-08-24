import java.util.ArrayList;

/**
 * Represents a processor that can retrieve the task list. A subclass of the Processor class.
 */
public class GetListProcessor extends Processor {
    /**
     * Constructor of the class `GetListProcessor`.
     */
    public GetListProcessor(Duke duke) {
        super("list", duke);
        this.message = "Here are the tasks in your list:\n";
    }

    /**
     * Generates the message to be printed.
     *
     * @return Whether the program is still running.
     */
    @Override
    public boolean process() {
        ArrayList<Task> tasks = this.duke.getTasks();
        int len = this.duke.getNumOfTasks();
        for (int i = 0; i < len; i++) {
            this.message += String.format("%o.%s\n", i + 1, tasks.get(i).toString());
        }
        return true;
    }
}
