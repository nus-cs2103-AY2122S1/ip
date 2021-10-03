package seedu.duke.storage;

import java.util.ArrayList;

import seedu.duke.tasks.ToDos;

public class StorageAddToDos extends StorageAddTask {

    @Override
    public ToDos execute(String currLine, String[] storageDataArray, String storageIsDone) {
        ArrayList<String> tags = this.addTags(currLine);

        ToDos todos = new ToDos(getDescriptions(storageDataArray), getIsDoneFromStorage(storageIsDone), tags);
        todos = (ToDos) checkAfterTask(currLine, todos);

        return todos;
    }

}
