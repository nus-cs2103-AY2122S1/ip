package duke;

import java.lang.reflect.Array;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Modified version of Duke (Personal Assistant Chatbot). Speaks owo language.
 * @author Ruth Poh
 */
public class Duke {
    private Storage storage;
    private TaskList tasklist;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasklist = storage.loadData();
            System.out.println("Loadiwng file for you. . . Loaded!\n");
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            tasklist = new TaskList(); // creates new tasklist if one cannot be found.
        }
    }

    public void run() throws DukeException {
        Ui.run(this.storage, this.tasklist);
    }

    public static void main(String[] args) throws DukeException {
        new Duke("data/tasks.txt").run();
    }

}