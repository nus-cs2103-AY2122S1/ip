package petal.components;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import petal.task.Deadline;
import petal.task.Event;
import petal.task.Task;
import petal.task.ToDo;

public class TaskTest {

    private final Task todo = new ToDo("go for a run", false);
    private final Task deadline = new Deadline("go for a run", "2/12/2021 1800", false);
    private final Task event = new Event("go for a run", "2/12/2021 1800 2100", false);

    @Test
    public void isTimeable_todo_false() {
        assertFalse(todo.isTimeable());
    }

    @Test
    public void isTimeable_deadline_true() {
        assertTrue(deadline.isTimeable());
    }

    @Test
    public void isTimeable_event_true() {
        assertTrue(event.isTimeable());
    }

    @Test
    public void keywordPresent_run_true() {
        assertTrue(todo.isKeyWordPresent("run"));
    }

    @Test
    public void keywordPresent_go_false() {
        assertTrue(todo.isKeyWordPresent("go"));
    }

    @Test
    public void getStatusIcon_noInput_whiteSpace() {
        assertEquals(" ", todo.getStatusIcon());
    }

    @Test
    public void formatStrForSavingTodo_noInput_formattedSaving() {
        assertEquals("T| |go for a run", todo.formatStrForSaving());
    }

    @Test
    public void formatStrForSavingDeadline_noInput_formattedSaving() {
        assertEquals("D| |go for a run|2/12/2021 1800", deadline.formatStrForSaving());
    }

    @Test
    public void formatStrForSavingEvent_noInput_formattedSaving() {
        assertEquals("E| |go for a run|2/12/2021 1800 2100", event.formatStrForSaving());
    }
}
