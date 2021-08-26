package duke.parser;

import duke.command.Add;
import duke.command.Delete;
import duke.command.Done;
import duke.command.DukeCommand;
import duke.command.Exit;
import duke.command.Find;
import duke.command.List;
import duke.data.DukeException;
import duke.data.NoToDoDescriptionException;
import duke.data.UnknownCommandException;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.TaskList;
import duke.task.ToDo;
import duke.ui.Ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Encapsulates the processing of user inputs to duke.
 *
 * @author Zhi Bin
 * @version Duke Level 9
 */
public class Parser {
    private final TaskList list;
    private final Storage storage;
    private final Ui ui;

    /**
     * Creates a Parser that handles user input and turn it into respective
     * commands and execute the required functions that user demands.
     *
     * @param list    The TaskList handler that is handling the list of task.
     * @param storage The Storage handler that is in-charged of saving and loading files on local directory.
     * @param ui      The Ui handler that handles printing of output, if required.
     */
    public Parser(TaskList list, Storage storage, Ui ui) {
        this.list = list;
        this.storage = storage;
        this.ui = ui;
    }

    /**
     * Returns the respective DukeCommand that user want duke to do.
     * If the command is not supported
     *
     * @param userInput User's input on what they want duke to do.
     * @return The respective command object.
     * @throws DukeException If some error occurred in the processing of user input,
     *                       like the user inputted some command that duke does not support.
     */
    public DukeCommand processInput(String userInput) throws DukeException {
        String commandType = userInput.split(" ")[0];
        switch (commandType) {
        case "list":
            return new List(ui, storage, list);

        case "bye":
            return new Exit(ui, storage, list);

        case "done":
            return new Done(ui, storage, list, Integer.parseInt(userInput.split(" ")[1]));

        case "todo":
            int spaceIndex = userInput.indexOf(" ");
            String toDoDescription = userInput.substring(spaceIndex + 1);
            if (toDoDescription.isBlank() || spaceIndex == -1) {
                throw new NoToDoDescriptionException();
            }
            return new Add(ui, storage, list, new ToDo(toDoDescription, false));

        case "deadline":
            String deadlineDescription = userInput.substring(userInput.indexOf(" ") + 1, userInput.indexOf("/by") - 1);
            try {
                LocalDateTime by = LocalDateTime.parse(userInput.substring(userInput.indexOf("by") + 3),
                        DateTimeFormatter.ofPattern("yyyy-M-d H:m"));
                return new Add(ui, storage, list, new Deadline(deadlineDescription, false, by));
            } catch (DateTimeParseException e) {
                System.out.println("Please enter Date and Time in YYYY-MM-DD HH:MM.");
            }

        case "event":
            String eventDescription = userInput.substring(userInput.indexOf(" ") + 1, userInput.indexOf("/at") - 1);
            try {
                LocalDateTime at = LocalDateTime.parse(userInput.substring(userInput.indexOf("at") + 3),
                        DateTimeFormatter.ofPattern("yyyy-M-d H:m"));
                return new Add(ui, storage, list, new Event(eventDescription, false, at));
            } catch (DateTimeParseException e) {
                System.out.println("Please enter Date and Time in YYYY-MM-DD HH:MM.");
            }

        case "delete":
            return new Delete(ui, storage, list, Integer.parseInt(userInput.split(" ")[1]));

        case "find":
            return new Find(ui, storage, list, userInput.substring(userInput.indexOf(" ") + 1));

        default:
            throw new UnknownCommandException();
        }
    }
}
