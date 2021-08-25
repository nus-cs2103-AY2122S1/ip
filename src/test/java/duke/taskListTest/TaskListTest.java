package duke.taskListTest;

import duke.parser.DateTimeParser;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import duke.tasklist.TaskList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TaskListTest {
    TaskList testList = new TaskList();

    public void loadTestList(TaskList testList) {
        testList.add(new ToDo("clean room"));
        testList.add(new Deadline("do homework", DateTimeParser.deadlineDateParse("2021-12-31")));
        testList.add(new Event("university fair", DateTimeParser.eventDateTimeParse("2021-11-30 0101")));
    }


    @Test
    public void getTaskTest() {
        loadTestList(testList);
        int index = 1;
        assertEquals(new ToDo("clean room").toString(), testList.get(index - 1).toString());
    }


    @Test
    public void containsTaskTestPositive() {
        loadTestList(testList);
        Task task1 = new ToDo("clean room");
        assertTrue(testList.containsTask(task1));
        Task task2 = new ToDo("read book");
        assertFalse(testList.containsTask(task2));
    }



}
