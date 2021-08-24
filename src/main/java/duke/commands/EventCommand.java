package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.tasks.Event;

import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDateTime;

public class EventCommand extends Command {
    public static final String COMMAND_WORD = "event";
    public static final String MESSAGE_USAGE = COMMAND_WORD +
            " <description> /at <date> - add an event scheduled at <date> in yyyy/MM/dd HHmm (24-hour format)\n" +
            "   Example: " + COMMAND_WORD + " project meeting /at 2021/08/24 1500";

    private String userCommand;

    public EventCommand(String userCommand) {
        super();
        this.userCommand = userCommand;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            int atIndex = userCommand.indexOf("/at");
            if (userCommand.strip().length() < 6 || atIndex <= 6) {
                throw new IllegalArgumentException("Please add a description and/or date for your event!");
            }

            String at = userCommand.substring(atIndex + 4);
            LocalDateTime date = LocalDateTime.parse(at, Command.inputFormatter);
            Event newEvent = new Event(userCommand.substring(6, atIndex - 1), date);

            tasks.addTask(newEvent);
            storage.save(tasks.getItems());

            ui.printTaskAdded(newEvent, tasks.getSize());

        } catch (IOException | IllegalArgumentException e) {
            ui.printError(e.getMessage());

        } catch (DateTimeException e) {
            ui.printError("Please add a valid event date of format yyyy/MM/dd HHmm (24-hour format)!");
        }

    }
}
