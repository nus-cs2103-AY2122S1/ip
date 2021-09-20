package uhtredragnarson;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import uhtredragnarson.util.Storage;
import uhtredragnarson.util.TaskList;
import uhtredragnarson.util.Ui;


public class TaskListTest {
    private final String filePath = "data/UhtredRagnarsonTest.txt";
    private final Storage storage = new Storage(filePath);

    @Test
    public void addTodoTaskTest() throws IOException {
        String userInput = "todo borrow books";
        TaskList taskList = new TaskList();
        String expectedOutput = "[[T][ ] borrow books]";
        taskList.addTodoTask(userInput, new Ui(), storage);
        assertEquals(expectedOutput, taskList.getList().toString());
    }

    @Test
    public void addDeadlineTaskTest() throws IOException {
        String userInput = "deadline assignment /by 2021-09-30 06:30";
        TaskList taskList = new TaskList();
        String expectedOutput = "[[D][ ] assignment  (by: 30 Sep 2021 06:30)]";
        taskList.addDeadlineTask(userInput, new Ui(), storage);
        assertEquals(expectedOutput, taskList.getList().toString());
    }

    @Test
    public void addEventTaskTest() throws IOException {
        String userInput = "event project meeting /at 2021-09-14 20:00 to 22:00";
        TaskList taskList = new TaskList();
        String expectedOutput = "[[E][ ] project meeting  (at: 14 Sep 2021 20:00-22:00)]";
        taskList.addEventTask(userInput, new Ui(), storage);
        assertEquals(expectedOutput, taskList.getList().toString());
    }
}
