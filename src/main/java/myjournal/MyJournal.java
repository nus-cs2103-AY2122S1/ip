package myjournal;

import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Creates chatBot called MyJournal.
 *
 * @author Felissa Faustine.
 */
public class MyJournal {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    /**
     * Constructs the object MyJournal.
     */
    public MyJournal() {
        ui = new Ui();
        parser = new Parser();
        storage = new Storage("./tasks.txt");
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            tasks = new TaskList();
        }
    }

    /**
     * Parses user's input.
     *
     * @param input The user's input.
     * @return The parsed input.
     */
    public String getResponse(String input) {
        Scanner text = new Scanner(input);
        String parsedInput = parser.parse(text, tasks, ui);
        storage.saveFile(tasks.toString());
        return parsedInput;
    }
}
