package duke.tasklisttest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import duke.parser.DateTimeParser;
import duke.tasklist.TaskList;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.ToDo;


public class TaskListTest {
    private TaskList testList = new TaskList();

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
    public void containsTaskTest() {
        loadTestList(testList);
        Task task1 = new ToDo("clean room");
        assertTrue(testList.containsTask(task1));
        Task task2 = new ToDo("read book");
        assertFalse(testList.containsTask(task2));
    }

}
