import java.util.Scanner;

public class Duke {
    private Ui ui;
    private TaskList taskList;
    private Storage storage;
    private boolean shouldExit;

    public enum CommandType {
        BYE, LIST, DONE, DELETE, TODO, DEADLINE, EVENT,
    }

    Duke(Scanner in, String filePath) throws Exception {
        this.shouldExit = false;
        this.ui = new Ui(in);
        this.storage = new Storage(filePath);
        // TODO: throw error if unable to create file?
        this.taskList = new TaskList(storage.loadTasks());
    }

    private void handleInput() throws Exception {
        String[] commandArguments = ui.readCommand();
        String commandString = commandArguments[0];
        String arguments = commandArguments[1];

        CommandType commandType;
        try {
            commandType = CommandType.valueOf(commandString.toUpperCase());
        } catch (IllegalArgumentException e) {
            String fullInput = commandString + " " + arguments;
            throw new Exception("Command not recognized: " + fullInput);
        }

        Command command;
        switch (commandType) {
            case BYE: {
                command = new ByeCommand(arguments);
                break;
            }
            case LIST: {
                command = new ListTasksCommand(arguments);
                break;
            }
            case DONE: {
                command = new CompleteTaskCommand(arguments);
                break;
            }
            case DELETE: {
                command = new DeleteTaskCommand(arguments);
                break;
            }
            case TODO: {
                command = new AddTodoCommand(arguments);
                break;
            }
            case DEADLINE: {
                command = new AddDeadlineCommand(arguments);
                break;
            }
            case EVENT: {
                command = new AddEventCommand(arguments);
                break;
            }
            default: {
                throw new Exception("No command found");
            }
        }

        command.execute(taskList, ui, storage);
        this.shouldExit = command.shouldExit();
    }

    private boolean shouldExit() {
        return this.shouldExit;
    }

    public void run() {
        ui.printGreeting();

        while (!shouldExit()) {
            try {
                handleInput();
            } catch (Exception e) {
                // TODO: custom Duke exceptions?
                ui.printMessage("Error: " + e.getMessage());
            }
        }

        ui.printGoodbye();
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String filePath = "duke.txt";
        Duke duke;

        try {
            duke = new Duke(input, filePath);
        } catch (Exception e) {
            // TODO: figure out static/non-static Ui class
            System.out.println("Unable to initialize data file");
            input.close();
            return;
        }

        duke.run();
        input.close();
    }
}
