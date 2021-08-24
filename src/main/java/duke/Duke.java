package duke;

import java.time.LocalDate;

/**
 * A human wannabe frog chat bot who can help keep track of tasks.
 */
public class Duke {

    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        ui.greeting();
        storage = new Storage(filePath);
        try {
            tasks = storage.load();
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Starts an instance of Duke/Jo.
     */
    public void run() {
        boolean isExit = false;

        while (!isExit) {
            try {
                String input = ui.readCommand();
                if (input.equals("bye")) {
                    isExit = true;
                } else {
                    ui.showLine();
                    String[] commands = Parser.parse(input);
                    this.run(commands);
                }
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
        ui.goodbye();
        storage.save();
    }

    public void run(String[] args) {
        switch (args[0]) {
            case "done":
                int index = Integer.parseInt(args[1]) - 1;
                this.tasks.markDone(index);
                ui.doneMessage(tasks, index);
                break;
            case "delete":
                int idx = Integer.parseInt(args[1]) - 1;
                ui.deleteMessage(tasks, idx - 1);
                this.tasks.delete(idx);
                break;
            case "list":
                ui.listMessage(tasks);
                break;
            case "todo":
                tasks.add(new ToDo(args[1]));
                ui.addTaskMessage(tasks);
                break;
            case "deadline":
                LocalDate date = LocalDate.parse(args[2]);
                tasks.add(new Deadline(args[1], date, args[3]));
                ui.addTaskMessage(tasks);
                break;
            case "event":
                tasks.add(new Event(args[1], args[2]));
                ui.addTaskMessage(tasks);
                break;
            default:
                System.out.println("Jo does not recognise non-frog speak!");
                break;
        }
    }

    public static void main(String[] args) {
        new Duke("./src/main/data/duke.txt").run();
    }
}
