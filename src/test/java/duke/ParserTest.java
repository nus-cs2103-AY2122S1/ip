import duke.exception.DukeExcpetion;
import duke.processor.Parser;
import duke.processor.TaskList;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void deadline_date() {
         TaskList test = new TaskList();
         String out = "";
         try {
             out = Parser.parse("deadline finish CS2103T code /by 2021-08-23", test).toString();
         } catch (DukeExcpetion e) {
             System.out.println(e);
         }
         assertEquals("[D][ ] finish CS2103T code (by: Mon, 23 Aug 2021)", out);
    }

    @Test
    public void event_date_time() {
         TaskList test = new TaskList();
         String out = "";
         try {
             out = Parser.parse("event finish CS2103T code /at 2021-08-23 0800", test).toString();
         } catch (DukeExcpetion e) {
             System.out.println(e);
         }
         assertEquals("[E][ ] finish CS2103T code (at: Mon, 23 Aug 2021 08:00:00)", out);
    }
}