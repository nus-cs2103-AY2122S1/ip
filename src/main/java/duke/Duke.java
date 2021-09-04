package duke;

import java.io.FileNotFoundException;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * creates a Duke object
     *
     * @param filePath takes a String representing the directory
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.readFile(filePath));
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Gets a String representing the response
     *
     * @param input a String representing user command
     * @return a String handling user command
     */
    public String getResponse(String input) {
        Command c = new Command(input);
        String s = c.execute(tasks, ui, storage);
        return s;
    }
}
