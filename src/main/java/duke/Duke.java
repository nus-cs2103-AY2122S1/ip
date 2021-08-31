package duke;

import java.io.EOFException;
import java.io.IOException;
import java.util.Scanner;

/**
 * Main class of the Duke chat-bot. When the main class is run, it creates an
 * instance of Duke which allows text-based user interaction.
 */
public class Duke {
    private final Ui ui;
    private final Scanner sc = new Scanner(System.in);
    private final Storage storage;
    private TaskList tasks = new TaskList();

    /**
     * Class constructor which takes the relative filepath where Duke's save-file
     * is stored.
     *
     * @param filePath Path of the save-file
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
    }

    /**
     * Point of entry through which Duke can be run.
     *
     * @param args The commandline arguments.
     */
    public static void main(String[] args) {
        new Duke("duke.txt").run();
    }

    /**
     * Loads the save file and begins to accept user input.
     */
    public void run() {
        try {
            tasks = storage.readSave();
        } catch (EOFException e) {
            ui.showNewSave();
        } catch (IOException | ClassNotFoundException e) {
            ui.showReadSaveError();
        }
        ui.showIntro();
        outer:
        while (true) {
            String userEntry = sc.nextLine();
            Command command;
            try {
                command = Parser.parseUserInput(userEntry);
            } catch (DukeException e) {
                ui.print(e.toString());
                continue;
            }
            Task task;
            switch (command.getOperation()) {
            case "bye":
                ui.showOutro();
                try {
                    storage.writeSave(tasks);
                } catch (IOException e) {
                    ui.showWriteSaveError();
                }
                break outer;
            case "list":
                ui.showTasks(tasks);
                break;
            case "done":
                task = tasks.get(command.getIndex() - 1);
                task.setDone(true);
                ui.showDone(task);
                break;
            case "todo":
                task = new ToDo(command.getDescription());
                tasks.add(task);
                ui.showAdded(task, tasks.size());
                break;
            case "deadline":
                task = new Deadline(command.getDescription(), command.getTime());
                tasks.add(task);
                ui.showAdded(task, tasks.size());
                break;
            case "event":
                task = new Event(command.getDescription(), command.getTime());
                tasks.add(task);
                ui.showAdded(task, tasks.size());
                break;
            case "delete":
                task = tasks.get(command.getIndex() - 1);
                tasks.delete(command.getIndex() - 1);
                ui.showDeleted(task, tasks.size());
                break;
            case "find":
                TaskList filteredTasks = tasks.find(command.getDescription());
                ui.showMatches(filteredTasks);
                break;
            default:
                break;
            }
        }
    }
}
