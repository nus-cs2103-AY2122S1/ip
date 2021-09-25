package duke.main;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.Test;


public class ParserTest {
    /**
     * Tests conversion of YYYY-MM-DD date to MMM DD YYYY date
     */
    @Test
    public void testDateConversion() {
        LocalDate today = LocalDate.now();
        assertEquals("Aug 30 2021", Parser.convert(today));
        LocalDate day1 = LocalDate.parse("2021-07-01");
        assertEquals("Jul 01 2021", Parser.convert(day1));
        LocalDate day2 = LocalDate.parse("2021-03-31");
        assertEquals("Mar 31 2021", Parser.convert(day2));
        assertThrows(DateTimeParseException.class, () ->
                Parser.convert(LocalDate.parse("08/10/2021")));
    }

    /**
     * Tests reversion of MMM DD YYYY date back to YYYY-MM-DD date
     */
    @Test
    public void testDateReversion() {
        assertEquals("2021-08-26", Parser.reverse("Aug 26 2021"));
        assertEquals("2017-09-13", Parser.reverse("Sep 13 2017"));
        assertEquals("2020-11-01", Parser.reverse("Nov 01 2020"));
        assertEquals("1945-09-02", Parser.reverse("Sep 02 1945"));
    }

    /**
     * Tests the Parser of DuC, whether it can analyze the correct command or not.
     */
    @Test
    public void testParser() {
        assertTrue(Parser.parse("help", new TaskList()).reply()
                    .contains("Here is a comprehensive list of commands you can use:"));

        assertTrue(Parser.parse("help", new TaskList()).reply()
                .contains("'bye': Exit the program"));
        TaskList taskList = new TaskList();
        File testFile = new File("taskFile/testFile1.txt");
        Storage.loadData("taskFile/testFile1.txt", taskList);
        assertEquals(Parser.parse("done 1", taskList).reply(),
                "Nice! I've marked this task as done: \n" + taskList.get(1));
        assertTrue(taskList.get(1).isCompleted());
        assertEquals("Nice! I've marked all tasks in your list as done!",
                Parser.parse("done all", taskList).reply());
        assertTrue(taskList.get(2).isCompleted());
        assertTrue(taskList.get(3).isCompleted());
        taskList.delete(2);
        assertEquals(2, taskList.size());
        assertEquals(Parser.parse("todo walk 10000 steps", taskList).reply(),
                "Nice! I've added the following task to your list:\n"
                        + taskList.get(3)
                        + "\nNow you have " + taskList.size() + " tasks in your list");
    }

}
