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
        Task[] tasks = Duke.getTasks();
        int i = 0;
        while (i < 100) {
            if (tasks[i] == null) {
                break;
            }
            this.message += String.format("%o.%s\n", i + 1, tasks[i].toString());
            i++;
        }
    }
}
