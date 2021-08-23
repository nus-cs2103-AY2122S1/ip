package duke;

import duke.commands.ListCommand;
import duke.tasktypes.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {
    @Test
    public void dummyTest(){
        assertEquals(2, 2);
    }

    @Test
    public void ToDo_Task_createTodo() {
        Task toDo = new ToDos("paint on book");
        toDo.markAsDone();
        assertEquals(toDo.toString(), "[T][X] paint on book");
    }

    @Test
    public void Event_Task_createEvent() {
        Task event = new Events("birthday party", "2001-01-01");
        event.markAsDone();
        assertEquals(event.toString(), "[E][X] birthday party (at: Jan 1 2001)");
    }

    @Test
    public void DeadLine_Task_createDeadline() {
        Deadlines deadline = new Deadlines("exam book", "2021-12-31");
        assertEquals(deadline.toString(), "[D][ ] exam book (by: Dec 31 2021)");
    }

}
