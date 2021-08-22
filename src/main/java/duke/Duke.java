/**
 * Main Duke class
 */
public class Duke {
    private final DukeListener listener;
    private final TaskList taskList;

    /**
     * Constructor
     * Instantiates a (saved) Task List and a DukeListener for Duke
     */
    private Duke() {
        taskList = Storage.loadList();
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
