package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

public class StorageTest {
    @Test
    public void autoSaveTest_tasksEntered_automaticallySaved() {
        try {
            Scanner testScanner = new Scanner("todo sleep\n"
                    + "deadline assignment /by 2021-08-09\n"
                    + "event meeting /at 2021-08-01\n"
                    + "done 1\n"
                    + "done 2\n"
                    + "done 3\n"
                    + "bye");
            Storage storage = new Storage();
            Parser parser = new Parser(new TaskList(storage));
            parser.parse(testScanner);

            BufferedReader expectedReader = new BufferedReader(new FileReader(String.valueOf(Path.of("data",
                    "expected-storage"))));
            BufferedReader actualReader = new BufferedReader(new FileReader(String.valueOf(Path.of("data",
                    "record"))));
            String expected = expectedReader.readLine();
            String actual = actualReader.readLine();

            while (expected != null && actual != null) {
                assertEquals(expected, actual);
                expected = expectedReader.readLine();
                actual = actualReader.readLine();
            }

            if (expected != null || actual != null) {
                fail();
            }

        } catch (IOException e) {
            fail("The expected output is different from the actual output");
        }
    }

    @Test
    public void saveLoadTest_tasksEntered_savedAndLoaded() {
        try {
            Scanner testScanner = new Scanner("todo sleep\n"
                    + "deadline assignment /by 2021-08-09\n"
                    + "event meeting /at 2021-08-01\n"
                    + "done 1\n"
                    + "done 2\n"
                    + "done 3\n"
                    + "save data/another-record\n"
                    + "deleteAll\n"
                    + "load data/another-record\n"
                    + "bye");
            Storage storage = new Storage();
            Parser parser = new Parser(new TaskList(storage));
            parser.parse(testScanner);

            BufferedReader expectedReader = new BufferedReader(new FileReader(String.valueOf(Path.of("data",
                    "expected-storage"))));
            BufferedReader actualReader = new BufferedReader(new FileReader(String.valueOf(Path.of("data",
                    "another-record"))));
            String expected = expectedReader.readLine();
            String actual = actualReader.readLine();

            while (expected != null && actual != null) {
                assertEquals(expected, actual);
                expected = expectedReader.readLine();
                actual = actualReader.readLine();
            }

            if (expected != null || actual != null) {
                fail();
            }
        } catch (FileNotFoundException e) {
            fail("Either the test or the expected out file not found.");
        } catch (IOException e) {
            fail("The expected output is different from the actual output.");
        }
    }
}
