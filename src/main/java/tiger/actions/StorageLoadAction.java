package tiger.actions;

import tiger.app.AppState;
import tiger.components.TaskList;
import tiger.constants.Flag;
import tiger.exceptions.storage.TigerStorageInitException;
import tiger.exceptions.storage.TigerStorageLoadException;
import tiger.exceptions.storage.TigerStorageSaveException;
import tiger.storage.Storage;

/**
 * Represents the action of loading the data saved at the start of the app.
 */

public class StorageLoadAction extends Action {

    private AppState applicationState;

    /**
     * Constructor for the {@code StorageLoadAction class}
     *
     * @param applicationState Context of application from which to run the task from.
     */

    public StorageLoadAction(AppState applicationState) {
        this.applicationState = applicationState;
    }

    /**
     * Runs the {@code StorageLoadAction} action.
     *
     * @return an updated {@code AppState}.
     */

    @Override
    public AppState run() {
        String response;
        TaskList taskList;
        switch (this.applicationState.checkFlag()) {
        case STORAGE_FAILED:
            /* This case will happen when we tried and failed to init the storage, and after prompting the user
            whether to wipe or do partial loading, the user responds with an invalid input. */
            return new AppState(new TaskList(), "Please enter Y or N only",
                    Flag.STORAGE_FAILED);
        case STORAGE_WIPE:
            /* Check if the user wants to wipe the storage */
            response = "Hello, I am Tiger, your personal assistant. I have fetched 0 tasks from my memory.";
            try {
                Storage.wipeStorage();
                return new AppState(Storage.load(), response);
            } catch (TigerStorageSaveException e) {
                /* If for whatever reason, we failed to wipe storage, we force wipe storage by creating a new
                TaskList */
                return new AppState(new TaskList(), response);
            }
        case STORAGE_PARTIAL_LOAD:
            /* Checks if the user wants to do partial loading. */
            taskList = Storage.partialLoad();
            response = String.format("Hello, I am Tiger, your personal assistant. I have fetched %d tasks from my "
                    + "memory.", taskList.size());
            return new AppState(taskList, response);
        case STORAGE_NOT_YET_INIT:
            /* Else, load the storage normally for the user. If the file isn't there and we fail creating it, send a
            message asking user to try again. If the file is there and we fail loading the file, send a message back
            asking if the user wants to do partial loading. The user should either respond with {@code Flag
            .STORAGE_WIPE}
            or {@code Flag.STORAGE_PARTIAL_LOAD} */
            try {
                Storage.makeFileIfNotPresent();
            } catch (TigerStorageInitException e) {
                return new AppState(new TaskList(), e.toString());
            }
            try {
                taskList = Storage.load();
                response = String.format("Hello, I am Tiger, your personal assistant. I have fetched %d tasks from my "
                        + "memory.", taskList.size());
                return new AppState(taskList, response);
            } catch (TigerStorageLoadException e) {
                return new AppState(new TaskList(), e.toString(), Flag.STORAGE_FAILED);
            }
        default:
            return new AppState(new TaskList(), Flag.STORAGE_FAILED);
        }
    }
}
