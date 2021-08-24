package duke.test;

import duke.DukeException;
import duke.task.TaskList;
import duke.task.TasksEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TaskListTest {
    TaskList taskList;
    private static final int LIMIT = 5;
    TaskListTest() {
        this.taskList = new TaskList(LIMIT);
        for (int i = 0; i < 4; i++) {
            taskList.addTask(TasksEnum.TODO.getTask("Hello World"));
        }
        taskList.markAsDone(1);
    }

    @Test
    void testAddTask() {
        Assertions.assertEquals(4, taskList.size());
        Assertions.assertTrue(taskList.addTask(TasksEnum.DEADLINE.getTask("get a gf /by 9/12/2023")));
        Assertions.assertFalse(taskList.addTask(TasksEnum.EVENT.getTask("hello /at 12:00")),
                "Task list exceeded hard limit");
    }

    @Test
    void testDoubleMarkedAsDone() {
        Assertions.assertFalse(taskList.markAsDone(1));
    }

    @Test
    void testSuccessfulMarkedDone() {
        Assertions.assertTrue(taskList.getTask(1).isDone());
    }

    @Test
    void testEmptyTaskList() {
        TaskList tl = new TaskList(1);
        Assertions.assertThrows(DukeException.class, () -> tl.removeTask(1));
    }
}
