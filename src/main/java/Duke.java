import duke.InputType;
import duke.Parser;
import duke.Storage;
import duke.tasks.*;
import duke.Ui;
import duke.command.Command;
import duke.command.CommandResult;

import java.util.Scanner;

public class Duke {

    // Path to file data/duke.txt
    public static final String DUKE_TXT = "data/duke.txt";

    private TaskManager taskList;
    private Storage storage;
    private Ui ui;
    private Parser parser;


    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        parser = new Parser();

        TaskManagerUi result = storage.initialiseTaskList();
        taskList = result.taskList;
        ui.printOneLine(result.message);
    }

    public CommandResult executeCommand(Command command) {
        try {
            return command.execute();
        } catch (Exception e) {
            return new CommandResult(e.getMessage(), false, null);
        }
    }

    // Continuously take in input and running the commands until input is BYE then exit
    public void runDuke() {
        ui.printGreeting();

        // Initialise variables to get input from user
        String userInput;
        Scanner in = new Scanner(System.in);

        // Get the first input from user
        ui.printPrompt();
        userInput = in.nextLine();

        // While the input is not "bye", add input to array of tasks and get another input
        while (!userInput.equals(InputType.BYE.getInputType())) {

            Command command = parser.parseCommand(taskList, userInput);

            CommandResult commandResult = executeCommand(command);

            if (commandResult.isUpdated()) {
                taskList = commandResult.getTaskList();
                String errorMessage = storage.writeToFile(taskList);

                ui.printMessage(errorMessage);
            }

            ui.printMessage(commandResult.getFeedbackMessage());

            // Get another input
            ui.printPrompt();
            userInput = in.nextLine();
        }

        ui.printExitLine();
    }

    public static void main(String[] args) {
        new Duke(DUKE_TXT).runDuke();
    }
}