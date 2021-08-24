import duke.Parser;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;
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
