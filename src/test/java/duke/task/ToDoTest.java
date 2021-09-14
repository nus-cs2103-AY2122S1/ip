package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ToDoTest {
    @Test
    public void encodeTaskForStorage_notDoneTodoTask_encodedString() {
        assertEquals("T | 0 | testingTodoTaskOne",
                new ToDo("testingTodoTaskOne", false).encodeTaskForStorage());
    }

    @Test
    public void encodeTaskForStorage_doneTodoTask_encodedString() {
        assertEquals("T | 1 | testingTodoTaskTwo",
                new ToDo("testingTodoTaskTwo", true).encodeTaskForStorage());
    }

    @Test
    public void toString_notDoneTodoTask_string() {
        assertEquals("[T][ ] testingTodoTaskThree",
                new ToDo("testingTodoTaskThree", false).toString());
    }

    @Test
    public void toString_doneTodoTask_string() {
        assertEquals("[T][X] testingTodoTaskFour",
                new ToDo("testingTodoTaskFour", true).toString());
    }
}
