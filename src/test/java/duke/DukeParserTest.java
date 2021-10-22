package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import duke.classes.DukeParser;
import duke.classes.TaskList;
import duke.classes.exceptions.DukeException;

public class DukeParserTest {
    @Test
    public void test1() {
        TaskList taskList = new TaskList(new ArrayList<>());
        String result = "";
        DukeParser test = new DukeParser(taskList);
        try {
            result = test.parse("what");
        } catch (DukeException e) {
            result = e.getMessage();
        }

        String expectedOutput = "!!! I'm sorry, but I don't know what what means. !!!";

        assertEquals(expectedOutput, result);
    }

    @Test
    public void test2() {
        TaskList taskList = new TaskList(new ArrayList<>());
        String result = "";
        DukeParser test = new DukeParser(taskList);
        try {
            result = test.parse("todo Test");
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }

        String expectedOutput =
                "Got it. I've added this task:\n\t" + "[T][ ] test"
                        + "\nNow you have " + 1 + " tasks in the list.";

        assertEquals(expectedOutput, result);
    }
}
