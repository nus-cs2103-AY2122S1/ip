package duke.command;

import duke.exception.UnknownCommandException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

public class UnknownCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws UnknownCommandException {
        String suggestions = String.format("%s\n" +
                "\t%s\n" +
                "\t %s\n" +
                "\t %s\n" +
                "\t %s\n" +
                "\t %s\n" +
                "\t %s\n" +
                "\t %s\n" +
                "\t %s\n",
                "I'm sorry, but I don't know what that means :-(",
                "Try the following commands instead:\n",
                "1. list - List all the existing tasks",
                "2. bye - Exit from duke.Duke's service",
                "3. done {N} -  Mark task of number 'N' as done",
                "4. delete {N} - Delete task of number 'N'",
                "5. todo {description} - Add a todo with the specified 'description'",
                "6. event {description} /at {date} - Add an event with the specified 'description' happening at 'date'",
                "7. deadline {description} /by {date} - Add a deadline with the specified 'description' expires at 'date'");
        throw new UnknownCommandException(suggestions);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
