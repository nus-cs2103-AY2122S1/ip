import java.util.ArrayList;

public class GetListProcessor extends Processor {
    /**
     * Constructor of the class `GetListProcessor`.
     */
    public GetListProcessor() {
        super("list");
        this.message = "Here are the tasks in your list:\n";
    }

    /**
     * Generates the message to be printed.
     */
    @Override
    public void process() {
        ArrayList<Task> tasks = Duke.getTasks();
        int len = Duke.getNumOfTasks();
        for (int i = 0; i < len; i++) {
            this.message += String.format("%o.%s\n", i + 1, tasks.get(i).toString());
        }
    }
}
