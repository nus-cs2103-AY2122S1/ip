package duke;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import duke.task.Task;
import duke.task.TaskCreator;

public class StorageTest {
    @Test
    public void testStorage() {
        TaskList taskList = new TaskList();
        Storage storage = new Storage("data/test.txt", "data", taskList);
        try {
            storage.start();
        } catch (IOException e) {
            System.out.println("Error caught.\n" + e);
        }
        Duke duke = new Duke();

        String[] test1 = new String[] {"todo", "dsauihdisad"};
        String[] test2 = new String[] {"deadline", "gergsdfgdsgd", "/by", "1999-08-09"};
        String[] test3 = new String[] {"event", "dsasgfdsfdsfad", "dsadsa", "/at", "2021-01-04"};

        TaskCreator.createTask(Task.Tasks.TODO, test1, storage, taskList);
        TaskCreator.createTask(Task.Tasks.DEADLINE, test2, storage, taskList);
        TaskCreator.createTask(Task.Tasks.EVENT, test3, storage, taskList);
    }

}
