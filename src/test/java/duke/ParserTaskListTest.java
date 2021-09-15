package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

public class ParserTaskListTest {
    @Test
    public void parseAndTaskList_addAndMarkDone_eventsCreatedAndMarkedDone() {
        Scanner testScanner = new Scanner("todo sleep\n"
                + "deadline assignment /by 2021-08-09\n"
                + "event meeting /at 2021-08-01\n"
                + "done 1\n"
                + "done 2\n"
                + "done 3\n");
        Storage storage = new Storage();
        Parser parser = new Parser(new TaskList(storage));
        while (testScanner.hasNextLine()) {
            parser.parse(testScanner.nextLine());
        }
        StubStorage stubStorage = new StubStorage();
        stubStorage.setExpectedResults();
        assertEquals(stubStorage.getUserInputRecords(), storage.getUserInputRecords());
    }

    @Test
    public void parseAndTaskList_delete_eventsDeleted() {
        Scanner testScanner = new Scanner("todo task 1\n"
                + "todo task 2\n"
                + "todo task 3\n"
                + "delete 3\n"
                + "delete 2\n");
        Storage storage = new Storage();
        ArrayList<Task> expectedRecord = new ArrayList<>();
        expectedRecord.add(new ToDo("task 1"));
        Parser parser = new Parser(new TaskList(storage));
        while (testScanner.hasNextLine()) {
            parser.parse(testScanner.nextLine());
        }
        assertEquals(storage.getUserInputRecords(), expectedRecord);
    }

    @Test
    public void parseAndTaskList_deleteAll_allEventsDeleted() {
        Scanner testScanner = new Scanner("todo task 1\n"
                + "todo task 2\n"
                + "todo task 3\n"
                + "deleteAll\n");
        Storage storage = new Storage();
        Parser parser = new Parser(new TaskList(storage));
        while (testScanner.hasNextLine()) {
            parser.parse(testScanner.nextLine());
        }
        assert (storage.getUserInputRecords().isEmpty());
    }

    @Test
    public void parseAndTaskList_update_lastTaskUpdated() {
        Scanner testScanner = new Scanner("todo sleep\n"
                + "deadline assignment /by 2021-08-09\n"
                + "todo 1\n"
                + "update 3 /to meeting /at 2021-08-01\n"
                + "done 1\n"
                + "done 2\n"
                + "done 3\n");
        Storage storage = new Storage();
        Parser parser = new Parser(new TaskList(storage));
        while (testScanner.hasNextLine()) {
            parser.parse(testScanner.nextLine());
        }
        StubStorage stubStorage = new StubStorage();
        stubStorage.setExpectedResults();
        assertEquals(stubStorage.getUserInputRecords(), storage.getUserInputRecords());
    }
}
