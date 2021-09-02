package duke;

public class Duke {

    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    /**
     * Class constructor.
     * Sets save file path to "./data/duke.txt" by default.
     */
    public Duke() {
        this("data/duke.txt");
    }

    /**
     * Class constructor specifying the save file's path.
     *
     * @param filepath The file path to the save file for Duke.
     */
    public Duke(String filepath) {
        ui = new Ui();
        storage = new Storage(filepath);

        try {
            tasks = new TaskList(storage.load());
            if (tasks.getSize() > 0) {
                ui.printLoadTasks(tasks);
            }
        } catch (DukeException e) {
            ui.printErrorMessage(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Returns the message informing that Duke has started up.
     *
     * @return A String object containing the start-up message.
     */
    public String getStartUpMessage() {
        return ui.printStartInteractionsMessage();
    }

    /**
     * Returns a printout of loaded tasks from the save file, if any.
     *
     * @return A String object containing a printout of the loaded tasks
     *           or an empty String object if no tasks were loaded.
     */
    public String getTasksLoadedMessage() {
        if (tasks.getSize() > 0) {
            return ui.printLoadTasks(tasks);
        } else {
            return "";
        }
    }

    /**
     * Gets the response from Duke to the command inputted by the user.
     *
     * @return A String object containing Duke's response.
     */
    public String getResponse(String userInput) {
        try {
            return executeCommand(userInput);
        } catch (DukeException e) {
            return ui.printErrorMessage(e.getMessage());
        }
    }

    private String executeCommand(String userInput) throws DukeException {
        // First, extract the command type inputted by the user.
        // parseCommandType() will throw an UnsupportedOperationException if
        // no valid command types was inputted by the user.
        CommandType commandType = Parser.parseCommandType(userInput);

        // Decide the action to take depending on command type given.
        // parseNewTask() and parseTaskNum() will throw MissingInputException
        // if no valid further input is entered by the user.
        switch (commandType) {
        case EXIT:
            System.exit(0);
            break;
        case LIST:
            return ui.printTaskList(tasks);
        case ADD_TASK:
            return addNewTask(Parser.parseNewTask(userInput));
        case COMPLETE_TASK:
            return completeTask(Parser.parseTaskNum(userInput));
        case DELETE_TASK:
            return deleteTask(Parser.parseTaskNum(userInput));
        case FIND_TASK:
            return findTask(Parser.parseSearchSubject(userInput));
        default:
            throw new UnsupportedOperationException(); // Error
        }
        return "";
    }

    private String addNewTask(Task newTask) {
        tasks.addTask(newTask);
        storage.saveTasks(tasks);
        return ui.printAddTask(tasks, newTask);
    }

    private String completeTask(int taskNum) {
        tasks.completeTask(taskNum);
        storage.saveTasks(tasks);
        return ui.printCompleteTask(tasks.getTask(taskNum));
    }

    private String deleteTask(int taskNum) {
        Task deletedTask = tasks.deleteTask(taskNum);
        storage.saveTasks(tasks);
        return ui.printDeleteTask(tasks, deletedTask);
    }

    private String findTask(String subject) {
        return ui.printTasksWithSubject(tasks, subject);
    }
}
