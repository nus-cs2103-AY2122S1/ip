package parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import commands.ByeCommand;
import commands.Command;
import commands.DeadlineCommand;
import commands.DeleteCommand;
import commands.DoneCommand;
import commands.DueCommand;
import commands.EventCommand;
import commands.FindCommand;
import commands.ListCommand;
import commands.RescheduleCommand;
import commands.ToDoCommand;

@Tag("Test")
public class ParserTest {
    /**
     * Checks if a DeleteCommand is created despite wrong delete input with word instead of number.
     */
    @Test
    public void test1() {
        Command c = new DeleteCommand(new ArrayList<>());
        assertEquals(c.getClass(), new Parser().parse("delete this").getClass());
    }

    /**
     * Checks if a DeleteCommand is created despite extra spaces.
     */
    @Test
    public void test2() {
        Command c = new DeleteCommand(new ArrayList<>());
        assertEquals(c.getClass(), new Parser().parse("   delete        ").getClass());
    }

    /**
     * Checks if a ByeCommand is created despite additional string.
     */
    @Test
    public void test3() {
        Command c = new ByeCommand(new ArrayList<>());
        assertEquals(c.getClass(), new Parser().parse("bye 12").getClass());
    }

    /**
     * Checks if a ByeCommand is created with correct input.
     */
    @Test
    public void test4() {
        Command c = new ByeCommand(new ArrayList<>());
        assertEquals(c.getClass(), new Parser().parse("bye").getClass());
    }

    /**
     * Checks if a DeadlineCommand is created despite wrong input with missing information.
     */
    @Test
    public void test5() {
        Command c = new DeadlineCommand(new ArrayList<>());
        assertEquals(c.getClass(), new Parser().parse("deadline whatever").getClass());
    }

    /**
     * Checks if a DoneCommand is created despite wrong input with non-number.
     */
    @Test
    public void test6() {
        Command c = new DoneCommand(new ArrayList<>());
        assertEquals(c.getClass(), new Parser().parse("done g!@#!$").getClass());
    }

    /**
     * Checks if a DoneCommand is created with correct input format.
     */
    @Test
    public void test7() {
        Command c = new DoneCommand(new ArrayList<>());
        assertEquals(c.getClass(), new Parser().parse("done 1").getClass());
    }

    /**
     * Checks if a DueCommand is created despite wrong input with non-date.
     */
    @Test
    public void test8() {
        Command c = new DueCommand(new ArrayList<>());
        assertEquals(c.getClass(), new Parser().parse("due yesterday").getClass());
    }

    /**
     * Checks if a EventCommand is created despite a mixture of lower and upper case chars.
     */
    @Test
    public void test9() {
        Command c = new EventCommand(new ArrayList<>());
        assertEquals(c.getClass(), new Parser().parse("EvEnT").getClass());
    }

    /**
     * Checks if a ListCommand is created despite a mixture of lower and upper case chars.
     */
    @Test
    public void test10() {
        Command c = new ListCommand(new ArrayList<>());
        assertEquals(c.getClass(), new Parser().parse("liST").getClass());
    }

    /**
     * Checks if a ToDoCommand is created despite a mixture of lower and upper case chars.
     */
    @Test
    public void test11() {
        Command c = new ToDoCommand(new ArrayList<>());
        assertEquals(c.getClass(), new Parser().parse("tOdo blah").getClass());
    }

    /**
     * Checks if a FindCommand is created despite a mixture of lower and upper case chars.
     */
    @Test
    public void test12() {
        Command c = new FindCommand(new ArrayList<>());
        assertEquals(c.getClass(), new Parser().parse("fiNd 12313").getClass());
    }

    /**
     * Checks if a RescheduleCommand is created despite a mixture of lower and upper case chars
     * and an invalid non-date input.
     */
    @Test
    public void test13() {
        Command c = new RescheduleCommand(new ArrayList<>());
        assertEquals(c.getClass(), new Parser().parse("rescHedule 12313").getClass());
    }
}
