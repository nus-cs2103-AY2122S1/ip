package duke.command;

import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Represents an abstract Command class.
 *
 * @author Chen Hsiao Ting
 * @version CS2103T AY21/22 Semester 1
 */
public abstract class Command {
    protected TaskList tasks;
    protected Parser parser;
    protected Storage storage;
    protected Ui ui;

    /**
     * A constructor for Command.
     *
     * @param tasks   A list of current Tasks.
     * @param parser  Parser to interpret user input.
     * @param storage Storage to store data
     * @param ui      Ui responsible for user interaction.
     */
    public Command(TaskList tasks, Parser parser, Storage storage, Ui ui) {
        this.tasks = tasks;
        this.parser = parser;
        this.storage = storage;
        this.ui = ui;
    }
}
