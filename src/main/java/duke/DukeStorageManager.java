
package duke;

import duke.Tasks.BaseTask;

import java.nio.file.Path;
import java.util.ArrayList;

/**
 * This class encapsulates the local storage functions of Duke.
 */
public class DukeStorageManager {

    /**
     * Loads storage file from the provided path.
     * @param savePath the path to the save file.
     */
    public DukeStorageManager(Path savePath) {


    }

    /**
     * Creates a new blank file for use as storage instead of loading.
     */
    public DukeStorageManager() {

    }



    /**
     * Triggers saving of the current state of the task list as a XML file.
     *
     * @param listOfTasks The ArrayList containing the tasks to be saved.
     */
    public void saveCurrentTasks(ArrayList<BaseTask> listOfTasks) {

    }

}