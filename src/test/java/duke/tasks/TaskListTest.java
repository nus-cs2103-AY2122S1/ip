package duke.tasks;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

class TaskListTest {
    private TaskList tasks;

    @BeforeEach
    void setUp() {
        ArrayList<Task> tasks = new ArrayList<Task>(
                List.of(new ToDo("A"),
                        new Deadline("B", LocalDate.parse("2021-09-13")),
                        new Event("C", LocalDate.parse("2021-09-12"), LocalDate.parse("2021-09-14"))));
        this.tasks = new TaskList(tasks);
    }

    @Test
    void findTest_taskWithB_deadlineTaskShown() {
        String expected = "1. " + new Deadline("B", LocalDate.parse("2021-09-13")).toString();
        assertEquals(expected, tasks.find("B"));
    }
}