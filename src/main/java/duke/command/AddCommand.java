package duke.command;

import duke.TaskList;
import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.task.*;

import java.time.LocalDate;

/**
 * This class handles command that add task to list.
 */
public class AddCommand extends Command {

    private String description;
    private String time;
    private String category;

    /**
     * Constructor for AddCommand.
     *
     * @param input The line that user inputs after heading.
     * @param category The category of task to be added.
     */
    public AddCommand(String input, String category) {
        super();
        this.category = category;
        String[] info = input.split("/");
        this.description = info[0].strip();
        if (!category.equals("todo")) {
            this.time = info[1].strip();
        }
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
        ui.showLine();
        if ("todo".equals(category)) {
            tasks.add(new Todo(description));
        } else if ("deadline".equals(category)) {
            LocalDate ld = LocalDate.parse(time);
            tasks.add(new Deadline(description, ld));
        } else if ("event".equals(category)) {
            LocalDate ld = LocalDate.parse(time);
            tasks.add(new Event(description, ld));
        } else {
            throw new DukeException("Wrong format of info");
        }
        ui.showLine();
    }



}