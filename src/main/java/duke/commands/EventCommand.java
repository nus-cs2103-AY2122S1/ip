package duke.commands;

import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDateTime;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.tasks.Event;

/**
 * Adds an event to the task list.
 */
public class EventCommand extends Command {
    /**
     * The command word that identifies an EventCommand instance.
     */
    public static final String COMMAND_WORD = "event";

    /**
     * Length of the command word.
     */
    public static final int COMMAND_LENGTH = COMMAND_WORD.length();

    /**
     * Guide on how to use this command word.
     */
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + " <description> /at <date> - add an event scheduled at <date> in yyyy/MM/dd HHmm (24-hour format)\n"
            + "    📍 Example: " + COMMAND_WORD + " project meeting /at 2021/08/24 1500";

    private String userCommand;

    /**
     * Instantiates EventCommand object.
     *
     * @param userCommand Full user input.
     */
    public EventCommand(String userCommand) {
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
            // a valid command with task description and date should give a String[] of length 2.
            // splitCommand[0] will be of format event <description>.
            // splitCommand[1] will be the date.
            String[] splitCommand = userCommand.split("/at");

            if (userCommand.length() <= COMMAND_LENGTH || splitCommand[0].strip().length() <= COMMAND_LENGTH
                    || splitCommand.length < 2) {
                throw new IllegalArgumentException("Please add a description and/or date for your event!");
            } else {
                LocalDateTime date = LocalDateTime.parse(splitCommand[1].strip(), Command.INPUT_FORMATTER);
                Event newEvent = new Event(splitCommand[0].substring(COMMAND_LENGTH).strip(), date);

                tasks.addTask(newEvent);
                storage.save(tasks.getItems());

                return ui.printTaskAdded(newEvent, tasks.getSize());
            }
        } catch (IOException | IllegalArgumentException e) {
            return ui.printError(e.getMessage());
        } catch (DateTimeException e) {
            return ui.printError("Please add a valid event date of format yyyy/MM/dd HHmm (24-hour format)!");
        }
    }
}
