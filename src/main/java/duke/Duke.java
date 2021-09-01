package duke;

import duke.command.Command;
import duke.exception.DukeIndexInputException;
import duke.exception.DukeTaskDetailsException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.nio.file.Paths;
import java.util.Scanner;
import java.util.ArrayList;

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

	/*
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }
		*/

    private void run() {
        this.ui.greet();
        Scanner sc = new Scanner(System.in);
        Boolean run = true;
        while (run && sc.hasNextLine()) {
            String input = sc.nextLine();
            System.out.println(Ui.DIVIDER_LINE);
            input = input.strip();
            this.runInstruction(input);
            System.out.println(Ui.DIVIDER_LINE);
            this.storage.saveTasks(this.tasks.getTasks());
        }
        sc.close();
    }

    private String runInstruction(String input) {
        String[] splitInput = input.split(" ", 2);
            Command command = Parser.parse(splitInput);
            try {
                switch (command) {
                case EXIT:
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
                    ArrayList<Task> list = this.tasks.find(splitInput[1]);
                    return this.ui.list(list);
                default:
                    return this.ui.unknownCommand();
                }
            } catch (DukeTaskDetailsException | DukeIndexInputException e) {
                return "\t" + e.toString();
            }
    }

    public String getResponse(String input) {
        return this.runInstruction(input.strip());
    }
}
