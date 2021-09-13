package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.exception.DukeNoSuchTask;
import duke.exception.DukeUnableLoadTask;
import duke.task.Task;
import duke.task.TaskList;

import java.io.File;
import java.io.IOException;

/**
 * Represents a Duke chatbot that can add tasks
 * to users' to-do list.
 *
 * @author Ne Zhijian, Didymus A0218159Y
 */
public class Duke {
    private boolean isOpen;
    private TaskList listOfTasks;
    private Storage storage;
    private static final String FILE_PATH = "./data/duke.txt";

    /**
     * Constructor for Duke chatbot.
     */
    public Duke() throws Exception {
        this.listOfTasks = TaskList.makeNewTaskList();
        this.storage = new Storage(Duke.FILE_PATH);

        createFileIfNoFile();
        this.loadSavedTasks();
    }

    /**
     * Opens and starts the chatbot.
     */
    public void openDukeChatBot() {
        this.isOpen = true;
    }

    /**
     * Closes the chatbot.
     */
    public void closeDukeChatBot() {
        this.isOpen = false;
    }

    /**
     * Takes the task from user input and adds it to the
     * task list.
     * @param item item to be added into the task list.
     * @return item in the type Task.
     */
    public Task addTaskToList(String item) {
        assert !item.equals("") : "description of task cannot be empty";
        Task task = Task.createTask(item);
        this.listOfTasks.addTaskToList(task);
        return task;
    }

    /**
     * Returns the task list.
     * @return task list.
     */
    public TaskList getTaskList() {
        return this.listOfTasks;
    }

    /**
     * Sets the task at the specified index to be done.
     * @param i index of the task that is done.
     */
    public void setTaskAsDone(int i) {
        this.listOfTasks.setTaskAsDone(i);
    }

    /**
     * Removes task specified by the index from task list.
     * @param i index of the task that is to be removed.
     * @return task that is removed.
     * @throws DukeNoSuchTask
     */
    public Task deleteTask(int i) throws DukeNoSuchTask {
        return this.listOfTasks.deleteTask(i);
    }

    /**
     * Saves the tasks into the storage file.
     * @throws IOException if there is an error in writing the file.
     */
    public void saveTasks() throws IOException {
        this.storage.saveTasks(this.listOfTasks);
    }

    /**
     * Loads tasks from the storage file into the given Duke chatbot.
     * @throws IOException if there is an error in reading the file.
     * @throws DukeUnableLoadTask if there is a corruption in tasks of the file.
     */
    public void loadSavedTasks() throws IOException, DukeUnableLoadTask {
        this.storage.loadSavedTasks(this);
    }

    /**
     * Finds the task from task list with matching keyword.
     * @param keyword keyword that user wishes to find.
     * @return task that contains the keyword.
     */
    public TaskList findTasks(String keyword) {
        TaskList tasksFound = this.listOfTasks.findTasks(keyword);
        return tasksFound;
    }

    private void createFileIfNoFile() {
        File output = new File(this.FILE_PATH);

        if (!output.isFile()) {
            output.getParentFile().mkdirs(); // if user does not have existing file path
            try {
                output.createNewFile();
            } catch (IOException e) {
                throw new DukeException(e.getMessage(), e);
            }
        }
    }

    private String parseUserInput(String input) throws IOException, DukeException {
        String userInput = input.strip();
        Command toExecute = Parser.parse(userInput, this);
        return toExecute.execute(this.listOfTasks);
    }

    /**
     * Gets the response from the duke based on user input.
     * @param input user input into the textfield.
     * @return duke response based on user input.
     * @throws DukeException if user input violates given rules.
     */
    public String getResponse(String input) throws DukeException {
        if (!this.isOpen) {
            return "PROBLEM";
        }

        createFileIfNoFile();

        try {
            return parseUserInput(input);
        } catch (DukeException | IOException e) {
            throw new DukeException(e.getMessage(), e);
        }

    }

    /**
     * Returns the greeting message for initialising Duke.
     * @return greeting message.
     */
    public static String getGreeting() {
        return GUI.sendOpeningMessage();
    }

    /**
     * Sorts tasks by their deadline or event occurring time.
     */
    public void sortTasks() {
        this.listOfTasks.sortListOfTasks();
    }
}

