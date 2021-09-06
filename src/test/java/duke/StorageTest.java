package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

public class StorageTest {

    private void readTestInputs(Scanner testScanner, Parser parser) {
        while (testScanner.hasNextLine()) {
            parser.parse(testScanner.nextLine());
        }
    }

    @Test
    public void autoSaveTest_tasksEntered_automaticallySaved() {
        Scanner testScanner = new Scanner("todo sleep\n"
                + "deadline assignment /by 2021-08-09\n"
                + "event meeting /at 2021-08-01\n"
                + "done 1\n"
                + "done 2\n"
                + "done 3\n");
        Storage storage = new Storage();
        Parser parser = new Parser(new TaskList(storage));
        readTestInputs(testScanner, parser);
        StubStorage stubStorage = new StubStorage();
        stubStorage.setExpectedResults();

        assertEquals(stubStorage.getUserInputRecords(), storage.getUserInputRecords());
    }

    @Test
    public void saveLoadTest_tasksEntered_savedAndLoaded() {
        Scanner testScanner = new Scanner("todo sleep\n"
                + "deadline assignment /by 2021-08-09\n"
                + "event meeting /at 2021-08-01\n"
                + "done 1\n"
                + "done 2\n"
                + "done 3\n"
                + "save data/another-record\n"
                + "deleteAll\n"
                + "load data/another-record\n");
        Storage storage = new Storage();
        Parser parser = new Parser(new TaskList(storage));
        readTestInputs(testScanner, parser);
        StubStorage stubStorage = new StubStorage();
        stubStorage.setExpectedResults();

        assertEquals(stubStorage.getUserInputRecords(), storage.getUserInputRecords());
    }

    @AfterAll
    private static void cleanUp() {
        try {
            Storage storage = new Storage();
            Parser parser = new Parser(new TaskList(storage));
            parser.parse("deleteAll");
            Files.delete(Path.of("data", "another-record"));
        } catch (IOException e) {
            System.out.println("Error during clean up" + e);
        }
    }
}
