package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import duke.stubs.DeadlineStub;
import duke.stubs.EventStub;
import duke.stubs.TodoStub;
import duke.task.Task;


class UiTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    void testShowWelcome() {
        Ui ui = new Ui();
        ui.showWelcome();

        assertEquals("Hello! I'm Duke\nWhat can I do for you?", outputStreamCaptor.toString().trim());
    }

    @Test
    void testList() {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new TodoStub());
        tasks.add(new DeadlineStub());
        tasks.add(new EventStub());
        TaskList taskList = new TaskList(tasks);

        Ui ui = new Ui();
        ui.list(taskList);

        assertEquals("Here are the tasks in your list:\n"
                + "1.[T][ ] Create a todo task\n2.[D][ ] Create a deadline task (by: Dec 4 2021)\n"
                + "3.[E][ ] Create an event task (at: Dec 4 2021)", outputStreamCaptor.toString().trim());
    }

    @Test
    void testDone() {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new TodoStub());
        tasks.add(new DeadlineStub());
        tasks.add(new EventStub());
        TaskList taskList = new TaskList(tasks);

        Ui ui = new Ui();
        ui.done(taskList, 1);
        assertEquals("Nice! I've marked this task as done:\n  [T][ ] Create a todo task",
                outputStreamCaptor.toString().trim());
    }

    @Test
    void testDelete() {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new TodoStub());
        tasks.add(new DeadlineStub());
        tasks.add(new EventStub());
        TaskList taskList = new TaskList(tasks);

        Ui ui = new Ui();
        ui.delete(taskList, 1);
        assertEquals("Noted. I've removed this task:\n  [T][ ] Create a todo task\n"
                        + "Now you have 2 tasks in the list.", outputStreamCaptor.toString().trim());
    }

    @Test
    void testExit() {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new TodoStub());
        tasks.add(new DeadlineStub());
        tasks.add(new EventStub());
        TaskList taskList = new TaskList(tasks);

        Ui ui = new Ui();
        ui.exit();
        assertEquals("Bye. Hope to see you again soon!",
                outputStreamCaptor.toString().trim());
    }
}
