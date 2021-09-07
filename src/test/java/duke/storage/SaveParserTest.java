package duke.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import java.util.Scanner;

import duke.exceptions.DukeReadSaveException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

class SaveParserTest {

    @Test
    void hasNextLine_matchScanner() {
        Scanner s = new Scanner("line1\nline2");
        SaveParser parser = new SaveParser(s);
        while (s.hasNextLine()) {
            assertEquals(s.hasNextLine(), parser.hasNextLine());
            s.next();
        }
    }

    @Test
    void getNextTask_Todo() throws DukeReadSaveException {
        String testString = "Task:todo\n"
                + "\tName:todoName\n"
                + "\tDone:true\n";

        Task todoTask = new Todo("todoName", true);

        Scanner s = new Scanner(testString);
        SaveParser parser = new SaveParser(s);
        assertEquals(todoTask.toString(), parser.getNextTask().toString());
    }

    @Test
    void getNextTask_Deadline() throws DukeReadSaveException {
        String testString = "Task:deadline\n"
                + "\tName:todoName\n"
                + "\tDone:true\n"
                + "\tBy:Right Now";

        Task todoTask = new Deadline("todoName", true, "Right Now");

        Scanner s = new Scanner(testString);
        SaveParser parser = new SaveParser(s);
        assertEquals(todoTask.toString(), parser.getNextTask().toString());
    }

    @Test
    void getNextTask_Event() throws DukeReadSaveException {
        String testString = "Task:event\n"
                + "\tName:todoName\n"
                + "\tDone:true\n"
                + "\tAt:Right Now";

        Task todoTask = new Event("todoName", true, "Right Now");

        Scanner s = new Scanner(testString);
        SaveParser parser = new SaveParser(s);
        assertEquals(todoTask.toString(), parser.getNextTask().toString());
    }

    @Test
    void getNextTask_EmptyLine_Exception() {
        String testString = "Task:todo\n"
                + "\tName:todoName\n"
                + "\n"
                + "Task:todo\n";

        Task todoTask = new Todo("todoName", true);

        Scanner s = new Scanner(testString);
        SaveParser parser = new SaveParser(s);
        boolean caught = false;

        try {
            assertEquals(todoTask.toString(), parser.getNextTask().toString());
        } catch (DukeReadSaveException e) {
            caught = true;
        }
        assertTrue(caught);
    }

    @Test
    void getNextTask_EmptyMiddleLine_ValidData() throws DukeReadSaveException {
        String testString = "Task:todo\n"
                + "\tName:todoName\n"
                + "\n"
                + "\tDone:true\n";

        Task todoTask = new Todo("todoName", true);

        Scanner s = new Scanner(testString);
        SaveParser parser = new SaveParser(s);
        assertEquals(todoTask.toString(), parser.getNextTask().toString());
    }
}
