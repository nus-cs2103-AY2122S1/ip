package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    public void testInstantiateDeadline1() {
        Task deadline = new Deadline("testInstantiateDeadline1", LocalDate.parse(
                "27-08-2021", DateTimeFormatter.ofPattern("dd-MM-yyyy")), 1);
        assertEquals("[D][ ][!] testInstantiateDeadline1 (by: 27 Aug 2021)", deadline.toString());
    }

    @Test
    public void testInstantiateDeadline2() {
        Task deadline = new Deadline("testInstantiateDeadline2", false, LocalDate.parse(
                "28-08-2021", DateTimeFormatter.ofPattern("dd-MM-yyyy")), 2);
        assertEquals("[D][ ][*] testInstantiateDeadline2 (by: 28 Aug 2021)", deadline.toString());
    }

    @Test
    public void testInstantiateDeadline3() {
        Task deadline = new Deadline("testInstantiateDeadline3", true, LocalDate.parse(
                "29-08-2021", DateTimeFormatter.ofPattern("dd-MM-yyyy")), 3);
        assertEquals("[D][X][ ] testInstantiateDeadline3 (by: 29 Aug 2021)", deadline.toString());
    }

    @Test
    public void testParseForStorage1() {
        Task deadline = new Deadline("testParseForStorage1", LocalDate.parse(
                "27-08-2021", DateTimeFormatter.ofPattern("dd-MM-yyyy")), 3);
        assertEquals("D | 0 | 3 | testParseForStorage1 | 27 Aug 2021", deadline.parseForStorage());
    }

    @Test
    public void testParseForStorage2() {
        Task deadline = new Deadline("testParseForStorage2", false, LocalDate.parse(
                "28-08-2021", DateTimeFormatter.ofPattern("dd-MM-yyyy")), 2);
        assertEquals("D | 0 | 2 | testParseForStorage2 | 28 Aug 2021", deadline.parseForStorage());
    }

    @Test
    public void testParseForStorage3() {
        Task deadline = new Deadline("testParseForStorage3", true, LocalDate.parse(
                "29-08-2021", DateTimeFormatter.ofPattern("dd-MM-yyyy")), 1);
        assertEquals("D | 1 | 1 | testParseForStorage3 | 29 Aug 2021", deadline.parseForStorage());
    }

    @Test
    public void testGetDate() {
        Deadline deadline = new Deadline("testGetDate", true, LocalDate.parse(
                "29-08-2021", DateTimeFormatter.ofPattern("dd-MM-yyyy")), 1);
        assertEquals("29 Aug 2021", deadline.getDate());
    }
}
