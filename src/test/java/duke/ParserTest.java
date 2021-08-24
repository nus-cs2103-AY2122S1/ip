import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void deadlineTest() {
        String out = Parser.parse("deadline finish CS2103T code /by 2021-08-23").toString();
        AssertEquals(out, "[D][ ] finish CS2103T code (by: Mon., 23 Aug. 2021)");
    }

    @Test
    public void eventTest() {
        String out = Parser.parse("event finish CS2103T code /at 2021-08-23").toString();
        AssertEquals(out, "[E][ ] finish CS2103T code (at: Mon., 23 Aug. 2021)");
    }
}