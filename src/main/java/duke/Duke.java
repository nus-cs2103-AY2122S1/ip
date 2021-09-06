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
     * @param taskFilePath The file in which the tasks are stored
     */
    public Duke(String taskFilePath, String noteFilePath) {
        ui = new Ui();

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
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        while (!Parser.parseCommand(input).equals("bye")) {
            try {
                if (Parser.parseCommand(input).equals("list")) {
                    if (TaskList.getCounter() == 0) {
                        return "\tThere are currently no tasks on your list :)";
                    } else {
                        return Parser.parseList();
                    }
                } else if (Parser.parseCommand(input).equals("notes")) {
                    if (NoteList.getCounter() == 0) {
                        return "\tThere are currently no notes on your list!";
                    } else {
                        return Parser.parseNoteList();
                    }
                } else if (Parser.parseCommand(input).equals("done")) {
                    return Parser.parseDone(input);
                } else if (Parser.parseCommand(input).equals("delete")) {
                    return Parser.parseDelete(input);
                } else if (Parser.parseCommand(input).equals("todo")) {
                    return Parser.parseTodo(input);
                } else if (Parser.parseCommand(input).equals("deadline")) {
                    return Parser.parseDeadline(input);
                } else if (Parser.parseCommand(input).equals("event")) {
                    return Parser.parseEvent(input);
                } else if (Parser.parseCommand(input).equals("today")) {
                    return Parser.parseToday();
                } else if (Parser.parseCommand(input).equals("find")) {
                    return Parser.parseFind(input);
                } else if (Parser.parseCommand(input).equals("note")) {
                    return Parser.parseNote(input);
                } else if (Parser.parseCommand(input).equals("delete note")) {
                    return Parser.parseDeleteNote(input);
                } else {
                    throw new DukeException("OOPS!!! I'm sorry, but I don't " +
                            "know what that means :-(");
                }
            } catch (DukeException | IOException e) {
                //System.err.println(e);
                return "" + e;
            }
        }
        return "Bye! Hope to see you soon :)";
    }
}


