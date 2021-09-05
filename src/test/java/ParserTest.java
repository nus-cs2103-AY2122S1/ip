//import duke.DukeAction;
//import duke.DukeException;
//import duke.Parser;
//
//import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//
//public class ParserTest {
//    @Test
//    public void stringToDukeAction_validInputs_actionTypeReturned() throws Exception{
//        assertEquals(Parser.stringToDukeAction(" bye", 1), DukeAction.EXIT);
//        assertEquals(Parser.stringToDukeAction("list", 1), DukeAction.PRINT_LIST);
//        assertEquals(Parser.stringToDukeAction("todo a", 1), DukeAction.TODO);
//        assertEquals(Parser.stringToDukeAction("deadline a /by b", 1), DukeAction.DEADLINE);
//        assertEquals(Parser.stringToDukeAction("event a /at b", 1), DukeAction.EVENT);
//        assertEquals(Parser.stringToDukeAction("delete 10000", 10000), DukeAction.DELETE);
//        assertEquals(Parser.stringToDukeAction("done 10000", 10000), DukeAction.MARK_DONE);
//    }
//
//    @Test
//    public void stringToDukeAction_invalidInputs_exceptionThrown() {
//        assertThrows(DukeException.class, () -> {
//            Parser.stringToDukeAction("bye s", 1);
//        });
//        assertThrows(DukeException.class, () -> {
//            Parser.stringToDukeAction("list a", 1);
//        });
//        assertThrows(DukeException.class, () -> {
//            Parser.stringToDukeAction("todo", 1);
//        });
//        assertThrows(DukeException.class, () -> {
//            Parser.stringToDukeAction("deadline  /by s", 1);
//        });
//        assertThrows(DukeException.class, () -> {
//            Parser.stringToDukeAction("event a /by b", 1);
//        });
//        assertThrows(DukeException.class, () -> {
//            Parser.stringToDukeAction("delete -1", 0);
//        });
//        assertThrows(DukeException.class, () -> {
//            Parser.stringToDukeAction("done 2", 1);
//        });
//    }
//}