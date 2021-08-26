package duke.storage;

import duke.tasks.Task;
import duke.DukeExceptions;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class TaskListTest {
    Task task = new Task("desc", "s");
    TaskList taskList= new TaskList();

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
