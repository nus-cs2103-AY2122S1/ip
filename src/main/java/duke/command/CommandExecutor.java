package duke.command;

import duke.exceptions.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.taskList.TaskList;
import duke.ui.Ui;

public class CommandExecutor {
    private final TaskList tasks;
    private final Storage storage;
    private final Ui ui;

    public CommandExecutor(TaskList tasks, Storage storage, Ui ui) {
        this.tasks = tasks;
        this.storage = storage;
        this.ui = ui;
    }

    public String execute(String input) throws DukeException {
        Parser parser = new Parser(input);
        String command = parser.getCommandWord();

        if (command.equals("bye")) {
            return ui.showBye();
        } else if (command.equals("list")) {
            return new ListCommand(tasks, input).list();
        } else if (command.equals("find")) {
            return new FindCommand(tasks, input).find();
        } else if (command.equals("done")) {
            return new DoneCommand(tasks, input).done();
        } else if (command.equals("delete")) {
            return new DeleteCommand(tasks, input).delete();
        } else if (command.equals("todo")) {
            return new AddCommand(tasks, input).addTodo();
        } else if (command.equals("deadline")) {
            return new AddCommand(tasks, input).addDeadline();
        } else if (command.equals("event")) {
            return new AddCommand(tasks, input).addEvent();
        } else {
            throw new DukeException("Command is not valid!");
        }
    }
}
