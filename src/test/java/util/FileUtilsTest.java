package util;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class FileUtilsTest {

    @Test
    void loadFile() {
        List<String> formattedTasks = FileUtils.loadFile("data", "duke.txt");
        List<String> expected = new ArrayList<>();
        expected.add("T | 0 | read book");
        expected.add("D | 0 | return book | 2021-06-06 18:00");
        expected.add("T | 0 | join sports club");
        expected.add("T | 1 | borrow book");
        expected.add("D | 1 | return book | 2021-08-08 10:00");
        expected.add("E | 1 | project meeting | 2021-08-20 14:00-16:00");
        assertArrayEquals(expected.toArray(String[]::new), formattedTasks.toArray(String[]::new));
    }
}
