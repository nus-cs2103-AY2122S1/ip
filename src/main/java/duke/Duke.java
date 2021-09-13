package duke;
import java.io.IOException;
import java.io.FileNotFoundException;

/**
 * Class which operates Jarvis the chat-bot
 */
public class Duke {
    private Storage taskStorage;
    private Storage notesStorage;
    private TaskList tasks;
    private NoteList notes;
    private Ui ui;

    /**
     * Retrieves all the tasks stored by Jarvis in the hard disk upon running the main method.
     *
     * @param taskFilePath The file in which the tasks are stored
     * @param noteFilePath The file in which the notes are stored
     */
    public Duke(String taskFilePath, String noteFilePath) {
        //ui = new Ui();
        taskStorage = new Storage(taskFilePath); //To load user tasks
        notesStorage = new Storage(noteFilePath); //To load user notes as well

        tasks = new TaskList();
        notes = new NoteList();

        try {
            taskStorage.retrieveFileContents();
            notesStorage.retrieveFileContents();
        } catch (FileNotFoundException e) {
            System.err.println(e);
        }
    }

    /**
     * Returns the response to be given by Jarvis to the user upon receiving a command
     *
     * @param input the command given by the user
     */
    public static String getResponse(String input) {
        try {
            switch (Parser.parseCommand(input)) {
            case "list":
                return Ui.listTasks();
            case "notes":
                // If there are no tasks in the task list
                if (NoteList.getCounter() == 0) {
                    return "\t" + Ui.NO_NOTES;
                } else {
                    return Parser.parseNoteList();
                }
            case "done":
                return Parser.parseDone(input);
            case "delete":
                return Parser.parseDelete(input);
            case "todo":
                return Parser.parseTodo(input);
            case "deadline":
                return Parser.parseDeadline(input);
            case "event":
                return Parser.parseEvent(input);
            case "today":
                return Ui.listTodayTasks();
            case "find":
                return Ui.findTasks(input);
            case "note":
                return Parser.parseNote(input);
            case "delete note":
                return Parser.parseDeleteNote(input);
            case "bye":
                return Ui.BYE;
            default:
                throw new DukeException(Ui.UNRECOGNISED_COMMAND);
            }
        } catch (DukeException | IOException e) {
            return "" + e;
        }
    }
}


