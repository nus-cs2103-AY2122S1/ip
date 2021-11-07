package test.duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

import duke.TaskList;

class TaskListTest {

    @Test
    void addingTask() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        TaskList tasks = new TaskList();
        tasks.addTodo("homework", true);
        assertEquals("Got it. I've added this task:\n[T] [✕] homework\n"
               + "Now you have 1 tasks in the list.\n" , outContent.toString());

        outContent.reset();
        tasks.addDeadline("assignment", "2021-01-01 12:00", true);
        assertEquals("Got it. I've added this task:\n"
                + "[D] [✕] assignment (by: Jan 01 2021 12:00)\n"
                + "Now you have 2 tasks in the list.\n", outContent.toString());

        outContent.reset();
        tasks.addEvent("carnival", "2021-01-01 12:00", true);
        assertEquals("Got it. I've added this task:\n"
                + "[E] [✕] carnival (at: Jan 01 2021 12:00)\n"
                + "Now you have 3 tasks in the list.\n", outContent.toString());
    }
}
