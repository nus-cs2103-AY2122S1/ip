import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Pilcrow is a personal assistant chatbot made for CS2103, based off of Duke.
 */
public class Pilcrow {
    private Ui ui;
    private Storage storage;
    private TaskList taskList;

    private final static String PILCROW_FILE_PATH = "data/pilcrow.txt";

    private Pilcrow() {
        this.ui = new Ui();
        this.storage = new Storage(Pilcrow.PILCROW_FILE_PATH);
        this.taskList = new TaskList();
    }

    /**
     * Runs the main body of the Pilcrow script.
     * @param args
     */
    public static void main(String[] args) {
        Pilcrow pilcrow = new Pilcrow();
        pilcrow.run();
    }

    private void run() {
        Boolean isExit = false;
        System.out.println(this.taskList);
        while (!isExit) {
            Scanner scanner = new Scanner(System.in);
            try {
                Parser parser = new Parser(scanner.nextLine());
                Pilcrow.runCommand(parser.getCommandWord(), parser.getRestOfCommand(),
                        this.ui, this.storage, this.taskList, parser);

                isExit = (parser.getCommandWord().equals("bye"));
            } catch (InvalidInputException exception) {
                ui.printException(exception);
            } catch (IOException exception) {
                ui.printException(exception);
            }
        }
    }

    private static void runCommand(String commandWord, String restOfCommand, Ui ui,
            Storage storage, TaskList taskList, Parser parser) {
        switch (commandWord) {
        // To fix indentation
        case "todo":
            // Fallthrough
        case "deadline":
            // Fallthrough
        case "event":
            Task task = Task.createTask(commandWord, restOfCommand, false);
            taskList.addTask(task);
            ui.printTaskAddedMessage(task, taskList);
            break;
        case "list":
            ui.printTaskList(taskList);
            break;
        case "done":
            int index = parser.getIndex();
            taskList.setTaskIsDone(index, true);
            ui.printSetTaskIsDoneMessage(taskList.getTask(index), taskList);
            break;
        case "delete":
            index = parser.getIndex();
            taskList.deleteTask(index);
            ui.printDeleteTaskMessage(index);
            break;
        case "bye":
            ui.printGoodbyeMessage();
            break;
        default:
            ui.printUnacceptedCommandMessage();
            break;
        }
    }
}