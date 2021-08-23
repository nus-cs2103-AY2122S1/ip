package tiger.actions;

import tiger.app.AppState;
import tiger.exceptions.storage.TigerStorageSaveException;
import tiger.storage.Storage;

public class StorageSaveAction extends Action {
    private AppState applicationState;

    public StorageSaveAction(AppState applicationState) {
        this.applicationState = applicationState;
    }

    @Override
    public AppState run() throws TigerStorageSaveException {
        Storage.save(this.applicationState.taskList);
        return this.applicationState;
    }
}
