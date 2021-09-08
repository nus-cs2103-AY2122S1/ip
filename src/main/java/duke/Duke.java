package duke;

import duke.exceptions.DukeException;

/**
 * Modified version of Duke (Personal Assistant Chatbot). Speaks owo language.
 * @author Ruth Poh
 */
public class Duke {
    private Storage storage;
    private TaskList tasklist;
    private Ui ui;
    private boolean continueDuke;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        continueDuke = true;
        try {
            tasklist = storage.loadData();
            System.out.println("Loadiwng file for you. . . Loaded!\n");
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            tasklist = new TaskList(); // creates new tasklist if one cannot be found.
        }
    }

    public String getResponse(String input){
        if (input.equalsIgnoreCase("bye")) {
            continueDuke = false;
        }
        return ui.getMessage(this.storage, this.tasklist, input);
    }

    public boolean isExit() {
        return !continueDuke;
    }

//    public void run() throws DukeException {
//    }
//
//    public static void main(String[] args) throws DukeException {
//    }


}