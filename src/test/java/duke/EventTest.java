package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    public void testInstantiateEvent1() {
        Task event = new Event("testInstantiateEvent1", LocalDateTime.parse("27-08-2021 2211",
                DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm")), 1);
        assertEquals("[E][ ][!] testInstantiateEvent1 (at: 27 Aug 2021 22:11)", event.toString());
    }

    @Test
    public void testInstantiateEvent2() {
        Task event = new Event("testInstantiateEvent2", false, LocalDateTime.parse(
                "28-08-2021 1259", DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm")), 2);
        assertEquals("[E][ ][*] testInstantiateEvent2 (at: 28 Aug 2021 12:59)", event.toString());
    }

    @Test
    public void testInstantiateEvent3() {
        Task event = new Event("testInstantiateEvent3", true, LocalDateTime.parse(
                "26-08-2021 0331", DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm")), 3);
        assertEquals("[E][X][ ] testInstantiateEvent3 (at: 26 Aug 2021 03:31)", event.toString());
    }

    @Test
    public void testParseForStorage1() {
        Task event = new Event("testParseForStorage1", LocalDateTime.parse(
                "29-08-2021 0000", DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm")), 3);
        assertEquals("E | 0 | 3 | testParseForStorage1 | 29 Aug 2021 00:00", event.parseForStorage());
    }

    @Test
    public void testParseForStorage2() {
        Task event = new Event("testParseForStorage2", false, LocalDateTime.parse(
                "30-08-2021 2359", DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm")), 2);
        assertEquals("E | 0 | 2 | testParseForStorage2 | 30 Aug 2021 23:59", event.parseForStorage());
    }

    @Test
    public void testParseForStorage3() {
        Task event = new Event("testParseForStorage3", true, LocalDateTime.parse(
                "29-08-2021 0000", DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm")), 1);
        assertEquals("E | 1 | 1 | testParseForStorage3 | 29 Aug 2021 00:00", event.parseForStorage());
    }

    @Test
    public void testGetTime() {
        Event event = new Event("testGetTime", true, LocalDateTime.parse(
                "29-08-2021 0000", DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm")), 1);
        assertEquals("29 Aug 2021 00:00", event.getTime());
    }
}
