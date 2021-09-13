package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class ParserTest {
    @Test
    void addTask_deadline_success() throws DukeException {
        TaskList tasks = new TaskList();
        Parser parser = new Parser(tasks);

        parser.parse("deadline return book /by 20/8/2021 1500");

        assertEquals(tasks.size(), 1);
        assertEquals("[D][ ] return book (by: Fri, 20 Aug 2021 03:00PM)", tasks.get(0).toString());
    }

    @Test
    void addTask_todoEmpty_exceptionThrown() {
        TaskList tasks = new TaskList();
        Parser parser = new Parser(tasks);

        assertThrows(DukeException.class, () ->
                parser.parse("todo"));
        assertEquals(tasks.size(), 0);
    }
}
