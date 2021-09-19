package duke.main;

import java.time.LocalDate;
import java.util.Scanner;

import duke.command.Parser;
import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;
import duke.ui.Ui;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Initializes Ui, storage and load TaskLists from specific filePath for Duke.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Initializes the duke.Duke chatbot program for the duke.Launcher
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage("data/tasks.txt");
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Run the programme of Duke.
     */
    public void run() {
        String input = "";
        ui.greet();
        Scanner keyboardIn = new Scanner(System.in);
        do {
            input = keyboardIn.nextLine();
            getResponse(input);

        } while (!input.equals("bye"));
        storage.updateData();
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }

    public String getResponse(String input) {
        String output = "";
        try {
            Parser parser = new Parser(input);
            String operation = parser.getOperationType();
            switch (operation) {
                case "bye": {
                    output = ui.bye();
                    break;
                }
                case "list": {
                    output = ui.list(tasks);
                    break;
                }
                case "done": {
                    int index = parser.getIndex();
                    tasks.markDone(index - 1);
                    output = ui.done(tasks.get(index - 1));
                    break;
                }
                case "delete": {
                    int index = parser.getIndex();
                    Task task = tasks.get(index - 1);
                    tasks.delete(index - 1);
                    output = ui.delete(tasks, task);
                    break;
                }
                case "todo": {
                    String description = parser.getTask();
                    Task task = new Todo(description);
                    tasks.add(task);
                    output = ui.add(tasks, task);
                    break;
                }
                case "event": {
                    String description = parser.getTask();
                    LocalDate time = parser.getTime();
                    Task task = new Event(description, time);
                    tasks.add(task);
                    output = ui.add(tasks, task);
                    break;
                }
                case "deadline": {
                    String description = parser.getTask();
                    LocalDate time = parser.getTime();
                    Task task = new Deadline(description, time);
                    tasks.add(task);
                    output = ui.add(tasks, task);
                    break;
                }
                case "find": {
                    String keyword = parser.getTask();
                    output = ui.find(tasks, keyword);
                    break;
                }
                default:
            }
            return output;
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            return e.getMessage();
        }
    }

}
