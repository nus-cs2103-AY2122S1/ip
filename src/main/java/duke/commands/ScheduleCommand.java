package duke.commands;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ScheduleCommand extends Command {
    /** The command word that identifies a ScheduleCommand instance. */
    public static final String COMMAND_WORD = "schedule";

    /** Length of the command word. */
    public static final int COMMAND_LENGTH = COMMAND_WORD.length();

    /** Guide on how to use this command. */
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + " <date> - finds a list of tasks at scheduled at the <date> in yyyy/MM/dd format.\n"
            + "    📍 Example: " + COMMAND_WORD + " 2021/08/24";

    /**
     * Instantiates a ScheduleCommand object.
     *
     * @param userCommand Full user input.
     */
    public ScheduleCommand(String userCommand) {
        super(userCommand);
    }

    /**
     * Returns true if the command is an exit command, return false otherwise.
     *
     * @return True if the command is an exit command, return false otherwise.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the respective command given and displays the result or error message(s).
     *
     * @param tasks TaskList that stores the list of tasks.
     * @param ui Ui instance that prints various messages.
     * @param storage Storage instance that reads and writes the task list.
     * @return Message to show whether successful execution of the command or error.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            if (userCommand.length() <= COMMAND_LENGTH) {
                throw new IllegalArgumentException(MESSAGE_INVALID_DATE);
            }

            String dateStr = userCommand.substring(COMMAND_LENGTH).strip();
            LocalDate date = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("yyyy/MM/dd"));

            return tasks.viewSchedule(date);

        } catch (IllegalArgumentException e) {
            return ui.printError(e.getMessage());
        } catch (DateTimeException e) {
            return ui.printError(MESSAGE_INVALID_DATE);
        }
    }
}
