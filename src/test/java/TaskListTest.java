import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import blitz.BlitzException;
import blitz.TaskList;
import blitz.Ui;
import org.junit.jupiter.api.Test;

import blitz.tasks.Deadline;
import blitz.tasks.Task;
import blitz.tasks.Todo;

class TaskListTest {

    @Test
    void get() {
        String date = "2/12/2019 1800";
        LocalDateTime d = LocalDateTime.parse(date,
            DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));

        ArrayList<Task> tasks = new ArrayList<>() {
            {
                add(new Todo("read book"));
                add(new Deadline("return book", d));
            }
        };
        assertEquals("[T][ ] read book", new TaskList(new ArrayList<>(tasks)).get(0).toString());
    }

    @Test
    void addTask() throws BlitzException {
        String date = "2/12/2019 1800";
        LocalDateTime d = LocalDateTime.parse(date,
                DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));

        TaskList tasksOld = new TaskList(new ArrayList<>() {
            {
                add(new Todo("read book"));
            }
        });

        TaskList tasksNew = new TaskList(new ArrayList<>() {
            {
                add(new Todo("read book"));
                add(new Deadline("return book", d));
            }
        });

        tasksOld.addTask(new Deadline("return book", d));

        boolean isEqual = tasksNew.getTaskList().toString().equals(tasksOld.getTaskList().toString());
        assertEquals(true, isEqual);
    }

    @Test
    void deleteTask() throws BlitzException {
        String date = "2/12/2019 1800";
        LocalDateTime d = LocalDateTime.parse(date,
                DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
        TaskList tasksOld = new TaskList(new ArrayList<>() {
            {
                add(new Todo("read book"));
                add(new Deadline("return book", d));
            }
        });

        TaskList tasksNew = new TaskList(new ArrayList<>() {
            {
                add(new Todo("read book"));
            }
        });

        tasksOld.deleteTask(1);

        boolean isEqual = tasksNew.getTaskList().toString().equals(tasksOld.getTaskList().toString());
        assertEquals(true, isEqual);

    }

    @Test
    void size() {
        TaskList tasks = new TaskList(new ArrayList<>() {
            {
                add(new Todo("read book"));
            }
        });
        assertEquals(1, tasks.size());
    }
}
