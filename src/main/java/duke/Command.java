package duke;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Determines the action of a Duke object. A Command object is initialized with the same file path
 * as a Duke object, and it reads the file in the file path if it exists and creates the file if it does not exists
 * at initialization.
 */
public class Command {

    Ui ui;
    Parser parser;
    Storage storage;
    TaskList taskList;
    boolean canUseFilePath;

    Command(String filePath) {
        this.ui = new Ui();
        this.parser = new Parser();
        this.storage = new Storage(filePath);
        boolean hasCreatedFile = this.storage.hasCreatedFile();
        this.canUseFilePath = true;
        if (!hasCreatedFile) {
            //file is read or an error has occurred when creating the file
            try {
                ArrayList<Task> tasks = this.storage.readFile();
                this.taskList = new TaskList(tasks);
            } catch (FileNotFoundException e) {
                this.canUseFilePath = false;
                this.taskList = new TaskList();
            } catch (DukeException e) {
                System.out.println(e);
                System.out.println("A new todo list is created for you.");
                this.taskList = new TaskList();
            }
        } else {
            this.taskList = new TaskList();
        }
    }

    /**
     * Prints the welcome message from Duke and starts taking commands from the user.
     */
    public void startDuke() {
        if (!this.canUseFilePath) {
            System.out.println("File path is corrupted :( Cannot start Duke.");
            return;
        }
        this.ui.printStartMessage(this.taskList.getTaskList());
        Scanner scanner = new Scanner(System.in);
        boolean hasNextCommand = true;
        while (hasNextCommand) {
            String command = scanner.nextLine();
            String action = this.parser.parseCommand(command);
            hasNextCommand = runNextCommand(action, command);
        }
    }

    /**
     * Runs the type of command given in the parameters and returns whether Duke
     * should be stop or not.
     *
     * @param typeOfCommand String that determines the actions of Duke.
     * @param description   The original command given by the user.
     * @return True if Duke should continue to take in commands from the user and false if Duke should stop.
     */
    public boolean runNextCommand(String typeOfCommand, String description) {
        try {
            if (typeOfCommand.equals("bye")) {
                this.ui.endMessage();
                this.storage.writeToFile(this.taskList.getTaskList());
                return false;
            } else if (typeOfCommand.equals("list")) {
                this.ui.iterateTaskList(this.taskList.getTaskList());
                return true;
            } else if (typeOfCommand.equals("done")) {
                int task = this.parser.parseDoneCommand(description);
                this.taskList.markAsDone(task);
                return true;
            } else if (typeOfCommand.equals("delete")) {
                int task = this.parser.parseDeleteCommand(description);
                this.taskList.deleteTask(task);
                return true;
            } else if (typeOfCommand.equals("addTask")) {
                Task task = this.parser.parseAddTask(description);
                this.taskList.addTask(task);
                return true;
            } else if (typeOfCommand.equals("empty")) {
                System.out.println("Please enter a new task or action.");
                return true;
            } else {
                throw new DukeException("Sorry I don't understand this command :(");
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            System.out.println();
            return true;
        }
    }

    /**
     * Sets taskList of the Command object to be empty. Use only for
     * testing purposes.
     */
    public void setTaskListEmpty() {
        this.taskList = new TaskList();
    }
}
