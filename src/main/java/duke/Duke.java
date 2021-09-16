package duke;

import duke.exceptions.DukeException;

/**
 * Dukewu, the modified version of Duke (Personal Assistant Chatbot).
 * Speaks owo language.
 * @author Ruth Poh
 */
public class Duke {
    private Storage storage;
    private TaskList tasklist;
    private Ui ui;
    private boolean shouldContinue;

    /**
     * Initialises Dukewu.
     * @param filePath File path to save data to
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        shouldContinue = true;
        try {
            tasklist = storage.loadData();
            System.out.println(Ui.getLoadingSuccessfulMessage());
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            // creates new tasklist if one cannot be found.
            tasklist = new TaskList();

        }
    }

    /**
     * Sends response to Ui and gets a response.
     * @param input Command input sent to Ui
     * @return Message from Ui
     */
    public String getResponse(String input){
        if (input.equalsIgnoreCase("bye")) {
            shouldContinue = false;
        }
        return ui.getMessage(this.storage, this.tasklist, input);
    }

    /**
     * Checks if Duke should continue receiving input from interface.
     * @return False if input is not "bye", true otherwise
     */
    public boolean isExit() {
        return !shouldContinue;
    }

}
