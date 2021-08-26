import duke.DukeExcpetion;
import duke.Parser;
import duke.TaskList;
import duke.Executable;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void deadlineTest() {
         TaskList test = new TaskList();
         String out = "";
         try {
             out = Parser.parse("deadline finish CS2103T code /by 2021-08-23", test).toString();
         } catch (DukeExcpetion e) {
             System.out.println(e);
         }
         assertEquals(out, "[D][ ] finish CS2103T code (by: Mon., 23 Aug. 2021)");
    }

    @Test
    public void eventTest() {
         TaskList test = new TaskList();
         String out = "";
         try {
             out = Parser.parse("event finish CS2103T code /at 2021-08-23", test).toString();
         } catch (DukeExcpetion e) {
             System.out.println(e);
         }
         assertEquals(out, "[E][ ] finish CS2103T code (at: Mon., 23 Aug. 2021)");
    }
}