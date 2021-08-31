package duke;

import java.io.EOFException;
import java.io.IOException;

import javafx.application.Platform;

/**
 * Main class of the Duke chat-bot. When the main class is run, it creates an
 * instance of Duke which allows text-based user interaction.
 */
public class Duke {
    private final Storage storage;
    private TaskList tasks = new TaskList();
    private MainWindow window;

    /**
     * Class constructor for Duke.
     */
    public Duke(MainWindow window) {
        storage = new Storage("duke.txt");
        this.window = window;
        try {
            tasks = storage.readSave();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e);
        }
    }

    String getResponse(String input) throws IOException {
        Command command;
        try {
            command = Parser.parseUserInput(input);
        } catch (DukeException e) {
            return e.toString();
        }
        Task task;
        switch (command.getOperation()) {
        case "bye":
            Platform.exit();
            return "";
        case "list":
            return Ui.taskListString(tasks);
        case "done":
            task = tasks.get(command.getIndex() - 1);
            task.setDone(true);
            storage.writeSave(tasks);
            return Ui.doneString(task);
        case "todo":
            task = new ToDo(command.getDescription());
            tasks.add(task);
            storage.writeSave(tasks);
            return Ui.addedString(task, tasks.size());
        case "deadline":
            task = new Deadline(command.getDescription(), command.getTime());
            tasks.add(task);
            storage.writeSave(tasks);
            return Ui.addedString(task, tasks.size());
        case "event":
            task = new Event(command.getDescription(), command.getTime());
            tasks.add(task);
            storage.writeSave(tasks);
            return Ui.addedString(task, tasks.size());
        case "delete":
            try {
                task = tasks.get(command.getIndex() - 1);
            } catch (IndexOutOfBoundsException e) {
                return Ui.outOfBoundsString(command.getIndex());
            }
            tasks.delete(command.getIndex() - 1);
            storage.writeSave(tasks);
            return Ui.deletedString(task, tasks.size());
        case "find":
            TaskList filteredTasks = tasks.find(command.getDescription());
            return Ui.taskListString(filteredTasks);
        default:
            return "";
        }
    }
}
