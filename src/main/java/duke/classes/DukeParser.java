package duke.classes;

import duke.classes.commands.DeadlineCommand;
import duke.classes.commands.DeleteCommand;
import duke.classes.commands.DoneCommand;
import duke.classes.commands.EventCommand;
import duke.classes.commands.ExitCommand;
import duke.classes.commands.FindCommand;
import duke.classes.commands.HelpCommand;
import duke.classes.commands.ListCommand;
import duke.classes.commands.TodoCommand;
import duke.classes.exceptions.DukeException;

/**
 * Parser class handling the logic of the program
 */
public class DukeParser {
    private final TaskList taskList;

    /**
     * Class Constructor
     *
     * @param taskList The task list to be modified through user-input
     */
    public DukeParser(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Main Parser function. Contains the logic to read and resolve user input,
     * then directs Duke to the appropriate response in the backend and frontend
     *
     * @param str Input entered by the user
     * @return Boolean to indicate the closing of the programme
     *         as a direct result of the "bye" command (True if hasQuit)
     * @throws DukeException Exception thrown when any issues are encountered
     */
    public String parse(String str) throws DukeException {
        assert str != null : "empty string";
        String[] words = str.split(" ");
        String keyword = words[0].toLowerCase();

        switch(keyword) {
        case "bye":
            return new ExitCommand().execute();
        case "list":
            return new ListCommand(taskList).execute();
        case "help":
            return new HelpCommand().execute();
        case "done":
            return new DoneCommand(taskList, words).execute();
        case "delete":
            return new DeleteCommand(taskList, words).execute();
        case "find":
            return new FindCommand(words).execute();
        case "todo":
            return new TodoCommand(taskList, str).execute();
        case "deadline":
            return new DeadlineCommand(taskList, words).execute();
        case "event":
            return new EventCommand(taskList, words).execute();
        default:
            throw new DukeException("!!! I'm sorry, but I don't know what " + keyword + " means. !!!");
        }
    }
}
