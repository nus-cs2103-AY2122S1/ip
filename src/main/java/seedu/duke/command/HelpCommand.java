package seedu.duke.command;

import seedu.duke.Ui;
import seedu.duke.task.Task;
import seedu.duke.task.TaskList;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Represents a help command. A <code>HelpCommand</code> returns
 * Duke's usage when a user is needs help.
 */
public class HelpCommand extends Command {
    private static final String HELP_MESSAGE = "Here are the available commands:\n";
    private ArrayList<Command> commands;
    private HashMap<LocalDate, ArrayList<Task>> dateTasks;

    /**
     * Public constructor for HelpCommand.
     *
     * @param ui The Ui to handle user interactions.
     * @param taskList The tasklist to be updated.
     */
    public HelpCommand(Ui ui, TaskList taskList,
                       HashMap<LocalDate, ArrayList<Task>> dateTasks) {
        super(ui, taskList);
        this.dateTasks = dateTasks;

        // Add dummy commands
        commands = new ArrayList<>();

        commands.add(new ExitCommand(ui, taskList));
        commands.add(new ListCommand(ui, taskList));
        commands.add(new ToDoCommand(ui, taskList, "", null));
        commands.add(new DeadlineCommand(ui, taskList, "", null, null));
        commands.add(new EventCommand(ui, taskList, "", null, null));
        commands.add(new DoneCommand(ui, taskList, 0, null));
        commands.add(new DeleteCommand(ui, taskList, dateTasks, 0, null));
        commands.add(new GetCommand(ui, taskList, null, null));
        commands.add(new FindCommand(ui, taskList, ""));
    }

    /**
     * Returns the format on how to use the command.
     *
     * @return String representation of the help message.
     */
    @Override
    public String getUsageMessage() {
        return "help  | get the available commands for Duke.";
    }

    /**
     * Returns a string representation of
     * the available commands.
     */
    @Override
    public String execute() {
        ui.divide();
        String help = HELP_MESSAGE + "\n";
        for (Command command : commands) {
            help += command.getUsageMessage() + "\n\n";
        }
        return help;
    }

}

