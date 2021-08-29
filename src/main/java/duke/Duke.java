package duke;

import duke.command.Command;
import duke.command.Parser;
import duke.command.Storage;
import duke.command.Ui;

import java.util.Scanner;

/**
 * A robot that can respond to user's input, and help user store tasks
 */
public class Duke {
    private Command command;

    /**
     * Constructor of Duke class.
     *
     * @param filePath The filePath of the file storing the data.
     */
    public Duke(String filePath) {
        TaskList taskList = new TaskList();
        Ui ui = new Ui(taskList);
        Storage storage = new Storage(filePath, taskList);
        this.command = new Command(taskList, ui, storage);
    }

    /**
     * Start running the program.
     */
    public void run() {
        //Read events stored in the input List
        command.loadSavedTasks();

        command.showLogo();

        Scanner inputScanner = new Scanner(System.in);
        String task = inputScanner.nextLine();
        while (!Parser.isBye(task)) {
            try {
                switch (Parser.judgeType(task)) {
                case TODO:
                    command.addNewTodo(task);
                    break;
                case EVENT:
                    command.addNewEvent(task);
                    break;
                case DEADLINE:
                    command.addNewDeadline(task);
                    break;
                case DONE:
                    command.setTaskDone(task);
                    break;
                case LIST:
                    command.showList();
                    break;
                case DELETE:
                    command.deleteTask(task);
                    break;
                case FIND:
                    command.findTask(task);
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
        command.showGoodBye();
    }

    public static void main(String[] args) {
        Duke duke = new Duke("duke.txt");
        duke.run();
    }
}
