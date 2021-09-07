package duke.command;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;
import duke.exception.EmptyValueException;
import duke.exception.NoTimeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

/**
 * This class handles command that add task to list.
 */
public class AddCommand extends Command {

    public static final String ADD_EVENT = "event";
    public static final String ADD_DEADLINE = "deadline";
    public static final String ADD_TODO = "todo";
    private final String cmd;
    private final String category;


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
     * @return Response message of adding a task.
     * @throws DukeException All exceptions related to Duke.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String time = "";
        String[] info = cmd.split("/");
        String description = info[0].strip();
        String result = "";
        if (description.equals("")) {
            throw new EmptyValueException();
        }
        if (!category.equals("todo")) {
            assert category.equals("deadline")
                    ||category.equals("event")
                    : "category should be one of the other two";
            if (info.length < 2) {
                throw new NoTimeException();
            } else {
                time = info[1].strip();
            }
        }

        try {
            switch (category) {
            case "todo":
                result = tasks.add(new Todo(description));
                break;
            case "deadline": {
                LocalDate ld = LocalDate.parse(time);
                result = tasks.add(new Deadline(description, ld));
                break;
            }
            case "event": {
                LocalDate ld = LocalDate.parse(time);
                result = tasks.add(new Event(description, ld));
                break;
            }
            }
        } catch (DateTimeParseException e) {
            throw new DukeException("Wrong date time format! Please use yyyy-MM-dd.");
        }

        return result;
    }
}
