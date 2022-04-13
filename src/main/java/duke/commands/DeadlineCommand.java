package duke.commands;

import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDateTime;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.tasks.Deadline;

/**
 * Adds a deadline task to the task list.
 */
public class DeadlineCommand extends Command {
    /**
     * The command word to identity DeadlineCommand instance.
     */
    public static final String COMMAND_WORD = "deadline";

    /**
     * Length of the command word.
     */
    public static final int COMMAND_LENGTH = COMMAND_WORD.length();

    /**
     * Guide on how to use this command word.
     */
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + " <description> /by <date> - add a task to be completed by <date> in yyyy/MM/dd HHmm (24-hour format)\n"
            + "    📍 Example: " + COMMAND_WORD + " project submission /by 2021/08/30 2359";

    /**
     * Instantiates DeadlineCommand object.
     *
     * @param userCommand Full user input.
     */
    public DeadlineCommand(String userCommand) {
        super(userCommand);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            // a valid command with task description and date should give a String[] of length 2.
            // splitCommand[0] will be of format deadline <description>.
            // splitCommand[1] will be the date.
            String[] splitCommand = userCommand.split("/by");
            assert splitCommand.length > 0 : "Invalid deadline command.";

            boolean isDescAdded = splitCommand[0].strip().length() > COMMAND_LENGTH;
            boolean isOneDeadlineAdded = splitCommand.length == 2;
            boolean isMultipleDeadlineAdded = splitCommand.length > 2;

            if (isMultipleDeadlineAdded) {
                throw new IllegalArgumentException(MESSAGE_MULTIPLE_DATE);
            } else if (!isDescAdded || !isOneDeadlineAdded) {
                throw new IllegalArgumentException(MESSAGE_MISSING_DESC_DATE);
            }

            String description = splitCommand[0].substring(COMMAND_LENGTH).strip();
            String dateStr = splitCommand[1].strip();
            LocalDateTime date = LocalDateTime.parse(dateStr, Command.INPUT_FORMATTER);
            Deadline newDeadline = new Deadline(description, date);

            tasks.addTask(newDeadline);
            storage.save(tasks.getItems());

            return ui.printTaskAdded(newDeadline, tasks.getSize());
        } catch (IOException | IllegalArgumentException e) {
            return ui.printError(e.getMessage());
        } catch (DateTimeException e) {
            return ui.printError(MESSAGE_INVALID_DATE);
        }
    }
}
