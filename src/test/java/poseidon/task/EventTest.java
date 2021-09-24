//package poseidon.task;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//import java.time.format.FormatStyle;
//
//import org.junit.jupiter.api.Test;
//
//import poseidon.parser.Parser;
//
///**
// * Represents a testing class for {@code Event}.
// *
// * @author Yeluri Ketan
// * @version CS2103T AY21/22 Sem 1 iP
// */
//public class EventTest {
//
//    @Test
//    public void eventConstructor_newEvent_correctDescAndStatus() {
//        String description = "Team meeting";
//        LocalDateTime fromDt = Parser.parseDateTime("2021 08 30 2000");
//        LocalDateTime toDt = Parser.parseDateTime("2021 08 30 2200");
//        Event event = new Event(description, fromDt, toDt);
//        assertEquals(description, event.description);
//        assertFalse(event.isDone);
//    }
//
//    @Test
//    public void eventConstructorWithStatus_undoneEvent_correctDoneStatus() {
//        String description = "Team meeting";
//        LocalDateTime fromDt = Parser.parseDateTime("2021 08 30 2000");
//        LocalDateTime toDt = Parser.parseDateTime("2021 08 30 2200");
//        Event event = new Event(description, false, fromDt, toDt);
//        assertFalse(event.isDone);
//    }
//
//    @Test
//    public void eventConstructorWithStatus_doneEvent_correctDoneStatus() {
//        String description = "Team meeting";
//        LocalDateTime fromDt = Parser.parseDateTime("2021 08 30 2000");
//        LocalDateTime toDt = Parser.parseDateTime("2021 08 30 2200");
//        Event event = new Event(description, true, fromDt, toDt);
//        assertTrue(event.isDone);
//    }
//
//    @Test
//    public void setDone_eventMarkedDone_correctDoneStatus() {
//        String description = "Team meeting";
//        LocalDateTime fromDt = Parser.parseDateTime("2021 08 30 2000");
//        LocalDateTime toDt = Parser.parseDateTime("2021 08 30 2200");
//        Event event = new Event(description, fromDt, toDt);
//        event.setDone();
//        assertTrue(event.isDone);
//    }
//
//    @Test
//    public void getDateTime_eventObject_correctFromDateTime() {
//        String description = "Team meeting";
//        LocalDateTime fromDt = Parser.parseDateTime("2021 08 30 2000");
//        LocalDateTime toDt = Parser.parseDateTime("2021 08 30 2200");
//        Event event1 = new Event(description, fromDt, toDt);
//        assertEquals(fromDt, event1.getDateTime());
//
//        Event event2 = new Event(description, true, fromDt, toDt);
//        assertEquals(fromDt, event2.getDateTime());
//
//        Event event3 = new Event(description, false, fromDt, toDt);
//        assertEquals(fromDt, event3.getDateTime());
//    }
//
//    @Test
//    public void toString_eventObject_stringRep() {
//        String description = "Team meeting";
//        LocalDateTime fromDt = Parser.parseDateTime("2021 08 30 2000");
//        LocalDateTime toDt = Parser.parseDateTime("2021 08 30 2200");
//        Event event = new Event(description, fromDt, toDt);
//        String expectedToString = "[E][ ] Team meeting (from: "
//                + fromDt.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM, FormatStyle.SHORT))
//                + " to "
//                + toDt.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM, FormatStyle.SHORT))
//                + ")";
//        assertEquals(expectedToString, event.toString());
//    }
//
//    @Test
//    public void toStorage_eventObject_storageRep() {
//        String description = "Team meeting";
//        LocalDateTime fromDt = Parser.parseDateTime("2021 08 30 2000");
//        LocalDateTime toDt = Parser.parseDateTime("2021 08 30 2200");
//        Event event = new Event(description, fromDt, toDt);
//        String expectedToStorageString = "E%false%Team meeting%2021-08-30T20:00%2021-08-30T22:00\n";
//        assertEquals(expectedToStorageString, event.toStorage());
//    }
//}
