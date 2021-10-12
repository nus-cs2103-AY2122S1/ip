package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import duke.classes.DukeParser;
import duke.classes.commands.DukeUI;
import org.junit.jupiter.api.Test;

import duke.classes.TaskList;
import duke.classes.exceptions.DukeException;

public class DukeParserTest {
    @Test
    public void test1() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        TaskList taskList = new TaskList(new ArrayList<>());
        DukeUI ui = new DukeUI();

        DukeParser test = new DukeParser(taskList);
        try {
            test.parse("test");
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }

        String expectedOutput = "!!! I'm sorry, but I don't know what that means. !!!\r\n";

        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void test2() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        TaskList taskList = new TaskList(new ArrayList<>());
        DukeUI ui = new DukeUI();

        DukeParser test = new DukeParser(taskList);
        try {
            test.parse("todo Test");
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }

        String expectedOutput = "____________________________________________________________\n"
                + "Got it. I've added this task: \n"
                + "\t[T][ ] Test\n"
                + "Now you have 1 tasks in the list.\n"
                + "____________________________________________________________";

        assertEquals(expectedOutput, outContent.toString().trim().replace("\r", ""));
    }
}
