package duke;

import duke.command.Command;
import duke.exception.DukeIndexInputException;
import duke.exception.DukeTaskDetailsException;
import duke.exception.DukeUpdateException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke() {
        this.storage = new Storage(Paths.get(".", "data"),
                                   Paths.get(".", "data", "tasks.txt"));
        this.tasks = new TaskList(this.storage.getTasks());
        this.ui = new Ui();
    }

    private String runInstruction(String input) {
        String[] splitInput = input.split(" ", 2);
            Command command = Parser.parse(splitInput);
            try {
                switch (command) {
                case EXIT:
                    new Timer().schedule(new TimerTask() {
                            @Override
                            public void run() {
                                System.exit(0);
                            }
                        }, 300L); // 300 is the delay in millis
                    return this.ui.exit();
                case LIST:
                    return this.ui.list(this.tasks);
                case INDEXCOMMAND:
                    Task task = this.tasks.indexCommand(splitInput);
                    return this.ui.indexCommand(this.tasks.size(), task, splitInput);
                case ADDCOMMAND:
                    Task addedTask = this.tasks.addTask(splitInput);
                    return this.ui.addTask(addedTask, this.tasks.size());
                case FIND:
                    List<Task> list = this.tasks.find(splitInput[1]);
                    return this.ui.list(list);
                case UPDATE:
                    Task updatedTask = this.tasks.updateTask(splitInput);
                    return this.ui.updateTask(updatedTask);
                default:
                    return this.ui.unknownCommand();
                }
            } catch (DukeTaskDetailsException | DukeIndexInputException | DukeUpdateException e) {
                return "\t" + e.toString();
            } finally {
                this.storage.saveTasks(this.tasks.getTasks());
            }
    }

    public String getResponse(String input) {
        return this.runInstruction(input.strip());
    }
}
