package seedu.duke.command;

import seedu.duke.Ui;
import seedu.duke.task.TaskList;

import java.util.ArrayList;

/**
 * Represents a help command. A <code>HelpCommand</code> returns
 * Duke's usage when a user is needs help.
 */
public class HelpCommand extends Command {
    private static final String HELP_MESSAGE = "Here are the available commands:\n";
    private ArrayList<Command> commands;

    /**
     * Public constructor for HelpCommand.
     *
     * @param ui The Ui to handle user interactions.
     * @param taskList The tasklist to be updated.
     */
    public HelpCommand(Ui ui, TaskList taskList) {
        super(ui, taskList);

        // Add dummy commands
        commands = new ArrayList<>();

        commands.add(new ExitCommand(ui, taskList));
        commands.add(new ListCommand(ui, taskList));
        commands.add(new ToDoCommand(ui, taskList, "", null));
        commands.add(new DeadlineCommand(ui, taskList, "", null, null));
        commands.add(new EventCommand(ui, taskList, "", null, null));
        commands.add(new DoneCommand(ui, taskList, 0, null));
        commands.add(new DeleteCommand(ui, taskList, 0, null));
        commands.add(new GetCommand(ui, taskList, null, null));
        commands.add(new FindCommand(ui, taskList, ""));
    }

    @Override
    public String getUsageMessage() {
        return "help  | get the available commands for Duke.";
    }

    /**
     * Prints the available commands.
     */
    @Override
    public void execute() {
        ui.divide();
        ui.outputMessage(HELP_MESSAGE);
        commands.forEach((command) -> ui.outputMessage(command.getUsageMessage() + "\n"));
        ui.divide();
    }

}

