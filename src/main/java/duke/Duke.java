package duke;

import java.io.IOException;
import java.util.Scanner;



public class Duke {
    private final Ui ui;
    private TaskList taskList;
    private final Storage storage;



    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage();
        taskList = new TaskList();
        try {
            taskList = new TaskList(storage.load(filePath));
        } catch (DukeException e) {
            ui.printError(e.getMessage());
            taskList = new TaskList();
        }

    }

    public Duke() {
        this(Storage.SERIALIZATION_PATH);
    }

    /**
     * Main function that runs the Duke chatbot
     * Consists of conditional branches for all the commands of Duke
     */
    public void run() {
        Ui.initialize();
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        String[] commandAndParameter = Parser.inputParser(input);
        Command currentCommand = Parser.commandParser(commandAndParameter[0]);
        String currentParameter = commandAndParameter[1];

        do {
            try {
                System.out.println(currentCommand);
                StringBuilder sb = new StringBuilder();
                String[] descriptionAndTime;
                switch (currentCommand) {
                    case UNKNOWN:
                        throw new DukeException("Unknown input");
                    case LIST:
                        sb.append("Here are the tasks in your list:\n");
                        sb.append(taskList.listTasks());
                        Ui.printStatement(sb.toString());
                        break;
                    case TODO:
                        if (currentParameter == "") {
                            throw new DukeException("TODO cannot have empty parameter.");
                        }
                        taskList.addTask(Command.TODO, currentParameter);
                        Storage.updateLocalFile(taskList);
                        break;
                    case EVENT:
                        if (currentParameter.equals("")) {
                            throw new DukeException("The description of a event cannot be empty.");
                        } else if (!currentParameter.contains(" /at ")) {
                            throw new DukeException("Missing /at command");
                        }
                        System.out.println(currentParameter);
                        descriptionAndTime = Parser.dateParameterParser(Command.EVENT, currentParameter);
                        System.out.println(descriptionAndTime);
                        taskList.addTask(Command.EVENT, descriptionAndTime[0], descriptionAndTime[1]);
                        Storage.updateLocalFile(taskList);
                        break;
                    case DEADLINE:
                        if (currentParameter.equals("")) {
                            throw new DukeException("The description of a deadline cannot be empty.");
                        } else if (!currentParameter.contains(" /by ")) {
                            throw new DukeException("Missing /by command");
                        }
                        descriptionAndTime = Parser.dateParameterParser(Command.DEADLINE, currentParameter);
                        taskList.addTask(Command.DEADLINE, descriptionAndTime[0], descriptionAndTime[1]);
                        Storage.updateLocalFile(taskList);
                        break;
                    case DELETE:
                        if (currentParameter.equals("")) {
                            throw new DukeException("Please indicate item to be deleted.");
                        }
                        int index = Integer.parseInt(currentParameter) - 1;
                        if (index > taskList.size() - 1) {
                            throw new DukeException("Item does not exist.");
                        }

                        taskList.removeTask(index);
                        Storage.updateLocalFile(taskList);
                        break;
                    case DONE:
                        if (currentParameter.equals("")) {
                            throw new DukeException("Please indicate item to be completed.");
                        }
                        int number = Integer.parseInt(currentParameter) - 1;
                        if (number > taskList.size() - 1 || number < 0) {
                            throw new DukeException("Invalid item does not exist");
                        }
                        taskList.done(number);
                        Storage.updateLocalFile(taskList);
                        break;
                }
            } catch (DukeException | IOException e) {
                Ui.printStatement("OOPS!!! " + e.getMessage());
            }
            input = sc.nextLine();
            commandAndParameter = Parser.inputParser(input);
            currentCommand = Parser.commandParser(commandAndParameter[0]);
            currentParameter = commandAndParameter[1];

        } while (!currentCommand.equals(Command.BYE));
        Ui.printStatement("Bye. Hope to see you again soon!");
        System.exit(0);
    }













    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }
}
