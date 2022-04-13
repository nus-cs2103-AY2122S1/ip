package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

/**
 * Tests the duke.
 */
public class DukeTest {
    private List<Task> tasks = new ArrayList<>(Arrays.asList(new ToDo("return book", false),
            new DeadLine("buy book", LocalDate.of(2020, 8, 23), false)));

    /**
     * Tests if taskList.size() returns the correct input.
     */
    @Test
    public void taskList_twoTasks_listSizeTwo() {
        TaskList tLst = new TaskList(tasks);
        assertEquals(2, tLst.size());
    }

    /**
     * Tests delete task.
     */
    @Test
    public void taskList_deleteTask_listSizeOne() {
        TaskList tLst = new TaskList(tasks);
        Task taskToBeDeleted = tLst.get(0);
        tLst.deleteTask(taskToBeDeleted);
        assertEquals(1, tLst.size());
    }

    /**
     * Tests parser for parsing dates.
     */
    @Test
    public void parseDate_invalidDate_invalidDateTimeException() {
        Parser parser = new Parser();
        assertThrows(InvalidDateTimeException.class, () -> parser.stringToLocalDate("2021-10-3"));
        assertThrows(InvalidDateTimeException.class, () -> parser.stringToLocalDate("2021-1-30"));
        assertThrows(InvalidDateTimeException.class, () -> parser.stringToLocalDate("2000-13-10"));
        assertThrows(InvalidDateTimeException.class, () -> parser.stringToLocalDate("20-10-29"));
        assertThrows(InvalidDateTimeException.class, () -> parser.stringToLocalDate("1999-1-3"));
    }


    /**
     * Tests parser for parsing dates.
     *
     * @throws InvalidDateTimeException if the date is invalid.
     */
    @Test
    public void parseDate_validDate_noException() throws InvalidDateTimeException {
        Parser parser = new Parser();
        assertEquals(LocalDate.of(1999, 10, 30), parser.stringToLocalDate("1999-10-30"));
        assertEquals(LocalDate.of(0, 10, 30), parser.stringToLocalDate("0000-10-30"));
        assertEquals(LocalDate.of(2020, 1, 3), parser.stringToLocalDate("2020-01-03"));
    }
}
