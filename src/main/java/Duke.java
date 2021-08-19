/**
 * Represents a chatbot to manage a list of tasks.
 *
 * @author Adam Ho
 */
public class Duke {
    protected TaskManager taskManager = new TaskManager();

    /**
     * Runs the TaskManager object.
     */
    public void run() {
        taskManager.run();
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
