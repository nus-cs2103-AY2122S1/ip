package duke;

import jdk.jfr.Category;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class DukeTest {
    @Test
    public void dummyTest(){
        assertEquals(2, 2);
    }

    @Test
    public void addTodoTest(){
        TaskList ob = new TaskList("todo read book", Duke.Category.TODO);
        assertEquals("[T][ ] read book",ob.toString());
    }

    @Test
    public void addDeadlineTest(){
        TaskList ob = new TaskList("deadline hw /by 2015-09-18", Duke.Category.DEADLINE);
        assertEquals("[D][ ] hw  (by: Sep 18 2015)",ob.toString());
    }

    @Test
    public void addEventTest(){
        TaskList ob = new TaskList("event concert /at 2018-06-18", Duke.Category.EVENT);
        assertEquals("[E][ ] concert  (at: Jun 18 2018)",ob.toString());
    }



}
