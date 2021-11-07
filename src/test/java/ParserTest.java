import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.Parser;

public class ParserTest {

    /**
     * Testing the creation of a ToDo. Should return true if the test case passes.
     */
    @Test
    public void testCommandInterpretation1() {
        assertEquals(true, Parser.interpretCommand("todo kill morty"));
    }

    /**
     * Testing whether Duke goes to sleep when user input is "bye". Should return false to pass the test case.
     */
    @Test
    public void testCommandInterpretation2() {
        assertEquals(false, Parser.interpretCommand("bye"));
    }
}
