package duke;

import duke.exceptions.DukeException;
import duke.tasks.*;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeStorageTest {
    @Test
    public void Test1(){
        DukeStorage storage = new DukeStorage();
        List<Task> test = new ArrayList<>();
        try{
            test = storage.retrieve("test.txt");
        } catch (DukeException | FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

        List<Task> expectedOutput = new ArrayList<>();

        assertEquals(expectedOutput, test);
    }
}
