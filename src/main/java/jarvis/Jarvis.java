package jarvis;

import java.io.IOException;
import java.io.FileNotFoundException;

/**
 * Class which operates Jarvis the chat-bot
 */
public class Jarvis {
    private Storage taskStorage;
    private Storage notesStorage;
    private TaskList tasks;
    private NoteList notes;

    /**
     * Retrieves all the tasks stored by Jarvis in the hard disk upon running the main method.
     *
     * @param taskFilePath The file in which the tasks are stored
     * @param noteFilePath The file in which the notes are stored
     */
    public Jarvis(String taskFilePath, String noteFilePath) {
        taskStorage = new Storage(taskFilePath); // To load user tasks
        notesStorage = new Storage(noteFilePath); // To load user notes as well

        tasks = new TaskList();
        notes = new NoteList();

        try {
            taskStorage.retrieveTaskFileContents(); // Retrieves contents from the jarvis.txt file in user's hard disk
            notesStorage.retrieveNotesFileContents(); // Retrieves contents from the notes.txt file in user's hard disk
        } catch (FileNotFoundException | JarvisException e) {
            System.err.println(e);
        }
    }

    /**
     * Returns the response to be given by Jarvis to the user upon receiving a command.
     *
     * @param input the command given by the user
     */
    public static String getResponse(String input) {
        try {
            switch (Parser.parseCommand(input)) {
            case "list":
                return Ui.listTasks();
            case "notes":
                return Ui.listNotes();
            case "done":
                return Parser.parseDone(input);
            case "delete note":
                return Parser.parseDeleteNote(input);
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
            case "bye":
                return Ui.BYE;
            default:
                throw new JarvisException(Ui.UNRECOGNISED_COMMAND);
            }
        } catch (JarvisException | IOException e) {
            return "" + e;
        }
    }
}


