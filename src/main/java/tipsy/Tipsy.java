package tipsy;

public class Tipsy {

    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    /**
     * Class constructor.
     * Sets save file path to "./data/tipsy.txt" by default.
     */
    public Tipsy() {
        this("data/tipsy.txt");
    }

    /**
     * Class constructor specifying the save file's path.
     *
     * @param filepath The file path to the save file for Tipsy.
     */
    public Tipsy(String filepath) {
        ui = new Ui();

        try {
            setStoragePath(filepath);
        } catch (TipsyException e) {
            tasks = new TaskList();
        }
    }

    /**
     * Returns the message informing that Tipsy has started up.
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
     * Gets the response from Tipsy to the command inputted by the user.
     *
     * @return A String object containing Tipsy's response.
     */
    public String getResponse(String userInput) {
        try {
            return executeCommand(userInput);
        } catch (TipsyException e) {
            return ui.printErrorMessage(e.getMessage());
        }
    }

    private String executeCommand(String userInput) throws TipsyException {
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
        case SHOW_PATH:
            return ui.printCurrentStoragePath(storage);
        case SET_PATH:
            return setNewStoragePath(Parser.parseNewPath(userInput));
        default:
            throw new UnsupportedOperationException(); // Error
        }
        assert false; // Execution should not reach this point
        return "";
    }

    private String addNewTask(Task newTask) {
        assert newTask != null; // Task to be added cannot be null
        tasks.addTask(newTask);
        storage.saveTasks(tasks);
        return ui.printAddTask(tasks, newTask);
    }

    private String completeTask(int taskNum) {
        // TaskNum should be within 1 to the size of the TaskList
        assert taskNum > 0 && taskNum <= tasks.getSize() : taskNum;
        tasks.completeTask(taskNum);
        storage.saveTasks(tasks);
        return ui.printCompleteTask(tasks.getTask(taskNum));
    }

    private String deleteTask(int taskNum) {
        // TaskNum should be within 1 to the size of the TaskList
        assert taskNum > 0 && taskNum <= tasks.getSize() : taskNum;
        Task deletedTask = tasks.deleteTask(taskNum);
        storage.saveTasks(tasks);
        return ui.printDeleteTask(tasks, deletedTask);
    }

    private String findTask(String subject) {
        return ui.printTasksWithSubject(tasks, subject);
    }

    /**
     * Sets a new save file location given a new file path and loads any saved
     * TaskList from the given location, or otherwise starts a new TaskList.
     *
     * @param newPath A String object describing the new path of the save file.
     *                The path should be the relative path from the working
     *                directory of the program.
     * @throws LoadingException If an IOException occurs while loading the new
     *                save file.
     */
    private void setStoragePath(String newPath) throws LoadingException {
        Storage newStorage = new Storage(newPath);
        TaskList newTasks = new TaskList(newStorage.load());

        storage = newStorage;
        tasks = newTasks;
    }

    private String setNewStoragePath(String newPath) {
        try {
            setStoragePath(newPath);
            return ui.printNewStoragePath(storage, tasks);
        } catch (TipsyException e) {
            return ui.printErrorMessage(e.getMessage());
        }
    }
}
