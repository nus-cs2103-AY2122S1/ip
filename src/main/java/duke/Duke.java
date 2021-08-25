package duke;

import task.TaskList;

/**
 * Main Duke class
 */
public class Duke {
    private final DukeListener listener;
    private final DukeParser parser;

    /**
     * Constructor
     * Instantiates a (saved) task.Task List and a duke.DukeListener for duke.Duke
     */
    private Duke() {
        TaskList taskList = Storage.loadList();
        parser = new DukeParser(taskList);
        listener = new DukeListener(parser);
    }

    /**
     * Main function
     *
     * @param args No arguments need to be passed in
     */
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.start();
    }

    /**
     * Starts the duke.Duke system
     */
    private void start(){
        Ui.intro();
        listener.startListen();
        Ui.goodBye();
    }

}
