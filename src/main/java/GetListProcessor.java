public class GetListProcessor extends Processor {
    /**
     * Constructor of the class `GetListProcessor`.
     */
    public GetListProcessor() {
        super("list");
        this.message = "";
    }

    /**
     * Generates the message to be printed.
     */
    @Override
    public void process() {
        String[] tasks = Duke.getTasks();
        int i = 0;
        while (i < 100) {
            if (tasks[i] == null) {
                break;
            }
            this.message += String.format("%o. %s\n", i + 1, tasks[i]);
            i++;
        }
    }
}
