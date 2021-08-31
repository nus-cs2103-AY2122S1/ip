package duke.command;

import duke.exception.UnknownCommandException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/** A class that handles unknown command. */
public class UnknownCommand extends Command {

    /**
     * Hint the user about the functional commands.
     *
     * @param tasks The list that stores all the tasks to be added/deleted.
     * @param ui The ui that deals with interactions with the user.
     * @param storage The storage that deals with loading tasks from the file and saving tasks in the file.
     * @throws UnknownCommandException The exception for handling unknown commands.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String suggestions = String.format("%s\n" +
                "%s\n" +
                "%s\n" +
                "%s\n" +
                "%s\n" +
                "%s\n" +
                "%s\n" +
                "%s\n" +
                "%s\n",
                "I'm sorry, but I don't know what that means :-(",
                "Try the following commands instead:\n",
                "1. list - List all the existing tasks",
                "2. bye - Exit from duke.Duke's service",
                "3. done {N} -  Mark task of number 'N' as done",
                "4. delete {N} - Delete task of number 'N'",
                "5. todo {description} - Add a todo with the specified 'description'",
                "6. event {description} /at {date} - " +
                        "Add an event with the specified 'description' happening at 'date'",
                "7. deadline {description} /by {date} - " +
                        "Add a deadline with the specified 'description' expires at 'date'");
        return suggestions;
    }

    /**
     * Return a boolean value of whether it is a command that exit the program.
     *
     * @return The boolean value of whether it is a command that exit the program.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
