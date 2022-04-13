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

    private Ui ui;
    private Parser parser;
    private Storage storage;
    private TaskList taskList;
    private boolean canUseFilePath;

    Command(String filePath) {
        this.ui = new Ui();
        this.parser = new Parser();
        this.storage = new Storage(filePath);
        boolean hasCreatedFile = this.storage.hasCreatedFile();
        this.canUseFilePath = true;
        initializeTaskList(hasCreatedFile);
    }

    private void initializeTaskList(boolean hasCreatedFile) {
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
     * Prints the welcome message from Duke and starts taking commands from the user. This
     * is for the original command line version of Duke.
     */
    public void startDuke() {
        if (!this.canUseFilePath) {
            System.out.println("File path is corrupted :( Cannot start Duke.");
            return;
        }
        System.out.println(this.ui.getStartMessage(this.taskList.getTaskList()));
        Scanner scanner = new Scanner(System.in);
        boolean hasNextCommand = true;
        while (hasNextCommand) {
            String command = "";
            if (!scanner.hasNextLine()) {
                break;
            }
            command = scanner.nextLine();
            String action = this.parser.parseCommand(command);
            String reply = getReplyFromDuke(action, command);
            System.out.println(reply);
            assert(reply != null);
            if (reply.equals(this.ui.getSaveMessage())) {
                hasNextCommand = false;
            }
        }
    }

    /**
     * Runs the type of command given in the parameters and returns Duke's
     * reply.
     *
     * @param typeOfCommand String that determines the actions of Duke.
     * @param description   The original command given by the user.
     * @return String of Duke's reply.
     */
    public String getReplyFromDuke(String typeOfCommand, String description) {
        try {
            if (typeOfCommand.equals("save")) {
                return getSaveCommandReply();
            } else if (typeOfCommand.equals("list")) {
                return getListCommandReply();
            } else if (typeOfCommand.equals("done")) {
                return getDoneCommandReply(description);
            } else if (typeOfCommand.equals("delete")) {
                return getDeleteCommandReply(description);
            } else if (typeOfCommand.equals("addTask")) {
                return getAddTaskReply(description);
            } else if (typeOfCommand.equals("empty")) {
                return getEmptyCommandReply();
            } else if (typeOfCommand.equals("find")) {
                return getFindCommandReply(description);
            } else {
                throw new DukeException("Sorry I don't understand this command :(");
            }
        } catch (DukeException e) {
            return e.getMessage() + "\n";
        }
    }

    private String getFindCommandReply(String description) throws DukeException {
        String str = this.parser.parseFindCommand(description);
        ArrayList<Task> matchingTasks = this.taskList.findTask(str);
        assert(this.ui.getMatchingTaskList(matchingTasks) != null);
        return this.ui.getMatchingTaskList(matchingTasks);
    }

    private String getEmptyCommandReply() {
        return this.ui.getMessageForEmptyLineInput();
    }

    private String getAddTaskReply(String description) throws DukeException {
        Task task = this.parser.parseAddTask(description);
        this.taskList.addTask(task);
        return this.ui.getAddedTaskMessage(task) + this.ui.getNumberOfTasksInList(this.taskList);
    }

    private String getDeleteCommandReply(String description) throws DukeException {
        int task = this.parser.parseDeleteCommand(description);
        Task t = this.taskList.deleteTask(task);
        return this.ui.getDeletedTaskMessage(t) + this.ui.getNumberOfTasksInList(this.taskList);
    }

    private String getDoneCommandReply(String description) throws DukeException {
        int task = this.parser.parseDoneCommand(description);
        Task t = this.taskList.markTaskAsDone(task);
        return this.ui.getMarkAsDoneMessage(t);
    }

    private String getListCommandReply() {
        return this.ui.getIterateTaskList(this.taskList.getTaskList());
    }

    private String getSaveCommandReply() throws DukeException {
        this.storage.writeToFile(this.taskList.getTaskList());
        return this.ui.getSaveMessage();
    }

    /**
     * Sets taskList of the Command object to be empty. Use only for
     * testing purposes.
     */
    public void setTaskListEmpty() {
        this.taskList = new TaskList();
    }
}
