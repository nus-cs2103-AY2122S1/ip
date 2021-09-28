package duke.main;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Represents testing the TaskList class.
 */
public class TaskListTest {
    private TaskList tasks;

    /**
     * Tests the findTasksMatchingDate method.
     */
    @Test
    public void findMatchingTasks() {
        tasks = new TaskList();
        try {
            tasks.add(new Deadline("deadline submit report by 30/08/2021"));
            tasks.add(new Event("event attend workshop at 03/09/2021"));
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
        TaskList matchingTasks = tasks.findMatchingTasks("Sep 03");
        assertEquals(matchingTasks.getNumTasks(), 1);
    }

    /**
     * Tests the markDone method.
     */
    @Test
    public void markDone() {
        tasks = new TaskList();
        try {
            tasks.add(new Deadline("deadline submit report by 30/08/2021"));
            tasks.add(new Event("event attend workshop at 03/09/2021"));
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
        Task taskMarkedDone = tasks.markDone(1);
        assertEquals(taskMarkedDone.toString(), "[E][X] attend workshop (at: Sep 03)");
    }

    public static void main(String[] args) {

    }
}
