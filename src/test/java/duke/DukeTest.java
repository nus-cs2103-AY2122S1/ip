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
    public void addTask_todo_addedTodo(){
        Task ob = new Task("todo read book", Duke.Category.TODO);
        assertEquals("[T][ ] read book",ob.toString());
    }

    @Test
    public void addTask_deadline_addedDeadline(){
        Task ob = new Task("deadline hw /by 2015-09-18", Duke.Category.DEADLINE);
        assertEquals("[D][ ] hw  (by: Sep 18 2015)",ob.toString());
    }

    @Test
    public void addTask_event_addedEvent(){
        Task ob = new Task("event concert /at 2018-06-18", Duke.Category.EVENT);
        assertEquals("[E][ ] concert  (at: Jun 18 2018)",ob.toString());
    }



}
