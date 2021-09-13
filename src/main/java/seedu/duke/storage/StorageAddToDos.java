package seedu.duke.storage;

import seedu.duke.tasks.AfterTask;
import seedu.duke.tasks.ToDos;

public class StorageAddToDos extends StorageAddTask {

    @Override
    public ToDos execute(String currLine, String[] storageDataArray, String storageIsDone) {
        ToDos todos = new ToDos(getDescriptions(storageDataArray), getIsDoneFromStorage(storageIsDone));
        if (currLine.contains(" | after")) {
            String afterTaskDescription = currLine.split(" \\| after ")[1];
            todos.setAfterTask(new AfterTask(afterTaskDescription));
        }
        return todos;
    }

}
