package duke;

import duke.exceptions.DukeException;
import duke.exceptions.DukeTodoTimeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class TasklistTest {

    @Test
    public void testTodoUpdateWithTime() throws DukeException {
        Tasklist tempTaskList = new Tasklist();
        tempTaskList.add(new ToDo("testing"));
        assertThrows(DukeTodoTimeException.class, () -> tempTaskList.updateTime(0,"Sep 09 2019"));
    }
}
