import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import tipsy.CommandType;
import tipsy.MissingInputException;
import tipsy.Parser;
import tipsy.Task;
import tipsy.TaskList;
import tipsy.TipsyException;
import tipsy.ToDo;
import tipsy.UnsupportedOperationException;

public class TipsyTest {

    @Test
    public void taskListTest() {
        TaskList taskList = new TaskList();
        assertEquals(taskList.getSize(), 0);

        Task task1 = new ToDo("test task 1");
        Task task2 = new ToDo("test task 2");
        taskList.addTask(task1);
        taskList.addTask(task2);
        assertEquals(taskList.getSize(), 2);
        assertEquals(taskList.getTask(1), task1);

        taskList.completeTask(1);
        assertEquals(taskList.getTask(1).isDone(), true);
        assertEquals(taskList.getTask(2).isDone(), false);

        taskList.deleteTask(1);
        assertEquals(taskList.getSize(), 1);
        assertEquals(taskList.getTask(1), task2);
    }

    @Test
    public void parserTestCommands() {
        try {
            assertEquals(Parser.parseCommandType("ByE   "), CommandType.EXIT);
            assertEquals(Parser.parseCommandType("DeadLine :((( "), CommandType.ADD_TASK);
            assertEquals(Parser.parseCommandType(" delete afeascs"), CommandType.DELETE_TASK);
            assertThrows(UnsupportedOperationException.class, () -> Parser.parseCommandType(" bleh"));
        } catch (TipsyException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void parserTestTasks() {
        assertThrows(MissingInputException.class, () -> Parser.parseNewTask("deadline "));

        assertEquals(Parser.parseTaskNum("deleTe         23"), 23);
        assertEquals(Parser.parseTaskNum(" dOnE 3"), 3);
    }
}
