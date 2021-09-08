import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.AbstractMap;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;
import duke.exception.InvalidTaskNumberException;
import duke.task.TaskList;
import duke.task.TasksEnum;
import duke.task.Todo;
import stub.TaskStub;

class TaskListTest {
    private static final int LIMIT = 5;
    private final TaskList taskList;

    TaskListTest() {
        taskList = new TaskList(LIMIT);
        for (int i = 0; i < 4; i++) {
            taskList.addTask(TasksEnum.TODO.getTask("Hello World"));
        }
        taskList.markAsDone(1);
    }

    @Test
    void testAddTask() {
        assertEquals(4, taskList.size());
        taskList.addTask(new TaskStub("", null));
        assertThrows(InvalidTaskNumberException.class, () ->
                taskList.addTask(TasksEnum.DEADLINE.getTask("get a gf /by 9/12/2023")),
            "Task list exceeded hard limit");
    }

    @Test
    void testDoubleMarkedAsDone() {
        assertFalse(taskList.markAsDone(1));
    }

    @Test
    void testSuccessfulMarkedDone() {
        assertTrue(taskList.getTask(1).isDone());
    }

    @Test
    void testEmptyTaskList() {
        TaskList tl = new TaskList(1);
        assertThrows(DukeException.class, () -> tl.removeTask(1));
    }

    @Test
    void testUpcomingExpired() {
        TaskList tl = new TaskList(9);
        tl.addTask(new TaskStub("First", LocalDateTime.of(2004, 12, 31, 19, 21)));
        tl.addTask(new TaskStub("Second", LocalDateTime.of(2002, 12, 31, 19, 21)));
        tl.addTask(new TaskStub("Third", LocalDateTime.of(2003, 11, 30, 19, 21)));
        tl.addTask(new TaskStub("Fourth", LocalDateTime.of(2003, 12, 2, 19, 21)));
        tl.addTask(new TaskStub("Fifth", LocalDateTime.of(2003, 12, 31, 19, 20)));
        tl.addTask(new TaskStub("Sixth", LocalDateTime.of(2003, 12, 31, 19, 21)));
        tl.addTask(new TaskStub("Seventh", LocalDateTime.of(2003, 10, 31, 19, 21)));
        tl.addTask(new TaskStub("Eighth", LocalDateTime.of(2003, 12, 31, 19, 21)));
        assertEquals(0, tl.getUpcomingTasks().size());
        tl.addTask(new Todo("Zeroth"));
        assertEquals(1, tl.getUpcomingTasks().size());
    }

    @Test
    void testUpcomingDone() {
        TaskList tl = new TaskList(9);
        tl.addTask(new Todo("Zeroth")); // 8
        tl.addTask(new TaskStub("First", LocalDateTime.of(2024, 12, 31, 19, 21))); // 7
        tl.addTask(new TaskStub("Second", LocalDateTime.of(2022, 12, 31, 19, 21))); // 0
        tl.addTask(new TaskStub("Third", LocalDateTime.of(2023, 11, 30, 19, 21))); // 2
        tl.addTask(new TaskStub("Fourth", LocalDateTime.of(2023, 12, 2, 19, 21))); // 3
        tl.addTask(new TaskStub("Fifth", LocalDateTime.of(2023, 12, 31, 19, 20))); // 4
        tl.addTask(new TaskStub("Sixth", LocalDateTime.of(2023, 12, 31, 19, 21))); // 5
        tl.addTask(new TaskStub("Seventh", LocalDateTime.of(2023, 10, 31, 19, 21))); // 1
        tl.addTask(new TaskStub("Eighth", LocalDateTime.of(2023, 12, 31, 19, 21))); // 6
        tl.getTask(5).markAsDone();
        assertFalse(
            tl.getUpcomingTasks().stream().map(AbstractMap.SimpleImmutableEntry::getValue).anyMatch(x -> x == 5),
            "Task 5 is still in the upcoming");
        assertEquals(8, tl.getUpcomingTasks().size(), "Wrong size of task.");
        assertEquals(7, tl.getUpcomingTasks().get(4).getValue(), "Task 7 should be in position 4");
        assertEquals(1, tl.getUpcomingTasks().get(7).getValue(), "Todo item must be last");
    }

    @Test
    void testTaskFinder() {
        TaskList tl = new TaskList(5);
        tl.addTask(new Todo("One: hello 123"));
        tl.addTask(new Todo("Two: bye 123"));
        tl.addTask(new Todo("Three: hello bye 123"));
        tl.addTask(new Todo("Four: lo bye"));
        tl.addTask(new Todo("Five: hey"));
        assertEquals("Two: bye 123", tl.getTasksContaining("by").get(0).getKey().getDescription());
        assertEquals("Five: hey", tl.getTasksContaining(" hEy").get(0).getKey().getDescription());
        assertEquals(0, tl.getTasksContaining("ok").size());
    }
}
