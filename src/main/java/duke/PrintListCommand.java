package duke;

import duke.exception.DukeException;

public class PrintListCommand extends Command {
    public PrintListCommand(TaskList tasks) {
        super(tasks);
    }

    @Override
    public String run() {
        return tasks.printList();
    }
}
