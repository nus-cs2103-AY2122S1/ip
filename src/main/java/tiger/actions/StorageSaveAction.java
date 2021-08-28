package tiger.actions;

import tiger.app.AppState;
import tiger.exceptions.storage.TigerStorageSaveException;
import tiger.storage.Storage;

/**
 * Represents the action of saving the data at the end of each user command.
 */

public class StorageSaveAction extends Action {
    private AppState applicationState;

    public StorageSaveAction(AppState applicationState) {
        this.applicationState = applicationState;
    }

    @Override
    public AppState run() throws TigerStorageSaveException {
        Storage.save(this.applicationState.getTaskList());
        return this.applicationState;
    }
}
