package Duke.Main;

import Duke.Task.Task;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {

    @Test
    public void testDateConversion() {
        LocalDate today = LocalDate.now();
        assertEquals("Aug 26 2021", Parser.convert(today));
        LocalDate day1 = LocalDate.parse("2021-07-01");
        assertEquals("Jul 01 2021", Parser.convert(day1));
        LocalDate day2 = LocalDate.parse("2021-03-31");
        assertEquals("Mar 31 2021", Parser.convert(day2));
        assertThrows(DateTimeParseException.class, () ->
                Parser.convert(LocalDate.parse("08/10/2021")));
    }

    @Test
    public void testDateReversion() {
        assertEquals("2021-08-26", Parser.reverse("Aug 26 2021"));
        assertEquals("2017-09-13", Parser.reverse("Sep 13 2017"));
        assertEquals("2020-11-01", Parser.reverse("Nov 01 2020"));
        assertEquals("1945-09-02", Parser.reverse("Sep 02 1945"));
    }

    @Test
    public void testParser() {
        assertTrue(Parser.parse("help", null).contains(
                        "Here is a comprehensive list of commands you can use:"));
        assertTrue(Parser.parse("help", null).contains(
                "8. 'bye': Exit the program"));
        assertEquals("1. Item 1\n2. Item 2\n3. Item 3",
                Parser.parse("list", new TaskListStub()));
        assertEquals("Task of index 3 is done",
                Parser.parse("done 3", new TaskListStub()));
        assertEquals("All tasks are done",
                Parser.parse("done all", new TaskListStub()));
        assertEquals("Task of index 69 is done",
                Parser.parse("done 69", new TaskListStub()));
        assertEquals("Task of index 6 is deleted",
                Parser.parse("delete 6", new TaskListStub()));
        assertEquals("Task list is reset to empty",
                Parser.parse("delete all", new TaskListStub()));
        assertEquals("Task of index 35 is deleted",
                Parser.parse("delete 35", new TaskListStub()));
        assertThrows(DukeException.class, () ->
                Parser.parse("bruh", new TaskListStub()));
        assertThrows(DukeException.class, () ->
                Parser.parse("oof", new TaskListStub()));
        assertEquals("Noted, task coding is added",
                Parser.parse("todo coding", new TaskListStub()));
        assertEquals("Noted, task IP project is added",
                Parser.parse("deadline IP project", new TaskListStub()));
    }

    private static class TaskListStub extends TaskList {
        @Override
        public String printList() {
            return "1. Item 1\n2. Item 2\n3. Item 3";
        }

        @Override
        public String done(int index) {
            return "Task of index " + index + " is done";
        }

        @Override
        public String doneAll() {
            return "All tasks are done";
        }

        @Override
        public String delete(int index) {
            return "Task of index " + index + " is deleted";
        }

        @Override
        public String deleteAll() {
            return "Task list is reset to empty";
        }

        @Override
        public String addTask(String task, Task.Type type) {
            return "Noted, task " + task + " is added";
        }
    }
}
