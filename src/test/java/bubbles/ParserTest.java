package bubbles;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import bubbles.util.Parser;

public class ParserTest {
    @Test
    public void testFormatTask() {
        Parser p = new Parser();
        String task = "E | 1 | meeting with cs2101 groupmates | at 2021-08-25";
        Object[] arr = {"E", "meeting with cs2101 groupmates /at 2021-08-25", true};

        // as we are testing the items in the Object array, we have to test the items in the array one by one
        // instead of testing assertEquals() on two arrays, which would definitely return false
        // as the two arrays are completely different objects

        for (int i = 0; i < 3; i++) {
            assertEquals(arr[i], p.formatTask(task)[i]);
        }
    }
}
