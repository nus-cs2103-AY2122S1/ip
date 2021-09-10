package duke.command;

import java.time.LocalDate;

import duke.DukeResponse;
import duke.Parser;
import duke.Storable;
import duke.TaskList;
import duke.Ui;
import duke.Ui.Descriptors;
import duke.Ui.UserCommands;
import duke.exception.DukeException;
import duke.exception.InvalidUserCommandException;
import duke.exception.MissingTaskDescriptionException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Period;
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
     * @throws MissingTaskDescriptionException If description is missing for task to be added.
     * @throws DukeException If underlying methods or checks fail.
     */
    private String addTask(TaskList tasks, Ui ui, char separator) throws DukeException {
        // Checks for command given in user input.
        UserCommands userCommand;
        if (this.userInput.startsWith(UserCommands.TODO.getCommand())) {
            userCommand = UserCommands.TODO;
        } else if (this.userInput.startsWith(UserCommands.DEADLINE.getCommand())) {
            userCommand = UserCommands.DEADLINE;
        } else if (this.userInput.startsWith(UserCommands.EVENT.getCommand())) {
            userCommand = UserCommands.EVENT;
        } else if (this.userInput.startsWith(UserCommands.PERIOD.getCommand())) {
            userCommand = UserCommands.PERIOD;
        } else {
            throw new InvalidUserCommandException(this.userInput);
        }

        // Preliminary check for any input following command.
        Parser.checkInputValidity(this.userInput, userCommand,
                new MissingTaskDescriptionException(userCommand));

        String userCommandString = userCommand.getCommand();

        // Extracts task description.
        String description = this.userInput.substring(userCommandString.length() + 1);

        // Check empty/whitespace task description.
        if (description.isBlank()) {
            throw new MissingTaskDescriptionException(userCommand);
        }

        // Parses description and adds the corresponding task to tasks.
        if (userCommandString.equals(UserCommands.TODO.getCommand())) {
            tasks.add(new Todo(description));
        } else if (userCommandString.equals(UserCommands.DEADLINE.getCommand())) {
            addDeadline(tasks, separator, description);
        } else if (userCommandString.equals(UserCommands.EVENT.getCommand())) {
            addEvent(tasks, separator, description);
        } else if (userCommandString.equals(UserCommands.PERIOD.getCommand())) {
            addPeriod(tasks, separator, description);
        }

        return ui.getAddSuccessMessage(tasks.get(tasks.size() - 1), tasks.size());
    }

    private void addPeriod(TaskList tasks, char separator, String description) throws DukeException {
        // Parses description into task description and time period.
        String[] descriptions =
                Parser.parseUserDescriptionInput(description, Descriptors.WITHIN, separator, UserCommands.PERIOD);

        LocalDate[] startEndDates = Parser.periodToLocalDate(descriptions[1]);
        LocalDate startDate = startEndDates[0];
        LocalDate endDate = startEndDates[1];

        tasks.add(new Period(descriptions[0], startDate, endDate));
    }

    private void addEvent(TaskList tasks, char separator, String description) throws DukeException {
        // Parses description into task description and time.
        String[] descriptions =
                Parser.parseUserDescriptionInput(description, Descriptors.AT, separator, UserCommands.EVENT);

        LocalDate localDate = Parser.dateToLocalDate(descriptions[1]);

        tasks.add(new Event(descriptions[0], localDate));
    }

    private void addDeadline(TaskList tasks, char separator, String description) throws DukeException {
        // Parses description into task description and time.
        String[] descriptions =
                Parser.parseUserDescriptionInput(description, Descriptors.BY, separator, UserCommands.DEADLINE);

        LocalDate localDate = Parser.dateToLocalDate(descriptions[1]);

        tasks.add(new Deadline(descriptions[0], localDate));
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
     * @return DukeResponse containing either string describing task added and new total count of task
     *         or error message.
     */
    @Override
    public DukeResponse execute(TaskList tasks, Ui ui, Storable storage) {
        try {
            String output = this.addTask(tasks, ui, '/');
            storage.saveTasksToData(tasks);
            return new DukeResponse(output, false);
        } catch (DukeException dukeException) {
            return new DukeResponse(dukeException.toString(), true);
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
