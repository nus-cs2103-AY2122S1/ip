package duke.classes.commands;

import static java.lang.Integer.parseInt;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import duke.classes.TaskList;
import duke.classes.exceptions.DukeException;
import duke.classes.tasks.Deadline;
import duke.classes.tasks.Event;
import duke.classes.tasks.Task;
import duke.classes.tasks.ToDo;




/**
 * Parser class handling the logic of the program
 */
public class DukeParser {
    private TaskList taskList;

    /**
     * Class Constructor
     *
     * @param taskList The tasklist to be modified through user-input
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
