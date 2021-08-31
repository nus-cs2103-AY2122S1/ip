package uhtredragnarson;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.Test;


public class TaskListTest {
    private final String filePath = "src/main/java/data/UhtredRagnarsonTest.txt";
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
        String userInput = "deadline assignment /by 2021-09-30";
        TaskList taskList = new TaskList();
        String expectedOutput = "[[D][ ] assignment  (by: 30 Sep 2021)]";
        taskList.addDeadlineTask(userInput, new Ui(), storage);
        assertEquals(expectedOutput, taskList.getList().toString());
    }

    @Test
    public void addEventTaskTest() throws IOException {
        String userInput = "event project meeting /at Sep 14th 2-4pm";
        TaskList taskList = new TaskList();
        String expectedOutput = "[[E][ ] project meeting  (at: Sep 14th 2-4pm)]";
        taskList.addEventTask(userInput, new Ui(), storage);
        assertEquals(expectedOutput, taskList.getList().toString());
    }
}
