package duke.command;

import duke.TaskList;
import duke.exception.DukeException;
import duke.Storage;
import duke.Ui;
import duke.exception.EmptyValueException;
import duke.exception.NoTimeException;
import duke.task.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * This class handles command that add task to list.
 */
public class AddCommand extends Command {

    private final String cmd;
    private final String category;
    public static final String ADD_EVENT = "event";
    public static final String ADD_DEADLINE = "deadline";
    public static final String ADD_TODO = "todo";

    /**
     * Constructor for AddCommand.
     *
     * @param input The line that user inputs after heading.
     * @param category The category of task to be added.
     */
    public AddCommand(String input, String category) {
        super();
        this.category = category;
        this.cmd = input;
    }

    /**
     * Method to execute command.
     *
     * @param tasks The list of tasks in the current programme.
     * @param ui The user interface.
     * @param storage Handles interaction with the file.
     * @throws DukeException All exceptions related to Duke.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String time = "";
        String[] info = cmd.split("/");
        String description = info[0].strip();
        if (description.equals("")) {
            throw new EmptyValueException();
        }
        if (!category.equals("todo")) {
            if (info.length < 2) {
                throw new NoTimeException();
            } else {
                time = info[1].strip();
            }
        }

        try {
            if ("todo".equals(category)) {
                tasks.add(new Todo(description));
            } else if ("deadline".equals(category)) {
                LocalDate ld = LocalDate.parse(time);
                tasks.add(new Deadline(description, ld));
            } else if ("event".equals(category)) {
                LocalDate ld = LocalDate.parse(time);
                tasks.add(new Event(description, ld));
            }
        } catch (DateTimeParseException e) {
            throw new DukeException("Wrong date time format! Please use yyyy-MM-dd.");
        }
    }



}