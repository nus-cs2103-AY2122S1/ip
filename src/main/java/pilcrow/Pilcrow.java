package pilcrow;

import java.util.Scanner;

/**
 * Pilcrow is a personal assistant chatbot made for CS2103, based off of Duke.
 */
public class Pilcrow {
    private Ui ui;
    private Storage storage;
    private TaskList taskList;

    private static final String PILCROW_FILE_PATH = "data/pilcrow.txt";

    /**
     * Constructor for Pilcrow object.
     * */
    public Pilcrow() {
        this.ui = new Ui();
        this.storage = new Storage(Pilcrow.PILCROW_FILE_PATH);
        this.taskList = new TaskList(this.storage.load());
    }

    /**
     * Main function for Pilcrow
     * @param args
     */
    public static void main(String[] args) {
        Pilcrow pilcrow = new Pilcrow();
        pilcrow.run();
    }

    /**
     * Runs the main body of the Pilcrow script.
     */
    private void run() {
        Boolean isExit = false;
        Scanner scanner = new Scanner(System.in);

        while (!isExit) {
            try {
                String fullCommand = scanner.nextLine();
                Parser parser = new Parser(fullCommand);
                this.respondToInput(fullCommand);

                isExit = (parser.getCommandWord().equals("bye"));
            } catch (InvalidInputException exception) {
                ui.printException(exception);
            }
        }
        scanner.close();
    }

    /**
     * Responds appropriately given a text input.
     * @param input Text of the input.
     * @return Text of Pilcrow's response.
     */
    public String respondToInput(String input) {
        Parser parser = new Parser(input);
        return Pilcrow.runCommand(parser.getCommandWord(), parser.getRestOfCommand(),
                this.ui, this.storage, this.taskList, parser);
    }

    private static String runCommand(String commandWord, String restOfCommand, Ui ui,
            Storage storage, TaskList taskList, Parser parser) {
        String text;
        switch (commandWord) {
        case "todo":
            // Fallthrough
        case "deadline":
            // Fallthrough
        case "event":
            if (restOfCommand.length() == 0) {
                throw new InvalidInputException("Must specify task name.");
            }
            Task task = Task.createTask(commandWord, restOfCommand, false);
            taskList.addTask(task);
            storage.save(taskList);
            text = ui.printTaskAddedMessage(task, taskList);
            break;
        case "list":
            text = ui.printTaskList(taskList);
            break;
        case "done":
            int index = parser.getIndex();
            taskList.setTaskIsDone(index, true);
            storage.save(taskList);
            text = ui.printSetTaskIsDoneMessage(taskList.getTask(index), taskList);
            break;
        case "search":
            String searchString = parser.getRestOfCommand();
            text = ui.printTaskList(taskList.getFilteredTaskList(searchString));
            break;
        case "delete":
            index = parser.getIndex();
            taskList.deleteTask(index);
            storage.save(taskList);
            text = ui.printDeleteTaskMessage(index);
            break;
        case "bye":
            text = ui.printGoodbyeMessage();
            break;
        default:
            text = ui.printUnacceptedCommandMessage();
            break;
        }
        return text;
    }
}
