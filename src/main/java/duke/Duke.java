package duke;

import java.util.Scanner;

/**
 * Handles receiving input from user and starting/stopping the program.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Creates Ui and Storage instances.
     * Loads data from fisk into TaskList object to create list of tasks user currently has.
     * @param filePath denotes the relative file path of where the tasks will be saved in the disk.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
    }


    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }


    /**
     * Continuously waits for input from user and executes the appropriate commands
     * based on command type.
     */
    public void run() {
        ui.greeting();

        Scanner scanner = new Scanner(System.in);
        boolean isExit = false;

        while (!isExit && scanner.hasNextLine()) {
            try {
                String fullCommand = scanner.nextLine().trim();
                CommandType commandType = Parser.parse(fullCommand);

                switch (commandType) {
                    case LIST:
                        ui.showTasks(tasks.getTaskList());
                        break;
                    case BYE:
                        ui.bye();
                        isExit = true;
                        break;
                    case DELETE:
                        tasks.delete(fullCommand.substring(7), storage, ui);
                        break;
                    case DONE:
                        tasks.done(fullCommand.substring(5), storage, ui);
                        break;
                    case TODO:
                        tasks.todo(fullCommand.substring(5), storage, ui);
                        break;
                    case DEADLINE:
                        tasks.deadline(fullCommand.substring(9), storage, ui);
                        break;
                    case EVENT:
                        tasks.event(fullCommand.substring(6), storage, ui);
                        break;
                }
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
        }

        scanner.close();
    }


}


