package lawbringer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void addTodoTaskTest() {
        String userInput = "todo borrow books";
        TaskList taskList = new TaskList();
        String expectedOutput = "[[T][ ] borrow books]";
        taskList.addTodoTask(userInput, new Ui());
        assertEquals(expectedOutput, taskList.getList().toString());
    }

    @Test
    public void addDeadlineTaskTest() {
        String userInput = "deadline assignment /by 2021-09-30";
        TaskList taskList = new TaskList();
        String expectedOutput = "[[D][ ] assignment  (by: 30 Sep 2021)]";
        taskList.addDeadlineTask(userInput, new Ui());
        assertEquals(expectedOutput, taskList.getList().toString());
    }

    @Test
    public void addEventTaskTest() {
        String userInput = "event project meeting /at Sep 14th 2-4pm";
        TaskList taskList = new TaskList();
        String expectedOutput = "[[E][ ] project meeting  (at: Sep 14th 2-4pm)]";
        taskList.addEventTask(userInput, new Ui());
        assertEquals(expectedOutput, taskList.getList().toString());
    }
}
