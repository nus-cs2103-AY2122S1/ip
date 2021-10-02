package seedu.duke.storage;

import java.util.ArrayList;

import seedu.duke.tasks.AfterTask;
import seedu.duke.tasks.ToDos;

public class StorageAddToDos extends StorageAddTask {

    @Override
    public ToDos execute(String currLine, String[] storageDataArray, String storageIsDone) {
        ArrayList<String> tags = addTags(currLine);
        ToDos todos = new ToDos(getDescriptions(storageDataArray), getIsDoneFromStorage(storageIsDone), tags);
        if (currLine.contains(" | after")) {
            String afterTaskDescription = currLine.split(" \\| after ")[1];
            todos.setAfterTask(new AfterTask(afterTaskDescription));
        }
        return todos;
    }

}
