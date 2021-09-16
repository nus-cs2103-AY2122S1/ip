package duke.command;

import duke.data.TaskList;
import duke.exception.*;
import duke.storage.Storage;
import duke.ui.Ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Stores description of usage and format of command.
 */
public abstract class Command {

    /**
     * Constructs Command object
     */
    public Command() {}

    public abstract String execute(TaskList tasks, Ui ui, Storage storage);
}
