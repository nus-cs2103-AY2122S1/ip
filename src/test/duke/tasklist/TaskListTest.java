package duke.tasklist;

import duke.exception.DukeException;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import org.junit.jupiter.api.Test;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TaskListTest {
    class todoStub extends Todo {
        public todoStub() {
            super("task");
        }
    }

    class eventStub extends Event {
        public eventStub() {
            super("task", "2021-09-11 21:30", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"),
                    true);
        }
    }

    @Test
    void tasksAsString() {
        ArrayList<Task> arr = new ArrayList<>();
        arr.add(new todoStub());
        arr.add(new eventStub());
        TaskList tasks = new TaskList(arr);
        assertEquals("1: [T][ ] task\r\n" +
                "2: [E][ ] task (at: Sep 11 2021 09:30 PM)\r\n", tasks.tasksAsString());
    }

    @Test
    void markTaskAsDone() throws DukeException {
        ArrayList<Task> arr = new ArrayList<>();
        arr.add(new todoStub());
        arr.add(new eventStub());
        TaskList tasks = new TaskList(arr);
        tasks.markTaskAsDone(1);
        assertEquals("1: [T][X] task\r\n" +
                "2: [E][ ] task (at: Sep 11 2021 09:30 PM)\r\n", tasks.tasksAsString());
    }
}