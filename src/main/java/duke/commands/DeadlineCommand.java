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
     * Guide on how to use this command word.
     */
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + " <description> /by <date> - add a task to be completed by <date> in yyyy/MM/dd HHmm (24-hour format)\n"
            + "    📍 Example: " + COMMAND_WORD + " project submission /by 2021/08/30 2359";

    private String userCommand;

    /**
     * Instantiates DeadlineCommand object.
     *
     * @param userCommand Full user input.
     */
    public DeadlineCommand(String userCommand) {
        super();
        this.userCommand = userCommand;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            int byIndex = userCommand.indexOf("/by");
            if (userCommand.length() < 9 || byIndex <= 9 || userCommand.length() <= byIndex + 4) {
                throw new IllegalArgumentException("Please add a description and/or deadline!");
            } else {
                String by = userCommand.substring(byIndex + 4);
                LocalDateTime date = LocalDateTime.parse(by, Command.INPUT_FORMATTER);
                Deadline newDeadline = new Deadline(userCommand.substring(9, byIndex - 1), date);

                tasks.addTask(newDeadline);
                storage.save(tasks.getItems());

                return ui.printTaskAdded(newDeadline, tasks.getSize());

            }

        } catch (IOException | IllegalArgumentException e) {
            return ui.printError(e.getMessage());
        } catch (DateTimeException e) {
            return ui.printError("Please add a valid event date of format yyyy/MM/dd HHmm (24-hour format)!");
        }

    }
}
