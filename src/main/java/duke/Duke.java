package duke;

import duke.command.InputTypes;
import duke.command.Parser;
import duke.command.Storage;
import duke.command.TaskList;
import duke.command.Ui;

import java.util.Scanner;

/**
 * A robot that can respond to user's input, and help user store tasks
 */
public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private InputTypes t;

    /**
     * Constructor of Duke class.
     *
     * @param filePath The filePath of the file storing the data.
     */
    public Duke(String filePath) {
        taskList = new TaskList();
        ui = new Ui(taskList);
        storage = new Storage(filePath, taskList, ui);
    }

    /**
     * Start running the program.
     */
    public void run() {
        //Read events stored in the input List
        storage.loadSavedTasks();

        ui.showLogo();

        Scanner inputScanner = new Scanner(System.in);
        String task = inputScanner.nextLine();
        while (!Parser.isBye(task)) {
            try {
                switch (Parser.judgeType(task)) {
                case TODO:
                    storage.addNewTodo(task);
                    break;
                case EVENT:
                    storage.addNewEvent(task);
                    break;
                case DEADLINE:
                    storage.addNewDeadline(task);
                    break;
                case DONE:
                    storage.setTaskDone(task);
                    break;
                case LIST:
                    ui.showList();
                    break;
                case DELETE:
                    storage.deleteTask(task);
                    break;
                case FIND:
                    storage.findTask(task);
                    break;
                case UNKNOWN:
                    throw new Exception("Cannot Understand");
                }
            } catch (Exception e) {
                if (e.getMessage().equals("todoEmpty")) {
                    System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
                } else if (e.getMessage().equals("Cannot Understand")) {
                    System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
                e.printStackTrace();
            } finally {
                task = inputScanner.nextLine();
            }
        }
        ui.showGoodBye();
    }

    public static void main(String[] args) {
        Duke duke = new Duke("duke.txt");
        duke.run();
    }
}
