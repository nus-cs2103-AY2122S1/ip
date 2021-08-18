/**
 * Main Duke class
 */
public class Duke {
    final DukeListener listener;
    final TaskList taskList;

    /**
     * Constructor
     * Instantiates a Task List and a DukeListener for Duke
     */
    private Duke() {
        taskList = new TaskList();
        listener = new DukeListener(taskList);
    }

    /**
     * Starts the Duke system
     */
    private void start(){
        Ui.intro();
        listener.startListen();
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.start();
    }
}
