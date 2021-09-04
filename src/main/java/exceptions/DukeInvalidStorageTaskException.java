package exceptions;

/**
 * Duke stores previously added tasks into the local storage by saving it into a text file.
 * This exception occurs when duke tries to load the task from the text file but is unable
 * to read and load a particular task.
 */
public class DukeInvalidStorageTaskException extends Exception {

    /**
     * Creates a DukeInvalidStorageTaskException telling the user that there was an error while
     * trying to load a saved task from local memory.
     */
    public DukeInvalidStorageTaskException() {
        super("Unable to load some saved tasks from local storage. The file may have been edited previously."
                + "Please check the taskList.txt file. Delete it if necessary.");
    }

}
