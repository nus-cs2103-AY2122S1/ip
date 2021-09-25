package saber;

import java.util.ArrayList;

import saber.commands.SaberCommand;
import saber.exceptions.SaberCommandNotFoundException;
import saber.exceptions.SaberStorageLoadException;
import saber.exceptions.SaberStorageStoreException;
import saber.parser.SaberParser;
import saber.storage.TaskStorage;
import saber.task.Deadline;
import saber.task.Event;
import saber.task.Todo;
import saber.tasklist.TaskList;
import saber.ui.SaberUI;

/***
 *  Represents the Saber application
 */
public class Saber {

    /** The storage of the Saber application that handles the loading and saving of the tasks to
     * the hard disk. Tasks are only loaded from hard disk at the beginning of the application and saved to the hard
     * disk when the application ends */
    private final TaskStorage storage;

    /** The UI that handles general responses of Saber */
    private final SaberUI saberGeneralUI;

    /** The list that stores the tasks when the Saber application is running */
    private TaskList taskList;

    private boolean hasInitializationError = false;

    /** The enum that represents all the possible command to Saber application */
    public enum InputCommand {
        add,
        bye,
        done,
        deadline,
        delete,
        event,
        find,
        todo,
        list,
        sort,
    }

    /** The enum that represents all the task types that are supported by the Saber application */
    public enum TaskType {
        deadline,
        event,
        normalTask,
        todo,
    }

    /**
     * Constructs Saber. Takes in a filepath for TaskStorage to know which file
     * to load the tasks from to TaskList and to save to from the TaskList
     *
     * @param filePath the file path where the tasks will be saved to (the tasks will be saved at
     *                 filePath/data/tasks.json)
     */
    public Saber(String filePath) {
        saberGeneralUI = new SaberUI();
        storage = new TaskStorage(filePath);

        try {
            this.taskList = new TaskList(storage.load());
        } catch (SaberStorageLoadException e) {
            saberGeneralUI.showStorageLoadingError();
            hasInitializationError = true;
            this.taskList = new TaskList(new ArrayList<>());
        }
    }

    /***
     * Stores the entire existing TaskList to the hard disk
     * (to the file specified by the filepath)
     */
    public void storeExistingTaskList() throws SaberStorageStoreException {
        TaskType[] taskTypeArray = new TaskType[taskList.size()];
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i) instanceof Deadline) {
                taskTypeArray[i] = TaskType.deadline;
            } else if (taskList.get(i) instanceof Event) {
                taskTypeArray[i] = TaskType.event;
            } else if (taskList.get(i) instanceof Todo) {
                taskTypeArray[i] = TaskType.todo;
            } else {
                taskTypeArray[i] = TaskType.normalTask;
            }
        }
        storage.store(taskList, taskTypeArray);
    }

    /***
     * Runs the Saber application
     */
    public void run() {
        boolean isEnd = false;
        saberGeneralUI.showLogo();
        saberGeneralUI.showGreeting();
        while (!isEnd) {
            Runtime.getRuntime().addShutdownHook(new Thread(() -> storeExistingTaskList()));
            try {
                String input = saberGeneralUI.readInput();
                SaberParser saberParser = new SaberParser(input);
                saberGeneralUI.showLineBreak();

                SaberCommand command = saberParser.parse();
                command.execute(taskList);

                isEnd = command.isExit();
            } catch (SaberCommandNotFoundException e) {
                saberGeneralUI.showCommandNotFoundError();
            } catch (NullPointerException e) {
                saberGeneralUI.showGenericError();
            } finally {
                saberGeneralUI.showLineBreak();
            }
        }
        try {
            storeExistingTaskList();
        } catch (SaberStorageStoreException e) {
            saberGeneralUI.showStorageStoringError();
        }
    }

    /**
     * Gets saber responses
     *
     * @param input user input
     * @return saber response to the input
     */
    public String getResponse(String input) {
        SaberParser saberParser = new SaberParser(input);
        try {
            SaberCommand command = saberParser.parse();
            String response = command.getResponse(taskList);
            storeExistingTaskList();
            return response;
        } catch (SaberCommandNotFoundException e) {
            return saberGeneralUI.getCommandNotFoundError();
        } catch (NullPointerException e) {
            return saberGeneralUI.getGenericError();
        } catch (SaberStorageStoreException e) {
            return saberGeneralUI.getStorageStoringError();
        }
    }

    /**
     * Gets initialization error
     *
     * @return initialization error
     */
    public boolean getHasInitializationError() {
        return hasInitializationError;
    }

    /***
     * The main function
     *
     * @param args the arguments of the main function when running the program
     */
    public static void main(String[] args) {
        new Saber(System.getProperty("user.dir")).run();
    }
}
