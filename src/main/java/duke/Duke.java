package duke;

import task.TaskList;

/**
 * Main duke.Duke class
 */
public class Duke {
    private final DukeListener listener;
    private final TaskList taskList;

    /**
     * Constructor
     * Instantiates a (saved) task.Task List and a duke.DukeListener for duke.Duke
     */
    private Duke() {
        taskList = Storage.loadList();
        listener = new DukeListener(taskList);
    }

    /**
     * Starts the duke.Duke system
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
