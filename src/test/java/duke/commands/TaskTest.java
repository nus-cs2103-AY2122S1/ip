package duke.commands;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    @Test
    public void testGetLogo() {
        Task test = new Task("test", "test");
        assertEquals( "test", test.getLogo());
    }
}