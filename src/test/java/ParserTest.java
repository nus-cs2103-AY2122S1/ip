import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.Parser;

public class ParserTest {

    @Test
    public void testCommandInterpretation1() {
        assertEquals(true, Parser.interpretCommand("todo kill morty"));
    }

    @Test
    public void testCommandInterpretation2() {
        assertEquals(false, Parser.interpretCommand("bye"));
    }
}
