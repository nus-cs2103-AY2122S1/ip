package duke.command;

import duke.commandresult.CommandResult;
import duke.tasklist.TaskList;

public class HelpCommand extends Command {

    /** Class level constant that signifies the command used to invoke this. */
    public static final String COMMAND_WORD = "help";

    /** Class level constant that represents the actions taken by the command. */
    public static final String DESCRIPTION = "Find out about the various commands you can use with Duke.";

    /** Class level constant which represents the feedback string to be returned. */
    public static final String FEEDBACK = "Here are the various commands you can use with Duke!\n\n";

    /** Class level list of command descriptions. */
    public static final String[] COMMAND_DESCRIPTION_LIST = new String[]{
            DeadlineCommand.formatAndDescription(),
            DeleteCommand.formatAndDescription(),
            DoneCommand.formatAndDescription(),
            EventCommand.formatAndDescription(),
            ExitCommand.formatAndDescription(),
            FindCommand.formatAndDescription(),
            HelpCommand.formatAndDescription(),
            ListCommand.formatAndDescription(),
            TodoCommand.formatAndDescription(),
            UpdateCommand.formatAndDescription()
    };

    /**
     * Abstract constructor that will have to be implemented by all classes that extend Command.
     *
     * @param taskList that is passed by Duke.
     */
    public HelpCommand(TaskList taskList) {
        super(taskList);
    }

    /**
     * Returns the string representation of the command description and format.
     * @return String that represents the command description and format.
     */
    public static String formatAndDescription() {
        return COMMAND_WORD + ": " + DESCRIPTION;
    }

    /**
     * Overrides execute() from Command and returns a CommandResult which stores the feedback string
     * to be returned to the UserInterface.
     * @return CommandResult to be rendered by UserInterface.
     */
    @Override
    public CommandResult execute() {
        String feedbackToReturn = formatHelpCommandDescriptionList();
        return new CommandResult(feedbackToReturn, false);
    }

    private String formatHelpCommandDescriptionList() {
        StringBuilder stringBuilder = new StringBuilder(HelpCommand.FEEDBACK);
        for (String s : COMMAND_DESCRIPTION_LIST) {
            stringBuilder.append(s).append("\n");
        }
        return stringBuilder.toString();
    }
}
