package duke;

import java.util.Scanner;

import duke.command.Command;
import duke.ui.Ui;

public class Duke {
    private Ui ui;
    private TaskList taskList;
    private Storage storage;

    public Duke(String filePath) throws Exception {
        super();
        this.storage = new Storage(filePath);
        // TODO: throw error if unable to create file?
        this.taskList = new TaskList(storage.loadTasks());
    }

    public Duke(Scanner in, String filePath) throws Exception {
        super();
//        this.ui = new ConsoleUi(in);
        this.storage = new Storage(filePath);
        // TODO: throw error if unable to create file?
        this.taskList = new TaskList(storage.loadTasks());
    }

    public void setUi(Ui ui) {
        this.ui = ui;
    }

    public boolean executeCommand(String fullCommand) {
        try {
            Command command = Parser.parse(fullCommand);
            command.execute(taskList, ui, storage);
            return command.shouldExit();
        } catch (Exception e) {
            // TODO: custom Duke exceptions?
            ui.printMessage("Error: " + e.getMessage());
            return false;
        }
    }

//    /**
//     * Runs the instance of {@link Duke}.
//     */
//    public void run() {
//        ui.printGreeting();
//
//        boolean shouldExit = false;
//        while (!shouldExit) {
//            try {
//                String fullCommand = ui.readCommand();
//                Command command = Parser.parse(fullCommand);
//                command.execute(taskList, ui, storage);
//                shouldExit = command.shouldExit();
//            } catch (Exception e) {
//                // TODO: custom Duke exceptions?
//                ui.printMessage("Error: " + e.getMessage());
//            }
//        }
//
//        ui.printGoodbye();
//    }

//    /**
//     * Initializes an instance of the {@link Duke} class and runs the program.
//     *
//     * @param args The command-line arguments.
//     */
//    public static void main(String[] args) {
//        Scanner inputScanner = new Scanner(System.in);
//        String filePath = "duke.txt";
//        Duke duke;
//
//        try {
//            duke = new Duke(inputScanner, filePath);
//        } catch (Exception e) {
//            // TODO: figure out static/non-static Ui class
//            System.out.println("Unable to initialize data file");
//            inputScanner.close();
//            return;
//        }
//
//        duke.run();
//        inputScanner.close();
//    }
}
