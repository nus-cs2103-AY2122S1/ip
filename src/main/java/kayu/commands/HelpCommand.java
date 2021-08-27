package kayu.commands;

import static kayu.commands.CommandType.HELP;

import kayu.exception.DukeException;
import kayu.service.TaskList;

/**
 * HelpCommand class.
 *
 * This class is an instance of {@link kayu.commands.Command} that uses the keyword {@link #COMMAND_WORD}. 
 * It returns the possible commands a user can key in for operations.
 */
public class HelpCommand extends Command {

    /** Key word for command. */
    public static final String COMMAND_WORD = "help";

    /**
     * Initializes a Help- {@link kayu.commands.Command}.
     */
    public HelpCommand() {
        super(HELP);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList taskList) throws DukeException {
        return "--- List of commands ---\n" 
                + "\n" 
                + "General:\n" 
                + " bye     \n" 
                + " --> Terminate the session with Kayu.\n" 
                + "\n" 
                + " help \n" 
                + " --> List all the possible commands to call on Kayu.\n" 
                + "\n" 
                + "Task-Creation:\n"
                + " todo     <desc>\n" 
                + " --> Creates a todo Task with the given <desc>. \n"
                + "     <desc> cannot be empty.\n" 
                + "     Example: todo read book\n" 
                + "\n" 
                + " event    <desc> /at <date> <time>\n" 
                + " --> Creates an event Task with the given <desc> and timing using /at keyword.\n" 
                + "     <desc>, <date> and <time> cannot be empty.\n" 
                + "     Example: event meeting /at 20-10-2021 1300\n" 
                + "\n" 
                + " deadline <desc> /by <date> <time>\n" 
                + " --> Creates a deadline Task with the given <desc> and timing using /by keyword. \n" 
                + "     <desc>, <date> and <time> cannot be empty.\n" 
                + "     Example: deadline assignment /by 16-08-2021 1045\n" 
                + "\n" 
                + "Task-Operations:\n" 
                + " list\n" 
                + " --> Lists all the Tasks present in the tasklist.\n" 
                + "\n" 
                + " delete   <task-number>\n" 
                + " --> Delete the Task with <task-number> from the tasklist.\n"
                + "     <task-number> cannot be empty.\n" 
                + "     Example: delete 4\n" 
                + "\n" 
                + " done     <task-number>\n" 
                + " --> Marks the Task with <task-number> as done.\n" 
                + "     <task-number> cannot be empty.\n" 
                + "     Example: done 3\n" 
                + "\n" 
                + " find     <keyword>\n" 
                + " --> Searches the Tasks in the tasklist that has the <keyword> in the description. \n" 
                + "     <keyword> cannot be empty.\n" 
                + "     Example: find test";
    }
}
