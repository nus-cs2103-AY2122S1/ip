package duke.command;

import java.time.LocalDate;

import duke.DukeException;
import duke.Parser;
import duke.Storable;
import duke.TaskList;
import duke.Ui;
import duke.Ui.Commands;
import duke.Ui.Descriptors;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

/**
 * Represents a command that can be executed to add a task based on user input,
 * print task added and new total count of tasks, then save tasks to storage.
 */
public class AddCommand extends Command {
    private final String userInput;

    /**
     * Constructor for AddCommand.
     * Creates an AddCommand containing user input.
     *
     * @param userInput User's input into Duke chatbot.
     */
    public AddCommand(String userInput) {
        this.userInput = userInput;
    }

    /**
     * Adds task based on user input into tasks,
     * then returns String describing task added and new total count of tasks.
     * Event is added for user inputs of the form "event task-descr /at dd/mm/yyyy".
     * Deadline is added for user inputs of the form "deadline task-descr /by dd/mm/yyyy".
     * Todo is added for user inputs of the form "todo task-descr".
     *
     * @param tasks TaskList to add task to.
     * @param ui Ui to get enums, response messages and exception messages from.
     * @param separator char separator used to separate task description and time in Event and Deadline.
     * @return String describing task added and new total count of tasks.
     * @throws DukeException If user command is invalid.
     * @throws DukeException If user input does not provide task description.
     * @throws DukeException If user input has missing spaces.
     * @throws DukeException If user input for time is in invalid date format.
     * @throws DukeException If user input does not contain descriptors by or at for Deadline and Event respectively.
     * @throws DukeException If user input is missing time input for Deadline and Event.
     */
    private String addTask(TaskList tasks, Ui ui, char separator) throws DukeException {
        // Checks for command given in user input.
        String userCommand;
        if (this.userInput.startsWith(Commands.TODO.getCommand())) {
            userCommand = Commands.TODO.getCommand();
        } else if (this.userInput.startsWith(Commands.DEADLINE.getCommand())) {
            userCommand = Commands.DEADLINE.getCommand();
        } else if (this.userInput.startsWith(Commands.EVENT.getCommand())) {
            userCommand = Commands.EVENT.getCommand();
        } else {
            throw new DukeException(Ui.exceptionInvalidUserCommand());
        }

        // Preliminary check for any input following command.
        Parser.checkInputValidity(this.userInput, userCommand,
                Ui.exceptionMissingTaskDescription(userCommand));

        // Extracts task description.
        String description = this.userInput.substring(userCommand.length() + 1);

        // Parses description and adds the corresponding task to tasks.
        if (userCommand.equals(Commands.TODO.getCommand())) {
            // Adds to-do task to tasks.
            tasks.add(new Todo(description));
        } else if (userCommand.equals(Commands.DEADLINE.getCommand())) {
            // Parses description into task description and time.
            String[] descriptions =
                    Parser.parseUserDescriptionInput(description, Descriptors.BY, separator, Commands.DEADLINE);

            // Convert time to LocalDate.
            LocalDate localDate = Parser.toLocalDate(descriptions[1]);

            // Adds duke.task.Deadline task to tasks.
            tasks.add(new Deadline(descriptions[0], localDate));
        } else {
            // Parses description into task description and time.
            String[] descriptions =
                    Parser.parseUserDescriptionInput(description, Descriptors.AT, separator, Commands.EVENT);

            // Convert time to LocalDate.
            LocalDate localDate = Parser.toLocalDate(descriptions[1]);

            // Adds duke.task.Event task to tasks.
            tasks.add(new Event(descriptions[0], localDate));
        }

        // Returns response to user after successfully adding task to tasks.
        return ui.getAddSuccess(tasks.get(tasks.size() - 1), tasks.size());
    }

    /**
     * Adds task based on user input to tasks, saves tasks to storage,
     * then returns String describing task added and new total count of task.
     * Event is added for user inputs of the form "event task-descr /at dd/mm/yyyy".
     * Deadline is added for user inputs of the form "deadline task-descr /by dd/mm/yyyy".
     * Todo is added for user inputs of the form "todo task-descr".
     *
     * @param tasks TaskList that command executes upon.
     * @param ui Ui contains enums, response messages and exception messages that command execution will use.
     * @param storage Storage that command executes upon.
     * @return String describing task added and new total count of task.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storable storage) {
        try {
            // Add task according to user specifications.
            String output = this.addTask(tasks, ui, '/');

            // Saves edited duke.TaskList to save file.
            storage.saveTasksToData(tasks);

            return output;
        } catch (DukeException dukeException) {
            return dukeException.toString();
        }
    }

    /**
     * Indicates whether another object is equals to this AddCommand.
     *
     * @param obj Other object to be compared to.
     * @return A boolean indicating whether the other object is equal to this AddCommand.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof AddCommand) {
            AddCommand other = (AddCommand) obj;
            return this.userInput.equals(other.userInput);
        }
        return false;
    }
}
