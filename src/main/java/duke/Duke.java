package duke;

import duke.gui.Main;
import javafx.application.Application;

/**
 * Represents Duke, an interactive personal assistant bot that can keep track of tasks via text commands.
 *
 * @author Hanif Kamal
 */
public class Duke {
    private static final String FILEPATH = "./data/tasks.txt";
    private static final String NOTE_FILEPATH = "./data/notes.txt";
    private final Storage storage;
    private final TaskList list;
    private final NoteList noteList;

    /**
     * Class constructor to initialize a Duke instance.
     */
    public Duke() {
        this.storage = new Storage(FILEPATH, NOTE_FILEPATH);
        this.list = new TaskList();
        this.noteList = new NoteList();
        try {
            storage.readTasks(list);
            storage.readNotes(noteList);
        } catch (Exception e) {
            System.out.println("Could not read the following data file: " + e.getMessage());
        }
    }

    /**
     * Starts up Duke to get ready for chatting and task-tracking.
     *
     * @param input The String input by the user.
     * @return Duke's response to the user as a String.
     */
    public String getResponse(String input) {
        if (input.equals("bye")) {
            System.exit(0);
        }
        Parser parser = new Parser(list, noteList);
        try {
            String response = parser.parse(input);
            storage.writeTasks(list);
            storage.writeNotes(noteList);
            return response;
        } catch (DukeException e) {
            return (e.getMessage());
        }
    }

    /**
     * Main method to begin Duke.
     *
     * @param args Not used.
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
