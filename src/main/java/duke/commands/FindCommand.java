package duke.commands;

import duke.Storage;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;

public class FindCommand extends Command {
    private String command;

    public FindCommand(String command) {
        this.command = command;
    }

    public String[] execute(Storage storage, TaskList tasks) {
        Task[] result = new Task[tasks.size()];
        String[] words = command.split(" ");
        int count = 0;
        if (words.length == 1) {
            throw new DukeException("invalidFindTask");
        } else {
            String piece = command.substring(5);
            for (int i = 0; i < tasks.size(); i++) {
                String name = tasks.get(i).getName();
                if (name.contains(piece)) {
                    result[count] = tasks.get(i);
                    count++;
                }
            }
        }
        return Ui.printFindTask(result);
    }
}
