package duke.commands;

import duke.Storage;
import duke.task.TaskList;

public class ListCommand extends Command {
    private String command;

    public ListCommand(String command) {
        this.command = command;
    }

    public String[] execute(Storage storage, TaskList tasks) {
        return tasks.printList(command);
    }
}
