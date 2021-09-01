package duke.storage;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import duke.DukeExceptions;
import duke.tasks.Task;

public class TaskListTest {
    private Task task = new Task("desc", "s");
    private TaskList taskList = new TaskList();

    @Test
    //Test to ensure first mark as finished does not throw exception
    void markAsFinishedTestFirst() {
        taskList.clear();
        taskList.add(task);
        Assertions.assertDoesNotThrow(() -> taskList.markAsFinished(0));
    }

    @Test
    //Test to ensure second mark as finished does throw a DukeException
    void markAsFinishedTestSecond() {
        taskList.clear();
        taskList.add(task);
        try {
            taskList.markAsFinished(0);
        } catch (DukeExceptions e) {
            System.out.println("First test must've failed");
            System.out.println(e.getMessage());
        }
        Assertions.assertThrows(DukeExceptions.class, ()-> taskList.markAsFinished(0));

    }


}
